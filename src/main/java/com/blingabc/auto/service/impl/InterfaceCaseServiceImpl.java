package com.blingabc.auto.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.blingabc.auto.beans.AssertVO;
import com.blingabc.auto.beans.InterfaceCaseVO;
import com.blingabc.auto.beans.InterfaceCaseVOWithBLOBs;
import com.blingabc.auto.common.PageInfo;
import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.dao.AssertVOMapper;
import com.blingabc.auto.dao.InterfaceCaseVOMapper;
import com.blingabc.auto.exception.BizException;
import com.blingabc.auto.service.IInterfaceCaseService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class InterfaceCaseServiceImpl implements IInterfaceCaseService {

    @Autowired
    private InterfaceCaseVOMapper interfaceCaseVOMapper;

    @Autowired
    private AssertVOMapper assertVOMapper;

    @Override
    public ResultBody addInterfaceCseService(InterfaceCaseVOWithBLOBs interfaceCaseVO) throws BizException {
        AssertVO assertVO = new AssertVO();
        String assertObj = interfaceCaseVO.getAssertObj();
        if (assertObj != null) {
            Map map = JSONObject.parseObject(assertObj, Map.class);
            assertVO.setAssertTypeId(Integer.parseInt(map.get("assertTypeId").toString()));
            assertVO.setCreateTime(new Date());
            assertVO.setName(map.get("name").toString());
            assertVO.setVal(map.get("val").toString());
            assertVOMapper.insertAssertReturnId(assertVO);
            if (assertVO == null) {
                throw new BizException(String.format("插入断言数据失败：{}", interfaceCaseVO.getAssertVO().toString()));
            }
        }
        int assertId = assertVO.getId();
        interfaceCaseVO.setAssertId(assertId);
        if (interfaceCaseVO.getCreateTime() == null) {
            interfaceCaseVO.setCreateTime(new Date());
        }
        return ResultBody.success(interfaceCaseVOMapper.insertSelective(interfaceCaseVO));
    }

    @Override
    public ResultBody getInterfaceCasesByCatalogIdService(int catalogId, Integer pageNum, Integer pageSize) throws BizException {
        PageHelper.startPage(pageNum, pageSize);
        List<InterfaceCaseVO> cases = interfaceCaseVOMapper.queryInterfaceCasesByCatalogId(catalogId);
        return ResultBody.success(new PageInfo(cases));
    }
}
