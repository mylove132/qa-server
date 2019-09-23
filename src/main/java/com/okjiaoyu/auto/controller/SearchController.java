package com.okjiaoyu.auto.controller;

import com.okjiaoyu.auto.annotion.Operation;
import com.okjiaoyu.auto.common.BaseResponse;
import com.okjiaoyu.auto.common.PageInfo;
import com.okjiaoyu.auto.common.SuccessResponse;
import com.okjiaoyu.auto.service.ISearchService;
import com.okjiaoyu.auto.vo.ModuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-20:17:36
 * Modify date: 2019-09-20:17:36
 */
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @Operation("通过环境id过滤模块")
    @RequestMapping(value = "moduleByEnvId",method = RequestMethod.GET)
    public BaseResponse filterModuleByEnvIdCrontroller(Integer envId,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                       @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        List<ModuleEntity> moduleEntities = searchService.filterModuleListByEnvIdService(envId,pageNum,pageSize);
        return new SuccessResponse(new PageInfo(moduleEntities));
    }

    @Operation("通过项目id过滤模块")
    @RequestMapping(value = "moduleByProjectId",method = RequestMethod.GET)
    public BaseResponse filterModuleByProjectIdCrontroller(Integer projectId,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                       @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        List<ModuleEntity> moduleEntities = searchService.filterModuleListByProjectIdService(projectId,pageNum,pageSize);
        return new SuccessResponse(new PageInfo(moduleEntities));
    }

    @Operation("通过项目id和环境id过滤模块")
    @RequestMapping(value = "moduleByProjectIdAndEnvId",method = RequestMethod.GET)
    public BaseResponse filterModuleByProjectIdAndEnvIdCrontroller(Integer projectId,Integer envId,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                           @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        List<ModuleEntity> moduleEntities = searchService.filterModuleListByProjectIdAndEnvIdService(projectId,envId,pageNum,pageSize);
        return new SuccessResponse(new PageInfo(moduleEntities));
    }

    @Operation("关键字查询模块")
    @RequestMapping(value = "keyword",method = RequestMethod.GET)
    public BaseResponse filterModuleByKeyword(String keyword,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                                   @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        List<ModuleEntity> moduleEntities = searchService.filterModuleListByKeywordService(keyword,pageNum,pageSize);
        return new SuccessResponse(new PageInfo(moduleEntities));
    }
}
