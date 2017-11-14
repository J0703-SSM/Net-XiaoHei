package com.lanou.interceptor;

import com.lanou.domain.AdminInfo;
import com.lanou.domain.RoleForUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dllo on 17/11/13.
 */
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String uri = request.getRequestURI();

        if(uri.indexOf("/universal") != -1){

            return true;

        }

        RoleForUser user = (RoleForUser) request.getServletContext().getAttribute("user");

        if(user == null){

            request.getRequestDispatcher("/WEB_INF/pages/login.jsp").forward(request,response);

        }

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
