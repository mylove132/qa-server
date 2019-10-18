//package com.okjiaoyu.auto.controller;
//
///**
// * @Author: liuzhanhui
// * @Decription:
// * @Date: Created in 2019-09-19:10:40
// * Modify date: 2019-09-19:10:40
// */
//import com.okjiaoyu.auto.util.RedisUtil;
//import com.okjiaoyu.auto.vo.UserEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.Date;
//
///**
// * @program: springbootdemo
// * @Date: 2019/2/22 15:03
// * @Author: zjjlive
// * @Description:
// */
//@Slf4j
//@RequestMapping("/redis")
//@RestController
//public class RedisController {
//
//    private static int ExpireTime = 60;   // redis中存储的过期时间60s
//
//    @Resource
//    private RedisUtil redisUtil;
//
//    @RequestMapping("set")
//    public boolean redisset(String key, String value){
//        UserEntity userEntity =new UserEntity();
//        userEntity.setId(1);
//        userEntity.setName("zhangsan");
//        userEntity.setCreateTime(new Date());
//        //return redisUtil.set(key,userEntity,ExpireTime);
//        return redisUtil.set(key,value,ExpireTime);
//    }
//
//    @RequestMapping("setList")
//    public boolean redissetList(String key){
//        UserEntity userEntity =new UserEntity();
//        userEntity.setId(1);
//        userEntity.setName("zhangsan");
//        userEntity.setCreateTime(new Date());
//        //return redisUtil.set(key,userEntity,ExpireTime);
//        return redisUtil.set(key,userEntity,ExpireTime);
//    }
//
//    @RequestMapping("get")
//    public Object redisget(String key){
//        return redisUtil.get(key);
//    }
//
//    @RequestMapping("expire")
//    public boolean expire(String key){
//        return redisUtil.expire(key,ExpireTime);
//    }
//}
