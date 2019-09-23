package com.okjiaoyu.auto.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-23:10:44
 * Modify date: 2019-09-23:10:44
 */
public class RequestUtil {

    public static String getRequetIp(HttpServletRequest request)  {
        if (request == null){
            throw new RuntimeException("HttpServletRequest对象为空");
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        final String[] arr = ip.split(",");

        for (final String str:arr){
            if (!"unknown".equalsIgnoreCase(str)){
                ip = str;
                break;
            }
        }
        return ip;

    }

    public static Integer getRequestType(HttpServletRequest request){
        if (request == null){
            throw new RuntimeException("HttpServletRequest对象为空");
        }
        String xRequestWith = request.getHeader("X-Requested-With");
        return xRequestWith == null ? 1 : 2;
    }

    public static Map<String,Object> getJoinPointInfoMap(JoinPoint joinPoint){
        Map<String,Object> joinPointInfo = new HashMap<>();
        String classPath = joinPoint.getTarget().getClass().getName();
        String mthodName = joinPoint.getSignature().getName();
        joinPointInfo.put("classPath",classPath);
        joinPointInfo.put("methodName",mthodName);
        Class<?> clazz = null;
        CtMethod ctMethod = null;
        LocalVariableAttribute attr = null;
        int length = 0;
        int pos = 0;
        try {
            clazz = Class.forName(classPath);
            String clazzName = clazz.getName();
            ClassPool classPool = ClassPool.getDefault();
            ClassClassPath classClassPath = new ClassClassPath(clazz);
            classPool.insertClassPath(classClassPath);
            CtClass ctClass = classPool.get(clazzName);
            ctMethod = ctClass.getDeclaredMethod(mthodName);
            MethodInfo methodInfo = ctMethod.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            if (attr == null)
                return joinPointInfo;
            length = ctMethod.getParameterTypes().length;
            pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("获取实例类失败");
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("未找到参数类型");
        }
        Map<String,Object> paramMap = new HashMap<>();
        Object[] paramsArgsValues = joinPoint.getArgs();
        String[] paramsArgsNames = new String[length];
        for (int i=0;i<length;i++){
            paramsArgsNames[i] = attr.variableName(i+pos);
            String paramsArgsName = attr.variableName(i+pos);
            if (paramsArgsName.equalsIgnoreCase("request") ||
                    paramsArgsName.equalsIgnoreCase("response") ||
                    paramsArgsName.equalsIgnoreCase("session")){
                break;
            }
            Object paramsArgsVallue = paramsArgsValues[i];
            paramMap.put(paramsArgsName,paramsArgsVallue);
        }

        joinPointInfo.put("paramMap", JSON.toJSONString(paramMap,
                SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue));
        return joinPointInfo;
    }
}
