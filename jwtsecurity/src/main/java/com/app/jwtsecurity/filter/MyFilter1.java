package com.app.jwtsecurity.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

//      토큰 : cos 이걸 만들어줘야함. id pw가 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 그걸 응답을 해준다.
//      요청할 때마다 header에 Authorization에 value값으로 토큰을 가지고 오겠지?
//      그 때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증하면 됨.(RSA, HS256)
        if(req.getMethod().equals("POST")){
            log.info("POST 요청됨");
            String headerAuth= req.getHeader("Authorization");
            log.info(headerAuth);
            log.info("필터1");

            if(headerAuth.equals("cos")){
                chain.doFilter(req, res);
            }else{
                PrintWriter out = res.getWriter();
                out.println("인증안됨");
            }
        }
    }

}
