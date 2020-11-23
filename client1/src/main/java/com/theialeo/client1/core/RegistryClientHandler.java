package com.theialeo.client1.core;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/23 13:00
 * @description
 */
public class RegistryClientHandler extends SimpleChannelInboundHandler<String> {
    @Value("${spring.application.name}")
    private String serverName;

    @Value("${proparm.waitTime}")
    private int waitTime;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channel.writeAndFlush(MethodType.REGISTER + CustomConstant
                .SPACE + serverName + CustomConstant.LINEFEED);
        System.out.println("Success, connected to 127.0.0.1:8888.");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
