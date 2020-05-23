package com.ysu.graduationproject;

import com.ysu.graduationproject.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class MySpringBootConfig implements WebMvcConfigurer {

    //拦截后台请求，验证用户是否等录
   @Autowired
    Interceptor interceptorNow;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加后台拦截路径

        List<String> stringList= new ArrayList<String>();
        registry.addInterceptor(interceptorNow).addPathPatterns("/patient/**").excludePathPatterns("/patient/login");
        //添加前台拦截路径
//        List<String> stringList= Lists.newArrayList();
//        stringList.add("/user/**");
//        stringList.add("/cart/**");
//        stringList.add("/portal/order/**");
//        stringList.add("/shipping/**");

        //需要排除的路径
       // List<String> excludePath=Lists.newArrayList();
//        excludePath.add("/user/register.do");
//        excludePath.add("/user/login.do");
//        excludePath.add("/user/forgetPasswordGetQuestion.do");
//        excludePath.add("/user/forgetPasswordCheckAnswer.do");
//        excludePath.add("/user/forgetPasswordSetPassword.do");

//        excludePath.add("/portal/order/alipayCallBack");
//        registry.addInterceptor(adminAuthroityInterceptor).addPathPatterns(stringList).excludePathPatterns(excludePath);
    }
}
