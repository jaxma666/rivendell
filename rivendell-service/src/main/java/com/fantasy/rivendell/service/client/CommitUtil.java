package com.fantasy.rivendell.service.client;

import com.fantasy.rivendell.service.util.ResultFormatUtil;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by lingyao on 16/7/21.
 */
public class CommitUtil {
    static void commitToServer(ChannelHandlerContext ctx, Object protocol) {
        ctx.writeAndFlush(ResultFormatUtil.formatResult(protocol));
    }
}
