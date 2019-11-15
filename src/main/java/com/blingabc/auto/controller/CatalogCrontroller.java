package com.blingabc.auto.controller;

import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.dao.CatalogVOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/catalog")
@RestController
public class CatalogCrontroller {

    @Autowired
    private CatalogVOMapper catalogVOMapper;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResultBody getCatalogListCrontroller(){
        return ResultBody.success(catalogVOMapper.queryCatalogList());
    }
}
