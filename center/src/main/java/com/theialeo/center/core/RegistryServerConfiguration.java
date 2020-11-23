package com.theialeo.center.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/20 17:56
 * @description
 */
@Configuration
public class RegistryServerConfiguration {

    @Bean
    public RegistryServer registryServer(){
        RegistryServer registryServer = new RegistryServer();
        registryServer.start();
        return registryServer;
    }

}
