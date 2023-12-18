package com.application.nosql2h23schedule.service;

import com.application.nosql2h23schedule.domain.Chain;
import com.application.nosql2h23schedule.dto.ChainDto;
import com.application.nosql2h23schedule.repository.ChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class AdminService {

    private final ChainRepository chainRepository;

    @Autowired
    public AdminService(ChainRepository chainRepository) {
        this.chainRepository = chainRepository;
    }

    public List<ChainDto> getSchedule(String faculty, int course) {

        List<Chain> chains = chainRepository.findAllByFacultyAndGroup_Course(faculty, course);
        if (chains.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Ячейки расписания по данному запросу не найдены.");
        }

        List<ChainDto> chainDtos = new java.util.ArrayList<>(Collections.emptyList());

        chains.forEach(chain -> chainDtos.add(new ChainDto(chain.getId(), chain.getWeekDay(),
                chain.getTime(), chain.getGroup().getGroupNumber(), chain.getFlowGroups(), chain.getClassroom(),
                chain.getTeacher(), chain.getSubject().getShortSubjectTitle())));

        return chainDtos;
    }
}
