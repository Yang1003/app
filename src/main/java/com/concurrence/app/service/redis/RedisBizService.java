package com.concurrence.app.service.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

import jakarta.annotation.Resource;

/**
 * .
 *
 * @author wangjiayang
 * @Email wangjiayang@fiberhome.com
 * @date 2025/4/24 19:22
 */
@Service
public class RedisBizService {

    private static final Logger log = LoggerFactory.getLogger(RedisBizService.class);

    @Resource
    private RedissonClient redissonClient;

    public String redissonTest(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            lock.lock();
            Thread.sleep(10000);
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        return "已解锁";
    }


    private int count = 0;

    public void redissonTest2() {

        CountDownLatch countDownLatch = new CountDownLatch(1000);

        for (int i = 0; i < 1000; i++) {

            new Thread(() -> {

                // 每个线程都创建自己的锁对象
                // 这是基于 Redis 实现的分布式锁
                RLock lock = this.redissonClient.getLock("counterLock");

                try {
                    // 上锁
                    lock.lock();

                    // 计数器自增 1
                    this.count = this.count + 1;

                } finally {
                    // 释放锁
                    lock.unlock();
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("count = {}", this.count);
    }


}
