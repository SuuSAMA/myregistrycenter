package com.theialeo.center.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/20 17:06
 * @description
 */
public class RegistryServer {
    private EventLoopGroup parentGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public RegistryServer(){
        parentGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(parentGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new RegistryInitializer());
    }

    public void start(){
        this.future = server.bind(8888);
        System.out.println("Netty Registry server started, listen to 8888");
    }

}
