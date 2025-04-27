package com.concurrence.app.controller.redis;


import com.concurrence.app.service.redis.RedisBizService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;


/**
 * Hello World
 * @author ZNZZ-2
 */

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisBizService redisBizService;

    @GetMapping(value = "/redisson/{key}")
    public String redissonTest(@PathVariable("key") String lockKey) {
        return redisBizService.redissonTest(lockKey);
    }

    @GetMapping(value = "/redissonTest2")
    public void redissonTest2() {
        redisBizService.redissonTest2();
    }

}
