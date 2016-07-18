package com.fantasy.rivendell.service.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by lingyao on 16/7/13.
 */

@Service
public class RivendellServer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RivendellServer.class);
    private int PORT = 8888;

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new RivendellServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, false)
                    .childOption(ChannelOption.TCP_NODELAY, true);
            ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
            if (channelFuture.isSuccess()) {
                logger.error("rivendellServer has been started...");
            }
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @PostConstruct
    public void init() throws Exception {
        logger.error("start init rivendellServer...");
        new Thread(new RivendellServer()).start();
    }
}
