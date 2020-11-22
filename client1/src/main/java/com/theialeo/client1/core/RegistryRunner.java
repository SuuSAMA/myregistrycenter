package com.theialeo.client1.core;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/21 16:11
 * @description
 */
@Component
public class RegistryRunner implements ApplicationRunner {
    private static final int DEFAULT_CONNECT_TIME = 3000;

    @Autowired
    private RegistryClient registryClient;

    @Value("${spring.application.name}")
    private String serverName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        registryClient.start();
        Channel channel = registryClient.getChannelFuture().channel();
        long startTimeStamp = System.currentTimeMillis();
        while (!channel.isActive()){
            Thread.sleep(200);
            if (System.currentTimeMillis() - startTimeStamp > DEFAULT_CONNECT_TIME){
                throw new RuntimeException("Connect to 127.0.0.1:8888 failed.");
            }
        }
        channel.writeAndFlush(MethodType.REGISTER + CustomConstant
                .SPACE + serverName + CustomConstant.LINEFEED);
        System.out.println("Success, connected to 127.0.0.1:8888.");
    }

}
