package com.theialeo.client1.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/20 20:55
 * @description
 */
@Configuration
public class RegistryConfiguration {

    @Bean
    public RegistryClient registryClient(){
        return new RegistryClient();

    }

}
