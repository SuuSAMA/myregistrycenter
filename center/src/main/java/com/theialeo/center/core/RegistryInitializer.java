package com.theialeo.center.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/20 17:24
 * @description
 */
public class RegistryInitializer extends ChannelInitializer<SocketChannel> {
    private static final int[] IDLE_TIME_SECONDS = new int[]{30, 40, 50};

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LineBasedFrameDecoder(1024));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new RegistryHandler());

        pipeline.addLast(new IdleStateHandler(IDLE_TIME_SECONDS[0],
                IDLE_TIME_SECONDS[1],
                IDLE_TIME_SECONDS[2]));

        pipeline.addLast(new RegistryHeartBeatHandler());
    }

}
