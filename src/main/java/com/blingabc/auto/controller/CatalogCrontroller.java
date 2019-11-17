package com.blingabc.auto.controller;

import com.blingabc.auto.beans.CatalogVO;
import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.dao.CatalogVOMapper;
import com.blingabc.auto.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/catalog")
@RestController
public class CatalogCrontroller {

    @Autowired
    private ICatalogService catalogService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResultBody getCatalogListCrontroller(String type){
        return ResultBody.success(catalogService.queryCatalogListService(type));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResultBody updateCatalogCrontroller(CatalogVO catalogVO){
        return catalogService.updateCatalogService(catalogVO);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultBody addCatalogCrontroller(CatalogVO catalogVO){
        return catalogService.updateCatalogService(catalogVO);
    }

    @RequestMapping(value = "{catalogId}", method = RequestMethod.DELETE)
    public ResultBody delCatalogCrontroller(@PathVariable("catalogId") int catalogId){
        return catalogService.delCatalogService(catalogId);
    }
}
