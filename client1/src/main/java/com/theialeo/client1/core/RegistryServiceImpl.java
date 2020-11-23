package com.theialeo.client1.core;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/20 20:59
 * @description
 */
@Service
public class RegistryServiceImpl<E> implements RegisterService<E> {

    @Autowired
    private RegistryClient registryClient;

    @Value("${spring.application.name}")
    private String serverName;

    @Value("${proparm.waitTime}")
    private String waitTime;

    @Override
    public void send(E element){
        String msg = JSONObject.toJSONString(element);
        registryClient.getChannelFuture().channel().writeAndFlush(msg);
    }

    @Override
    public String queryServerAddrs() {
        Channel channel = registryClient.getChannelFuture().channel();
        channel.writeAndFlush(MethodType.QUERY_SERVER
                .getContent() + CustomConstant.SPACE + serverName + CustomConstant.LINEFEED);
        return null;
    }

}
