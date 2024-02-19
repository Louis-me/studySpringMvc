package xyz.shi.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        // 检查用户是否已登录，如果未登录则跳转到登录页
        if (session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/home/userlogin");
            return false;
        } else {
            return true;
        }
    }
}
