package com.application.nosql2h23schedule.dev;

import com.application.nosql2h23schedule.domain.Chain;
import com.application.nosql2h23schedule.domain.Group;
import com.application.nosql2h23schedule.domain.Subject;
import com.application.nosql2h23schedule.domain.User;
import com.application.nosql2h23schedule.repository.ChainRepository;
import com.application.nosql2h23schedule.repository.GroupRepository;
import com.application.nosql2h23schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.application.nosql2h23schedule.constant.ApplicationConstants.*;

@Component
public class MongoInitializer {

    private final ChainRepository chainRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;

    @Autowired
    public MongoInitializer(ChainRepository chainRepository, UserRepository userRepository,
                            PasswordEncoder passwordEncoder, GroupRepository groupRepository) {
        this.chainRepository = chainRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.groupRepository = groupRepository;
        init();
    }

    private void init() {
        initTestUSer();
        init4CourseFKTI();
    }

    private void initTestUSer() {
        User user = new User();
        user.setEmail("testuser");
        user.setPassword(passwordEncoder.encode("1"));
        user.setFullName("Test User FullName");
        user.setRole("ROLE_ADMIN");

        userRepository.save(user);
    }

    private void init4CourseFKTI() {
        Group group0382 = new Group();
        group0382.setGroupNumber("0382");
        group0382.setCourse(4);
        group0382.setFaculty("ФКТИ");
        group0382 = groupRepository.save(group0382);
        Group group0381 = new Group();
        group0381.setGroupNumber("0381");
        group0381.setCourse(4);
        group0381.setFaculty("ФКТИ");
        group0381 = groupRepository.save(group0381);
        Group group0383 = new Group();
        group0383.setGroupNumber("0383");
        group0383.setCourse(4);
        group0383.setFaculty("ФКТИ");
        group0383 = groupRepository.save(group0383);
        Group group0303 = new Group();
        group0303.setGroupNumber("0303");
        group0303.setCourse(4);
        group0303.setFaculty("ФКТИ");
        group0303 = groupRepository.save(group0303);
        Group group0304 = new Group();
        group0304.setGroupNumber("0304");
        group0304.setCourse(4);
        group0304.setFaculty("ФКТИ");
        group0304 = groupRepository.save(group0304);

        Subject subjectOPNP = new Subject();
        subjectOPNP.setShortSubjectTitle("ОПНП");

        Subject subjectNoSQL = new Subject();
        subjectNoSQL.setShortSubjectTitle("NoSQL");

        Subject subjectBZD = new Subject();
        subjectBZD.setShortSubjectTitle("лек. БЖД");
        Subject subjectBZDPract = new Subject();
        subjectBZDPract.setShortSubjectTitle("пр. БЖД");
        Subject subjectBZDLab = new Subject();
        subjectBZDLab.setShortSubjectTitle("лаб. БЖД");

        Subject subjectCrypt = new Subject();
        subjectCrypt.setShortSubjectTitle("лек. Криптография");

        Subject subjectCryptPract = new Subject();
        subjectCryptPract.setShortSubjectTitle("пр. Криптография");

        Subject subjectTSOS = new Subject();
        subjectTSOS.setShortSubjectTitle("лек. ЦОС");

        Subject subjectMarket = new Subject();
        subjectMarket.setShortSubjectTitle("лек. Маркетинг");

        Subject subjectMarketPract = new Subject();
        subjectMarketPract.setShortSubjectTitle("пр. Маркетинг");

        List<String> dayOfWeek = new ArrayList<>(Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY));
        List<String> time = new ArrayList<>(Arrays.asList(FIRST_PAIR, SECOND_PAIR, THIRD_PAIR,
                FORTH_PAIR, FIFTH_PAIR, SIXTH_PAIR));
        List<Group> groups = new ArrayList<>(Arrays.asList(group0381, group0382, group0383, group0303, group0304));

        dayOfWeek.forEach(day -> time.forEach(t -> groups.forEach(group ->
                {
                    Chain chain = new Chain();
                    chain.setWeekDay(day);
                    chain.setTime(t);
                    chain.setGroup(group);
                    chain.setFaculty("ФКТИ");
                    chainRepository.save(chain);
                }
        )));

//        0382 week schedule
        Optional<Chain> chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, SECOND_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5134");
            chain.setSubject(subjectBZDLab);
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, THIRD_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5425");
            chain.setSubject(subjectBZD);
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FORTH_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5423");
            chain.setSubject(subjectNoSQL);
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FIFTH_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5423");
            chain.setSubject(subjectOPNP);
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, FIFTH_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5423");
            chain.setSubject(subjectTSOS);
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, FIRST_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5421");
            chain.setSubject(subjectMarket);
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, SECOND_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3423");
            chain.setSubject(subjectMarketPract);
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, THIRD_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("1234");
            chain.setSubject(subjectCrypt);
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, FORTH_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3129");
            chain.setSubject(subjectCryptPract);
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                THURSDAY, THIRD_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3234");
            chain.setSubject(subjectBZDPract);
            chainRepository.save(chain);
        }

        Optional<Chain> chain2 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, THIRD_PAIR, group0381.getGroupNumber());

        if (chain2.isPresent()) {
            Chain chain = chain2.get();
            chainRepository.delete(chain);
            chain.setClassroom("1235");
            chain.setSubject(subjectCrypt);
            chainRepository.save(chain);
        }

        Optional<Chain> chain3 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, THIRD_PAIR, group0383.getGroupNumber());

        if (chain3.isPresent()) {
            Chain chain = chain3.get();
            chainRepository.delete(chain);
            chain.setClassroom("1232");
            chain.setSubject(subjectOPNP);
            chainRepository.save(chain);
        }

        Optional<Chain> chain4 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FORTH_PAIR, group0383.getGroupNumber());

        if (chain4.isPresent()) {
            Chain chain = chain4.get();
            chainRepository.delete(chain);
            chain.setClassroom("5328");
            chain.setSubject(subjectNoSQL);
            chainRepository.save(chain);
        }
    }
}