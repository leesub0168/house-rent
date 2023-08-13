package com.org.houserent.config;

import com.org.houserent.controller.MemberController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AspectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + session.toString());
        if(session.getAttribute(MemberController.MEMBER_ID) == null && !isPassUrlList(request.getRequestURI()))
            throw new AuthenticationException("로그인 후 이용가능합니다.");
        return true;
    }

    public boolean isPassUrlList(String url) throws Exception {
        List<String> passUrlList = new ArrayList<>();

        passUrlList.add("/");
        passUrlList.add("/join");
        passUrlList.add("/login");
        passUrlList.add("/search");

        return passUrlList.contains(url);
    }
}
