package com.fantasy.rivendell.service.server.message;

import com.fantasy.rivendell.service.domain.SimpleProtocol;
import com.fantasy.rivendell.service.server.IPushManager;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by lingyao on 16/7/14.
 */
@Component("echoMessageHandlerImpl")
public class EchoMessageHandlerImpl implements IActionHandler {
    @Resource
    IPushManager pushManager;

    @Override
    public void handle(ChannelHandlerContext ctx, String content) {
        pushManager.pushToSingleClient(ctx, new SimpleProtocol(true, "echo", content));
    }
}
