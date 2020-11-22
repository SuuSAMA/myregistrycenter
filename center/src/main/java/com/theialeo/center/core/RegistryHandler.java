package com.theialeo.center.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/20 17:23
 * @description
 */
public class RegistryHandler extends SimpleChannelInboundHandler<String> {

    private Registry registry;

    public RegistryHandler(){
        registry = Registry.getInstance();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,String msg) throws Exception {
        RpcStruct rpcStruct = RpcStruct.init(msg, ctx);

        Server server = new Server();
        server.setServerIp(rpcStruct.getServerIp());
        server.setServerPort(rpcStruct.getServerPort());
        server.setServerAddr(rpcStruct.getServerAddr());

        registry.register(rpcStruct.getServerName(), server);
        System.out.println(rpcStruct.getServerAddr() + " register to registry center.");
    }

}
