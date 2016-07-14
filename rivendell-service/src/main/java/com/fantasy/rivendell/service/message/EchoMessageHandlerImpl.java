package com.fantasy.rivendell.service.message;

import com.alibaba.fastjson.JSON;
import com.fantasy.rivendell.service.domain.SimpleProtocol;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

/**
 * Created by lingyao on 16/7/14.
 */
@Service("echoMessageHandlerImpl")
public class EchoMessageHandlerImpl implements IMessageHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, String content, SimpleProtocol outMessage) {
        ctx.writeAndFlush(JSON.toJSONString(outMessage));
    }
}
