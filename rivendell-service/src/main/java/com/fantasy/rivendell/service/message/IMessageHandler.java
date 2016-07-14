package com.fantasy.rivendell.service.message;

import com.fantasy.rivendell.service.domain.SimpleProtocol;
import io.netty.channel.ChannelHandlerContext;


/**
 * Created by lingyao on 16/5/17.
 */
public interface IMessageHandler {
    void handle(ChannelHandlerContext ctx, String content, SimpleProtocol outMessage);
}
