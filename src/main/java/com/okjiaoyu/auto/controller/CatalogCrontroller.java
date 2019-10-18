package com.okjiaoyu.auto.controller;

import com.okjiaoyu.auto.annotion.Operation;
import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.SuccessResponse;
import com.okjiaoyu.auto.service.ICatalogService;
import com.okjiaoyu.auto.vo.CatalogEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BaseResponse catalogListCrontroller(){
        return new SuccessResponse(catalogService.catalogListService());
    }

    @Operation("添加目录")
    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse addCatalogCrontroller(CatalogEntity catalogEntity){
        return catalogService.addCatalogService(catalogEntity);
    }

    @Operation("修改目录")
    @RequestMapping(method = RequestMethod.PUT)
    public BaseResponse updateCatalogCrontroller(CatalogEntity catalogEntity){
        return catalogService.updateCatalogService(catalogEntity);
    }

    @Operation("删除目录")
    @RequestMapping(method = RequestMethod.DELETE)
    public BaseResponse delIdCatalogCrontroller(int catalogId){
        return catalogService.delCatalogService(catalogId);
    }
}
