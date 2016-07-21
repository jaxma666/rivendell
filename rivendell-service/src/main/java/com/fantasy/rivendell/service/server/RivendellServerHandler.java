package com.fantasy.rivendell.service.server;

import com.alibaba.fastjson.JSON;
import com.fantasy.rivendell.service.domain.SimpleProtocol;
import com.fantasy.rivendell.service.server.message.ActionHandlerFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by lingyao on 16/7/13.
 */
@Component("rivendellServerHandler")
@ChannelHandler.Sharable
public class RivendellServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger logger = LoggerFactory.getLogger(RivendellServerHandler.class);
    @Resource
    ActionHandlerFactory messageHandlerFactory;
    @Resource
    AsynExecutorManager asynExecutorManager;
    @Resource
    IClientManager clientManager;
    @Resource
    IPushManager pushManager;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.error(ctx.channel().id().asLongText() + "is connected...");
        clientManager.addClient(ctx.channel().id().asLongText(), ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.error(ctx.channel().id().asLongText() + "is disconnected...");
        clientManager.removeClient(ctx.channel().id().asLongText());
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //协议的解析
        SimpleProtocol inMessage = null;
        try {
            inMessage = JSON.parseObject(msg, SimpleProtocol.class);
        } catch (Exception e) {
            pushManager.pushToSingleClient(ctx, new SimpleProtocol(false, "protocol_error", "协议解析错误"));
        }
        //异步执行业务,不阻塞io线程
        if (inMessage != null) {
            SimpleProtocol finalInMessage = inMessage;
            asynExecutorManager.execute(() -> messageHandlerFactory.getMessageHandler(finalInMessage.getAction())
                    .handle(ctx, finalInMessage.getContent()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error(ctx.channel().id().asLongText() + "is disconnected", cause);
        clientManager.removeClient(ctx.channel().id().asLongText());
        ctx.close();
    }
}
