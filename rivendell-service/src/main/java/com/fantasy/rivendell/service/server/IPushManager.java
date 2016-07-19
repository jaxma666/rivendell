package com.fantasy.rivendell.service.server;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * Created by lingyao on 16/7/19.
 */
public interface IPushManager {
    void pushToSingleClient(String clientName, Object protocol);

    void pushToSingleClient(ChannelHandlerContext ctx, Object protocol);

    void pushToClientList(List<String> clientNameList, Object protocol);

    void broadcast(Object protocol);
}
