package com.application.nosql2h23schedule.repository;

import com.application.nosql2h23schedule.domain.Chain;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChainRepository extends MongoRepository<Chain, ObjectId> {
    List<Chain> findAll();
    List<Chain> findAllByFacultyAndGroup_Course(String faculty, int course);
    Chain findChainByWeekDayAndTimeAndGroup_GroupNumber(String weekDay, String time, String groupNumber);
}
