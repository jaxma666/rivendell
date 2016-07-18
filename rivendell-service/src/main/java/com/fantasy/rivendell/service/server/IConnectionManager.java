package com.fantasy.rivendell.service.server;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * Created by lingyao on 16/7/16.
 */

//对于长连接的管理,方便服务端推送数据
public interface IConnectionManager {
    void addConnection(String clientName, ChannelHandlerContext ctx);

    void removeConnection(String clientName);

    List getAllConnections();

    ChannelHandlerContext getConnectionsByName(String clientName);
}
