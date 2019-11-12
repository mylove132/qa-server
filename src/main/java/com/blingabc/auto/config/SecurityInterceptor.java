package com.blingabc.auto.config;
import com.blingabc.auto.annotion.AuthPermission;
import com.blingabc.auto.dao.TokenEntityMapper;
import com.blingabc.auto.dao.UserEntityMapper;
import com.blingabc.auto.vo.TokenEntity;
import com.blingabc.auto.vo.UserEntity;
import com.blingabc.auto.dao.RoleEntityMapper;
import com.blingabc.auto.vo.RoleEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenEntityMapper tokenEntityMapper;
    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证权限
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            AuthPermission requiredPermission = handlerMethod.getMethod().getAnnotation(AuthPermission.class);
            // 如果方法上的注解为空 则获取类的注解
            if (requiredPermission == null) {
                return true;
            }
            // 如果标记了注解，则判断权限
            if (requiredPermission != null) {
                String token = request.getHeader("token");
                TokenEntity tk = tokenEntityMapper.selectByToken(token);
                if (token == null) {
                    response.sendError(HttpStatus.FORBIDDEN.value(), "header请传入token");
                    return false;
                } else {
                    if (tk == null) {
                        response.sendError(HttpStatus.FORBIDDEN.value(), "token不存在");
                        return false;
                    }
                    int timeC = calculateTimeDifferenceBySimpleDateFormat(new Date(), tk.getUpdateTime());
                    if (timeC > 60) {
                        response.sendError(HttpStatus.FORBIDDEN.value(), "token失效");
                        return false;
                    }
                }
                if (StringUtils.isBlank(requiredPermission.value())) {
                    return true;
                } else {
                    Integer userId = tk.getUserId();
                    UserEntity user = userEntityMapper.selectByPrimaryKey(userId);
                    if (user == null) {
                        log.error("用户id{}不存在",userId);
                        response.sendError(HttpStatus.FORBIDDEN.value(), "token用户不存在");
                        return false;
                    }
                    Integer roleId = user.getRoleId();
                    RoleEntity role = roleEntityMapper.selectByPrimaryKey(roleId);
                    if (role == null) {
                        log.error("用户角色id{}不存在",roleId);
                        response.sendError(HttpStatus.FORBIDDEN.value(), "用户角色不存在");
                        return false;
                    }else {
                        if (roleId < Integer.parseInt(requiredPermission.value())){
                            log.info("角色id:{}",roleId);
                            log.info("权限要求:{}",requiredPermission.value());
                            response.sendError(HttpStatus.FORBIDDEN.value(), "用户角色无权限");
                            return false;
                        }else {
                            tk.setUpdateTime(new Date());
                            tokenEntityMapper.updateByPrimaryKeySelective(tk);
                            return true;
                        }
                    }
                }
            }
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // TODO
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // TODO
    }

    public static int calculateTimeDifferenceBySimpleDateFormat(Date time1, Date time2) {
        int minutes;
        try {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String tm1 = simpleFormat.format(time1);
            String tm2 = simpleFormat.format(time2);
            Date fromDate1 = simpleFormat.parse(tm1);
            Date toDate1 = simpleFormat.parse(tm2);
            long from1 = fromDate1.getTime();
            long to1 = toDate1.getTime();
            minutes = (int) ((from1 - to1) / (1000 * 60));
        } catch (ParseException e) {
            return 0;
        }

        return minutes;
    }
}
