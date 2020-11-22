package com.theialeo.client1.core;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.ScheduledFuture;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

public class KeepAliveHandler extends ChannelInboundHandlerAdapter {

    @Value("${spring.application.name}")
    private String serverName;

    private static final int TIME_INTERVAL  = 40;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ping(ctx.channel());
    }

    private void ping(Channel channel) {
        ScheduledFuture<?> future = channel.eventLoop().schedule(() -> {
            if (channel.isActive()) {
                System.out.println("sending heart beat to the server...");
                channel.writeAndFlush(MethodType.KEEP_ALIVE + CustomConstant
                        .SPACE + serverName + CustomConstant.LINEFEED);
            } else {
                System.err.println("The connection had broken, cancel the task that will send a heart beat.");
                channel.closeFuture();
                throw new RuntimeException();
            }
        }, TIME_INTERVAL, TimeUnit.SECONDS);

        future.addListener((future1) ->{
            if (future1.isSuccess()) {
                ping(channel);
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}