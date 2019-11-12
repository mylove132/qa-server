package com.blingabc.auto.controller;
/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-19:13:42
 * Modify date: 2019-09-19:13:42
 */

import com.blingabc.auto.annotion.AuthPermission;
import com.blingabc.auto.annotion.Operation;
import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.common.constant.PermissionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/case")
@RestController
public class CaseController {


    @AuthPermission(PermissionConstant.VIP)
    @Operation("添加case")
    @RequestMapping(method = RequestMethod.POST)
    public ResultBody addCaseCrontroller(){
        return null;
    }
}
