package com.theialeo.client1.core;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class RegistryClient {

    private Bootstrap bootstrap;
    private EventLoopGroup workGroup;
    private ChannelFuture channelFuture;

    public RegistryClient() {
        workGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new RegistryClientInitializer());

    }

    public void start(){
        channelFuture = bootstrap.connect("127.0.0.1", 8888);
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }
}