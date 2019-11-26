package com.blingabc.auto.service.impl;

import com.blingabc.auto.beans.InterfaceCaseVO;
import com.blingabc.auto.beans.InterfaceCaseVOWithBLOBs;
import com.blingabc.auto.common.PageInfo;
import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.dao.InterfaceCaseVOMapper;
import com.blingabc.auto.exception.BizException;
import com.blingabc.auto.service.IInterfaceCaseService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class InterfaceCaseServiceImpl implements IInterfaceCaseService {

    @Autowired
    private InterfaceCaseVOMapper interfaceCaseVOMapper;

    @Override
    public ResultBody addInterfaceCseService(InterfaceCaseVOWithBLOBs interfaceCaseVO) throws BizException {
        if (interfaceCaseVO.getCreateTime() == null){
            interfaceCaseVO.setCreateTime(new Date());
        }
        return ResultBody.success(interfaceCaseVOMapper.insertSelective(interfaceCaseVO));
    }

    @Override
    public ResultBody getInterfaceCasesByCatalogIdService(int catalogId,Integer pageNum, Integer pageSize) throws BizException {
        PageHelper.startPage(pageNum, pageSize);
        List<InterfaceCaseVO> cases = interfaceCaseVOMapper.queryInterfaceCasesByCatalogId(catalogId);
        return ResultBody.success(new PageInfo(cases));
    }
}
