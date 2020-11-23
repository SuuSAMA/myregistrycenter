package com.theialeo.client1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/23 13:37
 * @description
 */
@RestController
public class SayHelloController {
    @PostMapping("/sayHello")
    public String sayHello(){
        return "success";
    }
}
