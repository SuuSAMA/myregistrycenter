package com.theialeo.client1.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 17机制Theia
 * @copyright 神农大学生软件创新中心版权所有 @
 * @email leonardozou@foxmail.com
 * @date 2020/11/21 19:26
 * @description
 */
@AllArgsConstructor
@Getter
public enum MethodType {
    // 注册服务
    REGISTER("REGISTER"),
    KEEP_ALIVE("KEEP_ALIVE");

    private String content;

    public MethodType getType(String content){
        MethodType[] methodTypes = MethodType.values();
        for (MethodType methodType: methodTypes){
            if (methodType.getContent().equals(content)){
                return methodType;
            }
        }
        throw new IllegalArgumentException("method type is invalid.");
    }
}
