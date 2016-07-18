package com.fantasy.rivendell.service.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * Created by lingyao on 16/7/17.
 */
public class RivendellClientInitailizer extends ChannelInitializer<SocketChannel> {
    private StringEncoder encoder = new StringEncoder(Charset.forName("UTF-8"));
    private StringDecoder decoder = new StringDecoder(Charset.forName("UTF-8"));
    private RivendellClientHandler rivendellClientHandler = new RivendellClientHandler();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                .addLast(encoder)
                .addLast(decoder)
                .addLast(rivendellClientHandler);
    }
}
