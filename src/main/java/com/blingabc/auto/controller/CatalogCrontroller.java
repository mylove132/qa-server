package com.blingabc.auto.controller;

import com.blingabc.auto.annotion.AuthPermission;
import com.blingabc.auto.annotion.Operation;
import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.common.constant.PermissionConstant;
import com.blingabc.auto.service.ICatalogService;
import com.blingabc.auto.vo.CatalogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-10-08:16:54
 * Modify date: 2019-10-08:16:54
 */
@RequestMapping("/api/catalog")
@RestController
public class CatalogCrontroller {

    @Autowired
    private ICatalogService catalogService;

    @Operation("查询目录层级列表")
    @RequestMapping(method = RequestMethod.GET)
    public ResultBody catalogListCrontroller(){
        return ResultBody.success(catalogService.catalogListService());
    }

    @AuthPermission(PermissionConstant.VIP)
    @Operation("添加目录")
    @RequestMapping(method = RequestMethod.POST)
    public ResultBody addCatalogCrontroller(CatalogEntity catalogEntity){
        return catalogService.addCatalogService(catalogEntity);
    }

    @Operation("修改目录")
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBody updateCatalogCrontroller(CatalogEntity catalogEntity){
        return catalogService.updateCatalogService(catalogEntity);
    }

    @Operation("通过id查询项目目录")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResultBody getCatalogCatalogCrontroller(@PathVariable(value = "id") int catalogId){
        return catalogService.queryCatalogById(catalogId);
    }

    @Operation("删除目录")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResultBody delIdCatalogCrontroller(int catalogId){
        return catalogService.delCatalogService(catalogId);
    }
}
