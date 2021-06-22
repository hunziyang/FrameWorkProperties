package com.yang.module.controller;

import com.yang.module.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    /**
     * 服务对象
     */
    @Autowired
    private TeacherService teacherService;

}