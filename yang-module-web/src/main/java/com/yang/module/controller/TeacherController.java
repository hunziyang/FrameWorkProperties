package com.yang.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yang.module.cache.service.TeacherCacheService;
import com.yang.module.common.Result;
import com.yang.module.common.ResultCode;
import com.yang.module.entity.Teacher;
import com.yang.module.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {
    /**
     * 服务对象
     */
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherCacheService teacherCacheService;

    @PostMapping("/insert")
    public Result insertByOne(@RequestBody Teacher teacher) {
        teacherCacheService.teacherListPush(teacher);
        boolean flag = teacherService.save(teacher);
        if (!flag) {
            log.error("teacher insert err");
            return Result.error(ResultCode.FAILED);
        }
        return Result.success(flag);
    }

    @PostMapping("/insertTwo")
    public Result insertByTwo(Teacher teacher) {
        boolean flag = teacherService.insert(teacher);
        if (!flag) {
            log.error("teacher insert err");
            return Result.error(ResultCode.FAILED);
        }
        return Result.success(flag);
    }

    /**
     * 因开启乐观锁，所以必须先查询再更新
     *
     * @param id
     * @return
     */
    @PostMapping("/update/{id}")
    public Result updateByOne(@PathVariable("id") Long id) {
        Teacher teacher = teacherService.getById(id);
        teacher.setAge(99);
        boolean flag = teacherService.updateById(teacher);
        if (!flag) {
            log.error("teacher insert err");
            return Result.error(ResultCode.FAILED);
        }
        return Result.success(flag);
    }

    @PostMapping("/updateTwo/{id}")
    public Result updateByTwo(@PathVariable("id") Long id) {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getId, id);
        Teacher teacher = teacherService.getOne(wrapper);
        teacher.setName("yang");
        boolean flag = teacherService.update(teacher, wrapper);
        if (!flag) {
            log.error("teacher insert err");
            return Result.error(ResultCode.FAILED);
        }
        return Result.success(flag);
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        boolean flag = teacherService.removeById(id);
        if (!flag) {
            log.error("teacher insert err");
            return Result.error(ResultCode.FAILED);
        }
        return Result.success(flag);
    }

}