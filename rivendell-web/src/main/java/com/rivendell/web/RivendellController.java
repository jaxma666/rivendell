package com.rivendell.web;

/**
 * Created by lingyao on 16/7/18.
 */

import com.fantasy.rivendell.service.domain.ApiResult;
import com.fantasy.rivendell.service.domain.SimpleProtocol;
import com.fantasy.rivendell.service.server.IClientManager;
import com.fantasy.rivendell.service.server.IPushManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RivendellController {
    @Resource
    IClientManager clientManager;
    @Resource
    IPushManager pushManager;

    @RequestMapping("/getAllClientsName")
    @ResponseBody
    public ApiResult<List> getAllClientsName() {
        ApiResult<List> apiResult = new ApiResult<>();
        List clientNameList = clientManager.getAllClientsName();
        return apiResult.returnSuccessResult(clientNameList);
    }

    @RequestMapping("/getClientsNumber")
    @ResponseBody
    public ApiResult<Integer> getClientsNumber() {
        ApiResult<Integer> apiResult = new ApiResult<>();
        return apiResult.returnSuccessResult(clientManager.getClientsSize());
    }

    @RequestMapping("/pushToSingleClient")
    @ResponseBody
    public ApiResult<Boolean> pushToSingleClient(String clientName) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        pushManager.pushToSingleClient(clientName, new SimpleProtocol(true, "PUSH", "后台单点推送"));
        return apiResult.returnSuccessResult(true);
    }

    @RequestMapping("/broadcast")
    @ResponseBody
    public ApiResult<Boolean> broadcast() {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        pushManager.broadcast(new SimpleProtocol(true, "PUSH", "后台广播推送"));
        return apiResult.returnSuccessResult(true);
    }
}
