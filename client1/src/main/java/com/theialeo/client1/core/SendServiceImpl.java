package com.theialeo.client1.core;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/20 20:59
 * @description
 */
@Service
public class SendServiceImpl<E> implements SendService<E> {

    @Autowired
    private RegistryClient registryClient;

    @Override
    public void send(E element){
        String msg = JSONObject.toJSONString(element);
        registryClient.getChannelFuture().channel().writeAndFlush(msg);
    }

}
