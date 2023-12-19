package com.application.nosql2h23schedule.controller;

import com.application.nosql2h23schedule.domain.Chain;
import com.application.nosql2h23schedule.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> getSchedule(@RequestParam("faculty") String faculty,
                                                           @RequestParam("course") int course) {
        return ResponseEntity.ok(adminService.getSchedule(faculty, course));
    }

//    example to deal with ObjectId
    @GetMapping("/getChain")
    public ResponseEntity<Chain> getSchedule(@RequestParam("id") String id) {
        return ResponseEntity.ok(adminService.getChain(id));
    }
}
