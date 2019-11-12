package com.blingabc.auto.controller;

import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.exception.BizException;
import com.blingabc.auto.service.impl.WebServiceImpl;
import com.blingabc.auto.util.TestNGListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/web")
public class WebCaseCrontroller {

    @RequestMapping("run")
    public ResultBody execWebCaseCrontroller(){
        try {
            TestNG testNG = new TestNG();
            List listeners = new ArrayList<>();
            listeners.add(TestNGListener.class);
            testNG.setListenerClasses(listeners);
            testNG.setTestClasses(new Class[]{WebServiceImpl.class});
            testNG.run();
        }catch (BizException e){
            throw new BizException(e.getErrorMsg());
        }

        return ResultBody.success();
    }
}
