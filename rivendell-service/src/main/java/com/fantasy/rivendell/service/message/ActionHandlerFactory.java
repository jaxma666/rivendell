package com.fantasy.rivendell.service.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by lingyao on 16/5/17.
 */
@Component
public class ActionHandlerFactory {
    private static final Logger logger = LoggerFactory.getLogger(ActionHandlerFactory.class);

    private Map<String, IActionHandler> messageHandlerMap;

    public Map<String, IActionHandler> getMessageHandlerMap() {
        return messageHandlerMap;
    }

    public void setMessageHandlerMap(Map<String, IActionHandler> messageHandlerMap) {
        this.messageHandlerMap = messageHandlerMap;
    }

    public IActionHandler getMessageHandler(String type) {
        return messageHandlerMap.get(type);
    }
}
