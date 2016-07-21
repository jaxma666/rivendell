package com.fantasy.rivendell.service.domain;

/**
 * Created by lingyao on 16/7/14.
 */

public class SimpleProtocol {
    private boolean success;
    private String action;
    private String content;

    public SimpleProtocol(boolean success, String action, String content) {
        this.success = success;
        this.action = action;
        this.content = content;
    }

    public SimpleProtocol() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public SimpleProtocol returnSuccess(String action, String content) {
        this.setAction(action);
        this.setContent(content);
        this.setSuccess(true);
        return this;
    }

    public SimpleProtocol returnError(String action, String content) {
        this.setAction(action);
        this.setContent(content);
        this.setSuccess(false);
        return this;
    }
}
