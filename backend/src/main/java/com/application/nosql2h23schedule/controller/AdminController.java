package com.application.nosql2h23schedule.controller;

import com.application.nosql2h23schedule.dto.ChainDto;
import com.application.nosql2h23schedule.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/schedule/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/getSchedule")
    public ResponseEntity<List<ChainDto>> getSchedule(@RequestParam("faculty") String faculty,
                                                      @RequestParam("course") int course) {
        return ResponseEntity.ok(adminService.getSchedule(faculty, course));
    }
}
