package com.theialeo.center.core;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;

import java.net.InetSocketAddress;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/21 19:43
 * @description
 */
@Getter
public class RpcStruct {
    private MethodType methodType;
    private String serverName;
    private String serverAddr;
    private String serverIp;
    private int serverPort;

    public static RpcStruct init(String content, ChannelHandlerContext ctx){
        String[] struct = content.split(CustomConstant.LINEFEED);
        String first = struct[0];
        int spaceIndex = first.indexOf(CustomConstant.SPACE);
        String type = first.substring(0, spaceIndex);
        String serverName = first.substring(spaceIndex+1);
        MethodType methodType = MethodType.getType(type);

        InetSocketAddress inetSocketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
        String serverIp = inetSocketAddress.getHostString();
        int port = inetSocketAddress.getPort();
        String serverAddr = concatenation(serverIp, port);

        RpcStruct rpcStruct = new RpcStruct();
        rpcStruct.methodType = methodType;
        rpcStruct.serverName = serverName;
        rpcStruct.serverIp = serverIp;
        rpcStruct.serverPort = port;
        rpcStruct.serverAddr = serverAddr;
        return rpcStruct;
    }

    public static String concatenation(String ip, int port){
        return ip + ":" + port;
    }

}
