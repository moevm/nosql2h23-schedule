package com.application.nosql2h23schedule.dev;

import com.application.nosql2h23schedule.domain.Chain;
import com.application.nosql2h23schedule.domain.Group;
import com.application.nosql2h23schedule.domain.Subject;
import com.application.nosql2h23schedule.domain.User;
import com.application.nosql2h23schedule.repository.ChainRepository;
import com.application.nosql2h23schedule.repository.GroupRepository;
import com.application.nosql2h23schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        User user = new User();
        user.setEmail("testuser");
        user.setPassword(passwordEncoder.encode("1"));
        user.setFullName("Test User FullName");
        user.setRole("ROLE_ADMIN");

        if (userRepository.findUserByEmail(user.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Пользователь с таким email уже существует.");

        userRepository.save(user);

        Group group0382 = new Group();
        group0382.setGroupNumber("0382");
        group0382.setAmount(25);
        group0382.setCourse(4);
        group0382.setFaculty("ФКТИ");
        group0382 = groupRepository.save(group0382);
        Group group0381 = new Group();
        group0381.setGroupNumber("0381");
        group0381.setAmount(24);
        group0381.setCourse(4);
        group0381.setFaculty("ФКТИ");
        group0381 = groupRepository.save(group0381);
        Group group0383 = new Group();
        group0383.setGroupNumber("0383");
        group0383.setAmount(19);
        group0383.setCourse(4);
        group0383.setFaculty("ФКТИ");
        group0383 = groupRepository.save(group0383);

        Subject subjectOPNP = new Subject();
        subjectOPNP.setShortSubjectTitle("ОПНП");

        Subject subjectNoSQL = new Subject();
        subjectNoSQL.setShortSubjectTitle("NoSQL");

        Subject subjectBZD = new Subject();
        subjectBZD.setShortSubjectTitle("БЖД");

        Subject subjectCrypt = new Subject();
        subjectCrypt.setShortSubjectTitle("Криптография");

        List<String> dayOfWeek = new ArrayList<>(Arrays.asList("MON", "TUE", "WED", "THU", "FRI", "SAT"));
        List<String> time = new ArrayList<>(Arrays.asList("8:00-9:30", "9:50-11:20", "11:40-13:10",
                "13:40-15:10", "15:30-17:00", "17:20-18:50"));
        List<Group> groups = new ArrayList<>(Arrays.asList(group0381, group0382, group0383));

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
        Optional<Chain> chain1 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                "MON", "9:50-11:20", group0382.getGroupNumber());

        if (chain1.isPresent()) {
            Chain chain = chain1.get();
            chainRepository.delete(chain);
            chain.setClassroom("1234");
            chain.setSubject(subjectBZD);
            chainRepository.save(chain);
        }

        Optional<Chain> chain2 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                "MON", "11:40-13:10", group0381.getGroupNumber());

        if (chain2.isPresent()) {
            Chain chain = chain1.get();
            chainRepository.delete(chain);
            chain.setClassroom("1235");
            chain.setSubject(subjectCrypt);
            chainRepository.save(chain);
        }

        Optional<Chain> chain3 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                "MON", "11:40-13:10", group0383.getGroupNumber());

        if (chain3.isPresent()) {
            Chain chain = chain3.get();
            chainRepository.delete(chain);
            chain.setClassroom("1232");
            chain.setSubject(subjectOPNP);
            chainRepository.save(chain);
        }

        Optional<Chain> chain4 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                "MON", "13:40-15:10", group0383.getGroupNumber());

        if (chain4.isPresent()) {
            Chain chain = chain4.get();
            chainRepository.delete(chain);
            chain.setClassroom("5328");
            chain.setSubject(subjectNoSQL);
            chainRepository.save(chain);
        }
    }

}
