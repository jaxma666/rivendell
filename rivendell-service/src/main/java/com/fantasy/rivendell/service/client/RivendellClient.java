package com.fantasy.rivendell.service.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by lingyao on 16/7/15.
 */
public class RivendellClient implements Runnable {
    static final String HOST = "127.0.0.1";
    static final int PORT = 8888;

    public static void main(String[] args) throws Exception {
        new RivendellClient().connect();
    }

    public void connect() {
        for (int i = 0; i < 1; i++)
            new Thread(this).start();
    }

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup(1);
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new RivendellClientInitailizer());
            Channel channel = b.connect(HOST, PORT).sync().channel();
            System.out.println("client start!");
//          todo 客户端线程管理,主动向服务端推数据

//            这一段用来测试协议
//            ChannelFuture lastWriteFuture = null;
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

//            while (true) {
//                String line = in.readLine();
//                if (line == null) {
//                    break;
//                }
//                // Sends the received line to the server.
//                lastWriteFuture = channel.writeAndFlush(line + "\r\n");
//            }
//            // Wait until all messages are flushed before closing the channel.
//            if (lastWriteFuture != null) {
//                lastWriteFuture.sync();
//            }
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
