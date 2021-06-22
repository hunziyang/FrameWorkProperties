package com.yang.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.module.dao.mapper.TeacherMapper;
import com.yang.module.entity.Teacher;
import com.yang.module.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * (Teacher)表服务实现类
 *
 * @author makejava
 * @since 2020-11-01 09:49:08
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
}