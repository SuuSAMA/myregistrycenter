package com.theialeo.center.core;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.net.InetSocketAddress;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/21 18:33
 * @description
 */
public class RegistryHeartBeatHandler extends ChannelInboundHandlerAdapter {

    private Registry registry;

    public RegistryHeartBeatHandler(){
        registry = Registry.getInstance();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;

        if (event.state() == IdleState.READER_IDLE){
            System.out.println("reader idle");
        } else if (event.state() == IdleState.WRITER_IDLE){
            System.out.println("writer idle");
        } else if (event.state() == IdleState.ALL_IDLE){
            InetSocketAddress inetSocketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
            String serverIp = inetSocketAddress.getHostString();
            int port = inetSocketAddress.getPort();
            String serverAddr = RpcStruct.concatenation(serverIp, port);

            String serverName = registry.getServerName(serverAddr);
            if (serverName != null){
                registry.cancel(serverName, serverAddr);
            }
            System.out.println(serverAddr + " canceled");

            Channel channel = ctx.channel();
            channel.close();
        }
    }

}
