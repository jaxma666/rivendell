package com.fantasy.rivendell.service.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import javax.annotation.Resource;

/**
 * Created by lingyao on 16/7/17.
 */
public class RivendellServerInitializer extends ChannelInitializer<SocketChannel> {
    @Resource
    private RivendellServerHandler rivendellServerHandler;

    private StringEncoder ENCODER = new StringEncoder();
    private StringDecoder DECODER = new StringDecoder();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("delimiter", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                .addLast(ENCODER)
                .addLast(DECODER)
                .addLast(rivendellServerHandler);
    }
}
