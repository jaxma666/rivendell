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
public class ClientManagerImpl implements IClientManager {
    private ConcurrentHashMap<String, ChannelHandlerContext> clientsMap;

    @PostConstruct
    void init() {
        clientsMap = new ConcurrentHashMap<>();
    }

    @Override
    public void addClient(String clientName, ChannelHandlerContext ctx) {
        clientsMap.put(clientName, ctx);
    }

    @Override
    public void removeClient(String clientName) {
        clientsMap.remove(clientName);
    }

    @Override
    public List getAllClientsName() {
        List<String> result = Lists.newLinkedList();
        result.addAll(clientsMap.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList()));
        return result;
    }

    @Override
    public List getAllClients() {
        List<ChannelHandlerContext> result = Lists.newLinkedList();
        result.addAll(clientsMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()));
        return result;
    }

    @Override
    public ChannelHandlerContext getClientsByName(String clientName) {
        return clientsMap.get(clientName);
    }
}
