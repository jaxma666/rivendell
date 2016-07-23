package com.fantasy.rivendell.service.client;

import com.fantasy.rivendell.service.domain.SimpleProtocol;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by lingyao on 16/7/15.
 */
public class RivendellClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server:" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                System.out.println(ctx.channel().id().asLongText() + "is READER_IDLE,try to reconnect in 10 secodes");
                ChannelFuture closeFuture = ctx.close();
                closeFuture.addListener((ChannelFutureListener) future -> {
                    //线程休眠一段时间后再重连
                    Thread.sleep(10 * 1000);
                    System.out.println("开始重连!");
                    new RivendellClient().connect();
                });
            } else if (e.state() == IdleState.WRITER_IDLE) {
                CommitUtil.commitToServer(ctx, new SimpleProtocol(true, "HEART_BEAT", null));
            } else if (e.state() == IdleState.ALL_IDLE) {
                System.out.println(ctx.channel().id().asLongText() + "is ALL_IDLE");
            }
        }
    }
}
