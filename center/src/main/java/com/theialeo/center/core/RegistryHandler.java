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

        switch (rpcStruct.getMethodType()){
            case REGISTER:
                Server server = new Server();
                server.setServerIp(rpcStruct.getServerIp());
                server.setServerPort(rpcStruct.getServerPort());
                server.setServerAddr(rpcStruct.getServerAddr());
                ctx.channel().writeAndFlush(MethodType.REGISTER
                        .getContent() + CustomConstant.SPACE + "ok" + CustomConstant.LINEFEED);
                registry.register(rpcStruct.getServerName(), server);
                System.out.println(rpcStruct.getServerAddr() + " register to registry center.");
                break;
            case KEEP_ALIVE:
                System.out.println(rpcStruct.getServerAddr() + " keep alive");
                ctx.channel().writeAndFlush(MethodType.KEEP_ALIVE
                        .getContent() + CustomConstant.SPACE + "ok" + CustomConstant.LINEFEED);
                break;
            case QUERY_SERVER:
                System.out.println(rpcStruct.getServerAddr() + " query server");
                String serverStr = registry.obtainServerAddrs(rpcStruct.getServerName());
                ctx.channel().writeAndFlush(MethodType.QUERY_SERVER
                        .getContent() + CustomConstant.SPACE + serverStr + CustomConstant.LINEFEED);
                break;
            default:
        }
    }

}
