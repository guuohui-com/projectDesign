package com.ysu.graduationproject.interceptor;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.Patient;
import com.ysu.graduationproject.utils.JsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("===========preHandle=============");
//        if (request.getSession().getAttribute("patient")==null){
//            response.reset();
//            try {
//                //解决中文乱码
//                response.setHeader("Content-Type","application/json;charset=UTF-8");
//                PrintWriter printWriter=response.getWriter();
//                ServerResponse serverResponse=ServerResponse.createServerResponseByFail("请先登录");
//                String json= JsonUtils.objToString(serverResponse);
//                printWriter.write(json);
//                printWriter.flush();
//                printWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //controller给diaspatcherServlet响应时调用
        System.out.println("=========postHandler==========");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //给客户端响应之后
        System.out.println("=======afterCompletion========");
    }
}
