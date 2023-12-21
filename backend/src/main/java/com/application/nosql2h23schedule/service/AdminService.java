package com.application.nosql2h23schedule.service;

import com.application.nosql2h23schedule.domain.Chain;
import com.application.nosql2h23schedule.domain.Group;
import com.application.nosql2h23schedule.repository.GroupRepository;
import com.application.nosql2h23schedule.dto.ChainDto;
import com.application.nosql2h23schedule.repository.ChainRepository;
import com.application.nosql2h23schedule.request.ScheduleRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.application.nosql2h23schedule.constant.ApplicationConstants.*;

@Service
public class AdminService {

    private final ChainRepository chainRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public AdminService(ChainRepository chainRepository, GroupRepository groupRepository) {
        this.chainRepository = chainRepository;
        this.groupRepository = groupRepository;
    }

    public Map<String, Object> getSchedule(ScheduleRequest scheduleRequest) {

        // list of teachers
        List<String> teachers = new ArrayList<>();

        // result JSON to send to front (3 fields: groups, teachers, chains)
        Map<String, Object> resultJSON = new HashMap<>();

        // fill field groups, find groups from faculty and course? put this in JSON
        List<Group> groups = groupRepository.findAllByFacultyAndCourse(scheduleRequest.getFaculty(), scheduleRequest.getCourse());
        List<String> groupNumbers = new ArrayList<>(Collections.emptyList());

        groups.forEach(group -> groupNumbers.add(group.getGroupNumber()));

        resultJSON.put("groups", groupNumbers);

        // if there is no chains in DB, send from server empty lists with teachers and chains
        List<Chain> chains = chainRepository.findAllByFacultyAndGroup_Course(scheduleRequest.getFaculty(), scheduleRequest.getCourse());
        if (chains.isEmpty()) {
            resultJSON.put("chains", new ArrayList<>());
            resultJSON.put("teachers", new ArrayList<>());
            return resultJSON;
        }

        // week days and pairs - lists
        List<String> weekDay = new ArrayList<>(Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY));
        List<String> time = new ArrayList<>(Arrays.asList(FIRST_PAIR, SECOND_PAIR, THIRD_PAIR,
                FORTH_PAIR, FIFTH_PAIR, SIXTH_PAIR));

        // list of maps for list of chains
        List<Map<String, Object>> chainsJSON = new ArrayList<>();
        // go by week days
        weekDay.forEach(day ->
                // then by time of pairs
                time.forEach(t ->
                        {
                            // map (JSON) to put into list of chains; there are fields: weekDay, time,
                            // group_number (field) - inside: chain info (id, teacher, classroom, subject)
                            Map<String, Object> weekDayTimeJSON = new HashMap<>();
                            weekDayTimeJSON.put("weekDay", day);
                            weekDayTimeJSON.put("time", t);

                            groupNumbers.forEach(groupNumber ->
                                    {
                                        Optional<Chain> chain = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(day, t, groupNumber);

                                        // if chain exist in repo (DB)
                                        if (chain.isPresent()) {

                                            // make list of teachers
                                            if (chain.get().getTeacher() != null &&
                                                    !teachers.contains(chain.get().getTeacher())) {
                                                teachers.add(chain.get().getTeacher());
                                            }


                                            ChainDto chainDto;
                                            // if teacher not null - filter by teacher
                                            if (scheduleRequest.getTeacher() != null) {
                                                // ChainDto(chain.get().getId().toHexString() - get Hex String from ObjectId
                                                // teacher filter == chain.teacher
                                                if (scheduleRequest.getTeacher().equals(chain.get().getTeacher())) {
                                                    // make chainDto with full info about chain
                                                    chainDto = new ChainDto(chain.get().getId().toHexString(),
                                                            chain.get().getClassroom(),
                                                            chain.get().getTeacher(),
                                                            chain.get().getSubject() != null ?
                                                                    chain.get().getSubject().getShortSubjectTitle() : null);
                                                } else {
                                                    // if teacher filter != chain.teacher
                                                    // chain dto empty - we don't need chains with info about other teachers
                                                    chainDto = new ChainDto(chain.get().getId().toHexString(),
                                                            null,
                                                            null,
                                                            null);
                                                }
                                            } else {
                                                // if teacher is null - return all chains
                                                chainDto = new ChainDto(chain.get().getId().toHexString(),
                                                        chain.get().getClassroom(), chain.get().getTeacher(),
                                                        chain.get().getSubject() != null ?
                                                                chain.get().getSubject().getShortSubjectTitle() : null);
                                            }
                                            // put group_number: chainInfo in JSON
                                            weekDayTimeJSON.put(groupNumber, chainDto);

                                        }
                                    }
                            );
                            // put time, week day, list of groups and chains into chain list
                            chainsJSON.add(weekDayTimeJSON);
                        }
                )
        );

        // put chains and teachers into result JSON
        resultJSON.put("chains", chainsJSON);
        resultJSON.put("teachers", teachers);

        return resultJSON;
    }

//    example to deal with ObjectId
    public Chain getChain(String id) {
        System.out.println(id);
        return chainRepository.findById(new ObjectId(id)).isPresent() ? chainRepository.findById(new ObjectId(id)).get() : null;
    }
}
