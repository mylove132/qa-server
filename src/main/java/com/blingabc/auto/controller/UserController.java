//package com.blingabc.auto.controller;
//
//import com.blingabc.auto.dao.TokenEntityMapper;
//import com.blingabc.auto.service.IUserService;
//import com.blingabc.auto.util.MD5Util;
//import com.blingabc.auto.vo.TokenEntity;
//import com.blingabc.auto.vo.UserEntity;
//import com.github.pagehelper.PageInfo;
//import com.blingabc.auto.annotion.AuthPermission;
//import com.blingabc.auto.common.ResultBody;
//import com.blingabc.auto.common.constant.PermissionConstant;
//import com.blingabc.auto.common.errorcode.user.UserErrorCode;
//import com.blingabc.auto.exception.BizException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author: liuzhanhui
// * @Decription:
// * @Date: Created in 2019-06-28:16:33
// * Modify date: 2019-06-28:16:33
// */
//
//@Slf4j
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private TokenEntityMapper tokenMapper;
//
//    @RequestMapping(value = "",method = RequestMethod.POST)
//    public ResultBody addUserController(UserEntity user){
//        log.info("添加用户信息："+user.toString());
//        if (userService.getUserByEmail(user.getEmail()) != null){
//            return ResultBody.error(UserErrorCode.DISABLED);
//        }
//        if (user.getRoleId() == null){
//            user.setRoleId(1);
//        }
//        if (user.getCreateTime() == null){
//            user.setCreateTime(new Date());
//        }
//        try {
//            log.info("添加用户成功");
//            log.info("添加用户信息："+user.toString());
//            return ResultBody.success(userService.addUser(user));
//        }catch (BizException e){
//            throw new BizException("添加用户失败，"+e.getMessage());
//        }
//    }
//
//    @AuthPermission(PermissionConstant.ADMINISTRATOR)
//    @RequestMapping(value = "",method = RequestMethod.PUT)
//    public ResultBody updateUserController(UserEntity user){
//        log.info("修改用户信息："+user.toString());
//        if (user.getCreateTime() == null){
//            user.setCreateTime(new Date());
//        }
//        try {
//            return ResultBody.success(userService.updateUser(user));
//        }catch (BizException e){
//            throw new BizException(e.getMessage());
//        }
//    }
//
//
//    @RequestMapping(value = "",method = RequestMethod.GET)
//    public ResultBody userListController(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
//                                           @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
//        return ResultBody.success(new PageInfo(userService.getUserList(pageNum,pageSize)));
//    }
//
//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//    public ResultBody getUserInfo(@PathVariable Integer id){
//        return ResultBody.success(userService.getUserById(id));
//    }
//
//    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
//    public ResultBody deleteUserInfo(@PathVariable Integer id){
//        return ResultBody.success(userService.deleteUserById(id));
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResultBody login(HttpServletRequest request){
//        String account = request.getParameter("account");
//        String password = request.getParameter("passwd");
//        UserEntity u1 = userService.getUserByAccount(account);
//        UserEntity user = new UserEntity();
//        if (u1 == null){
//            user.setRoleId(1);
//            user.setCreateTime(new Date());
//            user.setEmail(account+"@okay.cn");
//            user.setPassword("123456");
//            user.setName(account);
//            userService.addUser(user);
//            user = userService.getUserByAccount(account);
//        }else {
//            user = u1;
//        }
//        String md5 = MD5Util.encrypt(user.getName());
//        TokenEntity token = tokenMapper.selectByUserId(user.getId());
//        if (token == null){
//            try {
//                TokenEntity tk = new TokenEntity();
//                tk.setToken(md5);
//                tk.setUserId(user.getId());
//                tk.setUpdateTime(new Date());
//                tokenMapper.insert(tk);
//            }catch (Exception e){
//                return ResultBody.error(UserErrorCode.DISABLED);
//            }
//
//        }else {
//            try {
//                TokenEntity tk = new TokenEntity();
//                tk.setUpdateTime(new Date());
//                tk.setUserId(user.getId());
//                tk.setToken(md5);
//                tk.setId(token.getId());
//                tokenMapper.updateByPrimaryKeySelective(tk);
//            }catch (BizException e){
//                return ResultBody.error(UserErrorCode.DISABLED);
//            }
//
//        }
//        Map<String,Object> result = new HashMap<String, Object>();
//        result.put("token",md5);
//        result.put("name",user.getName());
//        result.put("user_id",user.getId());
//        return ResultBody.success(result);
//    }
//}
