package com.application.nosql2h23schedule.dev;

import com.application.nosql2h23schedule.domain.Chain;
import com.application.nosql2h23schedule.domain.Group;
import com.application.nosql2h23schedule.domain.Subject;
import com.application.nosql2h23schedule.repository.ChainRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MongoInitializer {

    private final ChainRepository chainRepository;

    @Autowired
    public MongoInitializer(ChainRepository chainRepository) {
        this.chainRepository = chainRepository;
        init();
    }
    private void init() {

        Group group0382 = new Group();
        group0382.setId(ObjectId.get());
        group0382.setGroupNumber("0382");
        group0382.setAmount(25);
        group0382.setCourse(4);
        Group group0381 = new Group();
        group0381.setId(ObjectId.get());
        group0381.setGroupNumber("0381");
        group0381.setAmount(24);
        group0381.setCourse(4);
        Group group0383 = new Group();
        group0383.setId(ObjectId.get());
        group0383.setGroupNumber("0383");
        group0383.setAmount(19);
        group0383.setCourse(4);

        Subject subjectOPNP = new Subject();
        subjectOPNP.setId(ObjectId.get());
        subjectOPNP.setShortSubjectTitle("ОПНП");

        Subject subjectNoSQL = new Subject();
        subjectNoSQL.setId(ObjectId.get());
        subjectNoSQL.setShortSubjectTitle("NoSQL");

        Subject subjectBZD = new Subject();
        subjectBZD.setId(ObjectId.get());
        subjectBZD.setShortSubjectTitle("БЖД");

        Subject subjectCrypt = new Subject();
        subjectCrypt.setId(ObjectId.get());
        subjectCrypt.setShortSubjectTitle("Криптография");

        List<String> dayOfWeek = new ArrayList<>(Arrays.asList("MON", "TUE", "WEN", "THU"));
//        Уберем для сокращения количества сущностей
//        "FRI", "SAT"));
        List<String> time = new ArrayList<>(Arrays.asList("8:00-9:30", "9:50-11:20", "11:40-13:10",
                "13:40-15:10", "15:30-17:00"));
//        Уберем для сокращения количества сущностей
//                "17:20-18:50"));
        List<Group> groups = new ArrayList<>(Arrays.asList(group0381, group0382, group0383));

        dayOfWeek.forEach(day -> time.forEach(t -> groups.forEach(group ->
        {
            Chain chain = new Chain();
            chain.setWeekDay(day);
            chain.setTime(t);
            chain.setGroup(group);
            chainRepository.save(chain);
        }
        )));
        Chain chain1 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                "MON", "9:50-11:20", group0382.getGroupNumber());
        chainRepository.delete(chain1);
        chain1.setFaculty("ФКТИ");
        chain1.setClassroom("1234");
        chain1.setSubject(subjectBZD);
        chainRepository.save(chain1);

        Chain chain2 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                "MON", "11:40-13:10", group0381.getGroupNumber());
        chainRepository.delete(chain2);
        chain2.setFaculty("ФКТИ");
        chain2.setClassroom("1235");
        chain2.setSubject(subjectCrypt);
        chainRepository.save(chain2);

        Chain chain3 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                "MON", "11:40-13:10", group0383.getGroupNumber());
        chainRepository.delete(chain3);
        chain3.setFaculty("ФКТИ");
        chain3.setClassroom("1232");
        chain3.setSubject(subjectOPNP);
        chainRepository.save(chain3);

        Chain chain4 = chainRepository.findChainByWeekDayAndTimeAndGroup_GroupNumber(
                "MON", "13:40-15:10", group0383.getGroupNumber());
        chainRepository.delete(chain4);
        chain4.setFaculty("ФКТИ");
        chain4.setClassroom("5328");
        chain4.setSubject(subjectNoSQL);
        chainRepository.save(chain4);

    }

}
