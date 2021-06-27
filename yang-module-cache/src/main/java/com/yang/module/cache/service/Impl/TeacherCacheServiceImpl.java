package com.yang.module.cache.service.Impl;

import com.yang.module.cache.KeyMap;
import com.yang.module.cache.service.TeacherCacheService;
import com.yang.module.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TeacherCacheServiceImpl implements TeacherCacheService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void teacherListPush(Teacher teacher) {
        redisTemplate.opsForList().leftPush(KeyMap.REDIS_TEACHER_LIST, teacher);
    }
}
