package com.theialeo.center.core;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/20 21:25
 * @description
 */
public class Registry implements Registrable{

    private Map<String, ServerGroup> serverDefinitionMap;

    public Registry(){
        serverDefinitionMap = new HashMap<>();
    }

    @Override
    public synchronized void register(String serverName, Server server) {
        boolean islife = serverDefinitionMap.containsKey(serverName);
        ServerGroup serverGroup = null;
        if (!islife){
            serverGroup = new ServerGroup(serverName);
            serverDefinitionMap.put(serverName, serverGroup);
        } else {
            serverGroup = serverDefinitionMap.get(serverName);
        }
        serverGroup.addServer(server);
    }

    @Override
    public synchronized void cancel(String serverName, String serverAddr) {
        ServerGroup serverGroup = serverDefinitionMap.get(serverName);
        serverGroup.removeServer(serverAddr);
        if (serverGroup.size() == 0){
            serverDefinitionMap.remove(serverName);
        }
    }

    @Override
    public synchronized String getServerName(String serverAddr) {
        Collection<ServerGroup> values = serverDefinitionMap.values();

        for (ServerGroup serverGroup : values) {
            if (serverGroup.contains(serverAddr)){
                return serverGroup.getServerName();
            }
        }

        return null;
    }

    @Override
    public String obtainServerAddr(String serverName) {
        ServerGroup serverGroup = serverDefinitionMap.get(serverName);
        return serverGroup.pollingServer().getServerAddr();
    }

    private static class SingleRegistry{
        private static Registry INSTANCE;
    }

    public static Registry getInstance(){
        if (SingleRegistry.INSTANCE == null){
            synchronized (SingleRegistry.class){
                if (SingleRegistry.INSTANCE == null){
                    SingleRegistry.INSTANCE = new Registry();
                }
            }
        }
        return SingleRegistry.INSTANCE;
    }
}
