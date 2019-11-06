package com.okjiaoyu.auto.service.impl;

import com.okjiaoyu.auto.common.ResultBody;
import com.okjiaoyu.auto.common.errorcode.catalog.CatalogErrorCode;
import com.okjiaoyu.auto.dao.CatalogEntityMapper;
import com.okjiaoyu.auto.service.ICatalogService;
import com.okjiaoyu.auto.vo.CatalogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-10-08:16:34
 * Modify date: 2019-10-08:16:34
 */
@Slf4j
@Service
public class CatalogServiceImpl implements ICatalogService {

    @Autowired
    private CatalogEntityMapper catalogEntityMapper;

    @Override
    public List<CatalogEntity> catalogListService() {
        return catalogEntityMapper.queryCatalogList();
    }

    @Override
    public ResultBody addCatalogService(CatalogEntity catalogEntity) {
        int result = catalogEntityMapper.insertSelective(catalogEntity);
        if (result<=0){
            throw new RuntimeException("添加目录失败");
        }
        return ResultBody.success(true);
    }

    @Override
    public ResultBody updateCatalogService(CatalogEntity catalogEntity) {
        if (catalogEntity.getId() == null || catalogEntity.getId() <= 0){
            throw new RuntimeException("修改项目id不能为空或者小于0");
        }
        if (catalogEntityMapper.selectByPrimaryKey(catalogEntity.getId()) == null){
            throw new RuntimeException("修改项目id不存在");
        }
        int result = catalogEntityMapper.updateByPrimaryKeySelective(catalogEntity);
        if (result<=0){
            throw new RuntimeException(String.format("修改主键id：{}失败", catalogEntity.getId()));
        }
        return ResultBody.success(true);
    }

    @Override
    public ResultBody delCatalogService(int catalogId) {
        CatalogEntity catalogEntity = catalogEntityMapper.selectByPrimaryKey(catalogId);
        if (catalogEntity.getChildren() != null && catalogEntity.getChildren().size() > 0){
            return ResultBody.error(CatalogErrorCode.EXIST_SOON_ID);
        }
        int result = catalogEntityMapper.deleteByPrimaryKey(catalogId);
        if (result<=0){
            throw new RuntimeException(String.format("删除主键id：{}失败", catalogId));
        }
        return ResultBody.success(true);
    }

    @Override
    public ResultBody delCatalogService(int[] ids) {
        for (int i=0; i < ids.length-1;i++){
            CatalogEntity catalogEntity = catalogEntityMapper.selectByPrimaryKey(ids[i]);
            if (catalogEntity.getChildren() != null || catalogEntity.getChildren().size() > 0){
                return ResultBody.error(CatalogErrorCode.EXIST_SOON_ID);
            }
            int result = catalogEntityMapper.deleteByPrimaryKey(ids[i]);
            if (result<=0){
                throw new RuntimeException(String.format("删除主键id：{}失败", ids[i]));
            }
        }
        return ResultBody.success(true);
    }

    @Override
    public ResultBody queryCatalogById(int catalogId) {
        return ResultBody.success(catalogEntityMapper.selectByPrimaryKey(catalogId));
    }
}
