package com.fantasy.rivendell.service.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by lingyao on 16/5/17.
 */
public class MessageHandlerFactory {
    private static final Logger logger = LoggerFactory.getLogger(MessageHandlerFactory.class);

    private Map<String, IMessageHandler> messageHandlerMap;

    public Map<String, IMessageHandler> getMessageHandlerMap() {
        return messageHandlerMap;
    }

    public void setMessageHandlerMap(Map<String, IMessageHandler> messageHandlerMap) {
        this.messageHandlerMap = messageHandlerMap;
    }

    public IMessageHandler getMessageHandler(String type) {
        return messageHandlerMap.get(type);
    }
}
