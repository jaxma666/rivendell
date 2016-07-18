package com.fantasy.rivendell.service.server;

import com.google.common.collect.Lists;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by lingyao on 16/7/16.
 */
@Component
public class ConnectionManagerImpl implements IConnectionManager {
    private ConcurrentHashMap<String, ChannelHandlerContext> connectionsMap;

    @PostConstruct
    void init() {
        connectionsMap = new ConcurrentHashMap<>();
    }

    @Override
    public void addConnection(String clientName, ChannelHandlerContext ctx) {
        connectionsMap.put(clientName, ctx);
    }

    @Override
    public void removeConnection(String clientName) {
        connectionsMap.remove(clientName);
    }

    @Override
    public List getAllConnections() {
        List<String> result = Lists.newLinkedList();
        result.addAll(connectionsMap.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList()));
        return result;
    }

    @Override
    public ChannelHandlerContext getConnectionsByName(String clientName) {
        return connectionsMap.get(clientName);
    }
}
