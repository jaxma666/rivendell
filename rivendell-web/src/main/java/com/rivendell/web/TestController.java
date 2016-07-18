package com.rivendell.web;

/**
 * Created by lingyao on 16/7/18.
 */

import com.fantasy.rivendell.service.domain.ApiResult;
import com.fantasy.rivendell.service.server.IConnectionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TestController {
    @Resource
    IConnectionManager connectionManager;

    @RequestMapping("/test")
    @ResponseBody
    public ApiResult<List> test() {
        ApiResult<List> apiResult = new ApiResult<>();
        List connectionList = connectionManager.getAllConnections();
        apiResult.setSuccessResult(connectionList);
        return apiResult;
    }
}
