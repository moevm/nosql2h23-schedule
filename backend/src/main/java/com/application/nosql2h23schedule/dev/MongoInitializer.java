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
        initTestUsers();
        init4CourseFKTI();
        initGroups();
    }

    private void initGroups() {
        groupRepository.save(new Group(null, "1111", 0, 3, "ФРТ"));
        groupRepository.save(new Group(null, "1101", 0, 3, "ФРТ"));
        groupRepository.save(new Group(null, "1102", 0, 3, "ФРТ"));
        groupRepository.save(new Group(null, "1191", 0, 3, "ФРТ"));
        groupRepository.save(new Group(null, "1105", 0, 3, "ФРТ"));

        groupRepository.save(new Group(null, "1381", 0, 3, "ФКТИ"));
        groupRepository.save(new Group(null, "1382", 0, 3, "ФКТИ"));
        groupRepository.save(new Group(null, "1383", 0, 3, "ФКТИ"));
        groupRepository.save(new Group(null, "1303", 0, 3, "ФКТИ"));
        groupRepository.save(new Group(null, "1304", 0, 3, "ФКТИ"));
    }

    private void initTestUsers() {
        User user = new User();
        user.setEmail("ivanov@gmail.com");
        user.setPassword(passwordEncoder.encode("1"));
        user.setFullName("Иванов И.С.");
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);

        User user2 = new User();
        user2.setEmail("testuser");
        user2.setPassword(passwordEncoder.encode("1"));
        user2.setFullName("test test test");
        user2.setRole("ROLE_ADMIN");
        userRepository.save(user2);
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

        Subject subjectPRCMI = new Subject();
        subjectPRCMI.setShortSubjectTitle("лек. ПрЧМИ");

        Subject subjectPRCMIPract = new Subject();
        subjectPRCMIPract.setShortSubjectTitle("пр. ПрЧМИ");

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
            chain.setClassroom("5135");
            chain.setSubject(subjectBZDLab);
            chain.setTeacher("Борискина А.В.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, THIRD_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5423");
            chain.setSubject(subjectBZD);
            chain.setTeacher("Иванов А.Н.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FORTH_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5230");
            chain.setSubject(subjectNoSQL);
            chain.setTeacher("Заславский М.М.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FIFTH_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5230");
            chain.setSubject(subjectOPNP);
            chain.setTeacher("Заславский М.М.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, FIFTH_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3425");
            chain.setSubject(subjectTSOS);
            chain.setTeacher("Середа А.И.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, FIRST_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5427");
            chain.setSubject(subjectMarket);
            chain.setTeacher("Фомина И.Г.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, SECOND_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3423");
            chain.setSubject(subjectMarketPract);
            chain.setTeacher("Петрова А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, THIRD_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("1234");
            chain.setSubject(subjectCrypt);
            chain.setTeacher("Племянников А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, FORTH_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3129");
            chain.setSubject(subjectCryptPract);
            chain.setTeacher("Племянников А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                THURSDAY, THIRD_PAIR, group0382.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3234");
            chain.setSubject(subjectBZDPract);
            chain.setTeacher("Трусов А.О.");
            chainRepository.save(chain);
        }

//        0381 week schedule
        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, THIRD_PAIR, group0381.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5423");
            chain.setSubject(subjectBZD);
            chain.setTeacher("Иванов А.Н.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FORTH_PAIR, group0381.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5230");
            chain.setSubject(subjectNoSQL);
            chain.setTeacher("Заславский М.М.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FIFTH_PAIR, group0381.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5230");
            chain.setSubject(subjectOPNP);
            chain.setTeacher("Заславский М.М.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, FIFTH_PAIR, group0381.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3425");
            chain.setSubject(subjectTSOS);
            chain.setTeacher("Середа А.И.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, SECOND_PAIR, group0381.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5427");
            chain.setSubject(subjectMarket);
            chain.setTeacher("Фомина И.Г.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, THIRD_PAIR, group0381.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("1229");
            chain.setSubject(subjectCrypt);
            chain.setTeacher("Племянников А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, FORTH_PAIR, group0381.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5141");
            chain.setSubject(subjectBZDPract);
            chain.setTeacher("Трусов А.А.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, FIFTH_PAIR, group0381.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("2404");
            chain.setSubject(subjectMarketPract);
            chain.setTeacher("Петрова А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                THURSDAY, SECOND_PAIR, group0381.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5135");
            chain.setSubject(subjectBZDLab);
            chain.setTeacher("Маловский А.И.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                THURSDAY, THIRD_PAIR, group0381.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("2113");
            chain.setSubject(subjectCryptPract);
            chain.setTeacher("Племянников А.К.");
            chainRepository.save(chain);
        }

//        0383 week schedule
        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, THIRD_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5423");
            chain.setSubject(subjectBZD);
            chain.setTeacher("Иванов А.Н.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FORTH_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5230");
            chain.setSubject(subjectNoSQL);
            chain.setTeacher("Заславский М.М.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FIFTH_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5230");
            chain.setSubject(subjectOPNP);
            chain.setTeacher("Заславский М.М.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, SECOND_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5135");
            chain.setSubject(subjectBZDLab);
            chain.setTeacher("Демидович О.В.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, THIRD_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("2113");
            chain.setSubject(subjectCryptPract);
            chain.setTeacher("Племянников А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, FORTH_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("2412");
            chain.setSubject(subjectMarketPract);
            chain.setTeacher("Чешуина Е.Ю.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, FIFTH_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3425");
            chain.setSubject(subjectTSOS);
            chain.setTeacher("Середа А.И.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, SECOND_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5427");
            chain.setSubject(subjectMarket);
            chain.setTeacher("Фомина И.Г.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, THIRD_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("1229");
            chain.setSubject(subjectCrypt);
            chain.setTeacher("Племянников А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                THURSDAY, THIRD_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5141");
            chain.setSubject(subjectBZDPract);
            chain.setTeacher("Смирнова Н.В.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                THURSDAY, THIRD_PAIR, group0383.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5141");
            chain.setSubject(subjectBZDPract);
            chain.setTeacher("Смирнова Н.В.");
            chainRepository.save(chain);
        }

//        0303 week schedule
        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, THIRD_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5423");
            chain.setSubject(subjectBZD);
            chain.setTeacher("Иванов А.Н.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FORTH_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5230");
            chain.setSubject(subjectNoSQL);
            chain.setTeacher("Заславский М.М.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FIFTH_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5230");
            chain.setSubject(subjectOPNP);
            chain.setTeacher("Заславский М.М.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, SIXTH_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3410");
            chain.setSubject(subjectPRCMI);
            chain.setTeacher("Калишенко Е.Л.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, SECOND_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3410");
            chain.setSubject(subjectPRCMIPract);
            chain.setTeacher("Яцык А.А.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, THIRD_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("2113");
            chain.setSubject(subjectCryptPract);
            chain.setTeacher("Племянников А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, FORTH_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5135");
            chain.setSubject(subjectBZDLab);
            chain.setTeacher("Демидович О.В.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, FIFTH_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5134");
            chain.setSubject(subjectBZDPract);
            chain.setTeacher("Трусов А.А.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, SIXTH_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3425");
            chain.setSubject(subjectTSOS);
            chain.setTeacher("Середа А.И.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, SECOND_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5427");
            chain.setSubject(subjectMarket);
            chain.setTeacher("Фомина И.Г.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, THIRD_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("1229");
            chain.setSubject(subjectCrypt);
            chain.setTeacher("Племянников А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, FORTH_PAIR, group0303.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("2404");
            chain.setSubject(subjectMarketPract);
            chain.setTeacher("Петрова А.К.");
            chainRepository.save(chain);
        }

//        0303 week schedule
        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, THIRD_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5423");
            chain.setSubject(subjectBZD);
            chain.setTeacher("Иванов А.Н.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FORTH_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5230");
            chain.setSubject(subjectNoSQL);
            chain.setTeacher("Заславский М.М.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, FIFTH_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5230");
            chain.setSubject(subjectOPNP);
            chain.setTeacher("Заславский М.М.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                MONDAY, SIXTH_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3410");
            chain.setSubject(subjectPRCMI);
            chain.setTeacher("Калишенко Е.Л.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, FIFTH_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3410");
            chain.setSubject(subjectPRCMIPract);
            chain.setTeacher("Яцык А.А.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                TUESDAY, SIXTH_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("3425");
            chain.setSubject(subjectTSOS);
            chain.setTeacher("Середа А.И.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, SECOND_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5427");
            chain.setSubject(subjectMarket);
            chain.setTeacher("Фомина И.Г.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, THIRD_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("1229");
            chain.setSubject(subjectCrypt);
            chain.setTeacher("Племянников А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, FORTH_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5134");
            chain.setSubject(subjectBZDPract);
            chain.setTeacher("Смирнова Н.В.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                WEDNESDAY, FIFTH_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("2404");
            chain.setSubject(subjectMarketPract);
            chain.setTeacher("Петрова А.К.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                THURSDAY, SECOND_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("5135");
            chain.setSubject(subjectBZDLab);
            chain.setTeacher("Демидович О.В.");
            chainRepository.save(chain);
        }

        chainOptional = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                THURSDAY, THIRD_PAIR, group0304.getGroupNumber());
        if (chainOptional.isPresent()) {
            Chain chain = chainOptional.get();
            chainRepository.delete(chain);
            chain.setClassroom("2113");
            chain.setSubject(subjectCrypt);
            chain.setTeacher("Племянников А.К.");
            chainRepository.save(chain);
        }
    }
}