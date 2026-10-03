package com.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.GoogleSheetService;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private GoogleSheetService googleSheetService;

    @GetMapping("/students")
    public List<Student> getStudents() throws Exception {

        return googleSheetService.getStudents();
    }
}