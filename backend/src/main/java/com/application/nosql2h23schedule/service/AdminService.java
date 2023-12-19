package com.application.nosql2h23schedule.service;

import com.application.nosql2h23schedule.domain.Chain;
import com.application.nosql2h23schedule.domain.Group;
import com.application.nosql2h23schedule.repository.GroupRepository;
import com.application.nosql2h23schedule.dto.ChainDto;
import com.application.nosql2h23schedule.repository.ChainRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Map<String, Object> getSchedule(String faculty, int course) {

        Map<String, Object> resultJSON = new HashMap<>();

        List<Group> groups = groupRepository.findAllByFacultyAndCourse(faculty, course);
        List<String> groupNumbers = new ArrayList<>(Collections.emptyList());

        groups.forEach(group -> groupNumbers.add(group.getGroupNumber()));

        resultJSON.put("groups", groupNumbers);

        List<Chain> chains = chainRepository.findAllByFacultyAndGroup_Course(faculty, course);
        if (chains.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Ячейки расписания по данному запросу не найдены.");
        }

        List<String> weekDay = new ArrayList<>(Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY));
        List<String> time = new ArrayList<>(Arrays.asList(FIFTH_PAIR, SECOND_PAIR, THIRD_PAIR,
                FORTH_PAIR, FIFTH_PAIR, SIXTH_PAIR));


        List<Map<String, Object>> chainsJSON = new ArrayList<>();
        weekDay.forEach(day ->
                time.forEach(t ->
                        {
                            Map<String, Object> weekDayTimeJSON = new HashMap<>();
                            weekDayTimeJSON.put("weekDay", day);
                            weekDayTimeJSON.put("time", t);

                            groupNumbers.forEach(groupNumber ->
                                    {
                                        Optional<Chain> chain = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(day, t, groupNumber);

                                        if (chain.isPresent()) {

                                            // ChainDto(chain.get().getId().toHexString() - get Hex String from ObjectId
                                            ChainDto chainDto = new ChainDto(chain.get().getId().toHexString(),
                                                    chain.get().getClassroom(), chain.get().getTeacher(),
                                                    chain.get().getSubject() != null ?
                                                            chain.get().getSubject().getShortSubjectTitle() : null);

                                            weekDayTimeJSON.put(groupNumber, chainDto);

                                        }
                                    }
                            );
                            chainsJSON.add(weekDayTimeJSON);
                        }
                )
        );

        resultJSON.put("chains", chainsJSON);

        return resultJSON;
    }

//    example to deal with ObjectId
    public Chain getChain(String id) {
        System.out.println(id);
        return chainRepository.findById(new ObjectId(id)).isPresent() ? chainRepository.findById(new ObjectId(id)).get() : null;
    }
}
