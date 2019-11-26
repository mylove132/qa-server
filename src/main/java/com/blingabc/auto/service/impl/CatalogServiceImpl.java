package com.blingabc.auto.service.impl;

import com.blingabc.auto.beans.CatalogVO;
import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.dao.CatalogVOMapper;
import com.blingabc.auto.service.ICatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-11-16:07:25
 * Modify date: 2019-11-16:07:25
 */
@Slf4j
@Service
public class CatalogServiceImpl implements ICatalogService{

    @Autowired
    private CatalogVOMapper catalogVOMapper;

    @Override
    public List<CatalogVO> queryCatalogListService(Integer caseTypeId, Integer userId, Integer envId) {
        return catalogVOMapper.queryCatalogList(caseTypeId,userId,envId);
    }

    @Override
    public ResultBody updateCatalogService(CatalogVO catalogVO) {
        return ResultBody.success(catalogVOMapper.updateByPrimaryKeySelective(catalogVO));
    }

    @Override
    public ResultBody addCatalogService(CatalogVO catalogVO) {
        return ResultBody.success(catalogVOMapper.insertSelective(catalogVO));
    }

    @Override
    public ResultBody delCatalogService(int catalogId) {
        return ResultBody.success(catalogVOMapper.deleteByPrimaryKey(catalogId));
    }
}
