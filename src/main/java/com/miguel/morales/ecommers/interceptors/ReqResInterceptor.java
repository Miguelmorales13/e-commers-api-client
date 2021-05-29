package com.miguel.morales.ecommers.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.System.currentTimeMillis;


@Component("reqResInterceptor")
public class ReqResInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ReqResInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String log = "[REQUEST][PATH:".concat(request.getServletPath()).concat("]");
        log = log.concat("[TYPE:").concat(request.getMethod()).concat("]");

        if (handler instanceof HandlerMethod) {
            HandlerMethod res = (HandlerMethod) handler;
            log = log.concat("[Class:").concat(res.getMethod().getDeclaringClass().getName()).concat("]");
            log = log.concat("[Method:").concat(res.getMethod().getName()).concat("]");
        }

        request.setAttribute("time", currentTimeMillis());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long time = currentTimeMillis() - ((long) request.getAttribute("time"));
        String log = "[RESPONSE][PATH:".concat(request.getServletPath()).concat("]")
                .concat("[TYPE:").concat(request.getMethod()).concat("]");
        if (handler instanceof HandlerMethod) {
            HandlerMethod res = (HandlerMethod) handler;
            log = log.concat("[Class:").concat(res.getMethod().getDeclaringClass().getName()).concat("]");
            log = log.concat("[Method:").concat(res.getMethod().getName()).concat("]");
        }

        log = log.concat("[TIME:").concat(String.valueOf(time)).concat("ms]");
//        if (response.c)
        logger.info(log);
    }

}
