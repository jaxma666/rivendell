package com.fantasy.rivendell.service.server;

import com.fantasy.rivendell.service.util.ResultFormatUtil;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lingyao on 16/7/19.
 */
@Component("pushManagerImpl")
public class PushManagerImpl implements IPushManager {
    private static final Logger logger = LoggerFactory.getLogger(PushManagerImpl.class);

    @Resource
    IClientManager clientManager;

    @Override
    public void pushToSingleClient(String clientName, Object protocol) {
        ChannelHandlerContext ctx = clientManager.getClientsByName(clientName);
        if (ctx == null) {
            logger.error("try to send message to a client who is nonexistent");
            return;
        }
        pushToSingleClient(ctx, protocol);
    }

    @Override
    public void pushToSingleClient(ChannelHandlerContext ctx, Object protocol) {
        ctx.writeAndFlush(ResultFormatUtil.formatResult(protocol));
    }

    @Override
    public void pushToClientList(List<String> clientNameList, Object protocol) {
        for (String each : clientNameList) {
            ChannelHandlerContext eachCtx = clientManager.getClientsByName(each);
            if (eachCtx != null) {
                pushToSingleClient(eachCtx, protocol);
            }
        }
    }

    @Override
    public void broadcast(Object protocol) {
        List ctxList = clientManager.getAllClients();
        for (Object each : ctxList) {
            pushToSingleClient((ChannelHandlerContext) each, protocol);
        }
    }
}
