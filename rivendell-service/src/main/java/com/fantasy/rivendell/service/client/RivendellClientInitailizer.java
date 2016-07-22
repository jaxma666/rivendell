package com.fantasy.rivendell.service.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.Charset;

/**
 * Created by lingyao on 16/7/17.
 */
public class RivendellClientInitailizer extends ChannelInitializer<SocketChannel> {
    private StringEncoder encoder = new StringEncoder(Charset.forName("UTF-8"));
    private StringDecoder decoder = new StringDecoder(Charset.forName("UTF-8"));

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                .addLast(encoder)
                .addLast(decoder)
                //每10秒发送心跳包,25秒是没有收到读消息,相当于有两次机会去看链接是否存在
                //如果都没成功,则在写操作里边关掉本链接,并且5秒后尝试重连
                .addLast(new IdleStateHandler(25, 10, 0))
                .addLast(new RivendellClientHandler());
    }
}
