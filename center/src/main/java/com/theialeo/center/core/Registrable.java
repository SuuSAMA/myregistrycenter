package com.theialeo.center.core;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/20 17:02
 * @description
 */
public interface Registrable {

    void register(String serverName, Server server);

    void cancel(String serverName, String serverAddr);

    String getServerName(String serverAddr);

    String obtainServerAddr(String serverName);

}
