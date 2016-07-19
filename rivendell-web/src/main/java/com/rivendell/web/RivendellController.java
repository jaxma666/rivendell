package com.rivendell.web;

/**
 * Created by lingyao on 16/7/18.
 */

import com.fantasy.rivendell.service.domain.ApiResult;
import com.fantasy.rivendell.service.server.IClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RivendellController {
    @Resource
    IClientManager clientManager;

    @RequestMapping("/getAllClientsName")
    @ResponseBody
    public ApiResult<List> getAllClientsName() {
        ApiResult<List> apiResult = new ApiResult<>();
        List clientNameList = clientManager.getAllClientsName();
        apiResult.setSuccessResult(clientNameList);
        return apiResult;
    }
}
