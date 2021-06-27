package com.yang.module.cache.util;

import com.yang.module.cache.KeyMap;
import com.yang.module.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * 通过redis实现工单号自增长
 */
@Component
public class RedisSequence {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 产生自增序列以时间划分
     * @return
     */
    public String getTicket() {
        return DateUtil.format(new Date(),"yyyy-MM-dd") + getSuffix(String.valueOf(getID()));
    }

    private String getSuffix(String id) {
        while (id.length() < 5) {
            id = "0" + id;
        }
        return id;
    }

    private long getID() {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(KeyMap.REDIS_INCREMENT, redisTemplate.getConnectionFactory());
        long increment = redisAtomicLong.getAndIncrement() + 1l;
        // 设置过期时间，redis自增默认从0开始
        if (increment == 1l) {
            redisAtomicLong.expireAt(getExpireTime());
        }
        return increment;
    }

    private Date getExpireTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }
}
