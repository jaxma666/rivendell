package com.fantasy.rivendell.service.server.message;

import com.fantasy.rivendell.service.domain.SimpleProtocol;
import com.fantasy.rivendell.service.util.ResultFormatUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

/**
 * Created by lingyao on 16/7/14.
 */
@Component("echoMessageHandlerImpl")
public class EchoMessageHandlerImpl implements IActionHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, String content, SimpleProtocol outMessage) {
        ctx.writeAndFlush(ResultFormatUtil.formatResult(outMessage));
    }
}
