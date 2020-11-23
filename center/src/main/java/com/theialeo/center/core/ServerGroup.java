package com.theialeo.center.core;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/20 17:37
 * @description
 */
public class ServerGroup {
    private static final int DEFAULT_CAPACITY = 16;

    private String serverName;
    private HashMap<String, Server> servers;
    private List<String> serverAddrs;
    private int nowIndex;

    public ServerGroup(String serverName){
        this.serverName = serverName;
        this.servers = new HashMap<>();
        serverAddrs = new ArrayList<>(DEFAULT_CAPACITY);
        this.nowIndex = 0;
    }

    public String getServerName() {
        return serverName;
    }

    public Server pollingServer(){
        if (nowIndex >= servers.size()){
            nowIndex = 0;
        }
        String serverAddr = serverAddrs.get(nowIndex);
        nowIndex++;
        return servers.get(serverAddr);
    }

    public String getServerAddrs(){
        return JSONArray.toJSONString(serverAddrs);
    }

    public void addServer(Server server){
        String serverAddr = server.getServerAddr();

        servers.put(serverAddr, server);
        serverAddrs.add(serverAddr);
    }

    public void removeServer(String serverAddr){
        servers.remove(serverAddr);
        serverAddrs.remove(serverAddr);
    }

    public int size(){
        return servers.size();
    }

    public boolean contains(String serverAddr){
        return serverAddrs.contains(serverAddr);
    }


}
