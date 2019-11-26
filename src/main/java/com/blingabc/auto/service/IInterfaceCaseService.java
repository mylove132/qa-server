package com.blingabc.auto.service;
import com.blingabc.auto.beans.InterfaceCaseVOWithBLOBs;
import com.blingabc.auto.common.ResultBody;
import com.blingabc.auto.exception.BizException;

public interface IInterfaceCaseService {

    ResultBody addInterfaceCseService(InterfaceCaseVOWithBLOBs interfaceCaseVO) throws BizException;

    ResultBody getInterfaceCasesByCatalogIdService(int catalogId, Integer pageNum, Integer pageSize) throws BizException;
}
