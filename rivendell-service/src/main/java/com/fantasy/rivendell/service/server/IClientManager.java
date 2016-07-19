package com.fantasy.rivendell.service.server;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * Created by lingyao on 16/7/16.
 */

//对于长连接的管理,方便服务端推送数据
public interface IClientManager {
    void addClient(String clientName, ChannelHandlerContext ctx);

    void removeClient(String clientName);

    List getAllClientsName();

    List getAllClients();

    ChannelHandlerContext getClientsByName(String clientName);
}
