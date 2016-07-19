package com.fantasy.rivendell.service.server.message;

import io.netty.channel.ChannelHandlerContext;


/**
 * Created by lingyao on 16/5/17.
 */
public interface IActionHandler {
    void handle(ChannelHandlerContext ctx, String content);
}
