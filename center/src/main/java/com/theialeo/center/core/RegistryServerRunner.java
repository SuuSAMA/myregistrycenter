package com.theialeo.center.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/21 16:09
 * @description
 */
@Component
public class RegistryServerRunner implements ApplicationRunner {
    @Autowired
    private RegistryServer registryServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        registryServer.start();
    }
}
