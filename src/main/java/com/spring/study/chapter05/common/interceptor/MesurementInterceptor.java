package com.spring.study.chapter05.common.interceptor;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MesurementInterceptor implements AsyncHandlerInterceptor {

    public static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("==========pre Handle===========");
        if (request.getAttribute(START_TIME) == null)
            request.setAttribute(START_TIME, System.currentTimeMillis());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("==========post Handle===========");
        long startTime = (Long) request.getAttribute(START_TIME);
        request.removeAttribute(START_TIME);
        long endTime = System.currentTimeMillis();
        System.out.println("Response-Processing-Time" + (endTime - startTime) + "ms");
        System.out.println("Response-Processing-Thread" + Thread.currentThread().getName());
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("==========after completion===========");
        long startTime = (Long) request.getAttribute(START_TIME);
        request.setAttribute(START_TIME, System.currentTimeMillis());
        long endTime = System.currentTimeMillis();

        System.out.println("Response-Processing-Time" + (endTime - startTime) + "ms");
        System.out.println("Response-Processing-Thread" + Thread.currentThread().getName());
    }
}
