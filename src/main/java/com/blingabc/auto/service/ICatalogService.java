package com.blingabc.auto.service;

import com.blingabc.auto.beans.CatalogVO;
import com.blingabc.auto.common.ResultBody;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-11-16:07:22
 * Modify date: 2019-11-16:07:22
 */
public interface ICatalogService {
    List<CatalogVO> queryCatalogListService(String type);

    ResultBody updateCatalogService(CatalogVO catalogVO);

    ResultBody addCatalogService(CatalogVO catalogVO);

    ResultBody delCatalogService(int catalogId);
}
