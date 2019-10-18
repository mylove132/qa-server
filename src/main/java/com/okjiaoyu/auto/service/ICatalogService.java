package com.okjiaoyu.auto.service;

import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.vo.CatalogEntity;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-10-08:16:33
 * Modify date: 2019-10-08:16:33
 */
public interface ICatalogService {

    List<CatalogEntity> catalogListService();

    BaseResponse addCatalogService(CatalogEntity catalogEntity);

    BaseResponse updateCatalogService(CatalogEntity catalogEntity);

    BaseResponse delCatalogService(int catalogId);

    BaseResponse delCatalogService(int[] ids);
}
