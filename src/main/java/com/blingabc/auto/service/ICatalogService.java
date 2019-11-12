package com.blingabc.auto.service;

import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.exception.BizException;
import com.blingabc.auto.vo.CatalogEntity;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-10-08:16:33
 * Modify date: 2019-10-08:16:33
 */
public interface ICatalogService {

    List<CatalogEntity> catalogListService() throws BizException;

    ResultBody addCatalogService(CatalogEntity catalogEntity) throws BizException;

    ResultBody updateCatalogService(CatalogEntity catalogEntity) throws BizException;

    ResultBody delCatalogService(int catalogId) throws BizException;

    ResultBody delCatalogService(int[] ids) throws BizException;

    ResultBody queryCatalogById(int catalogId);
}
