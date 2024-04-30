package com.example.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    // lombok 미 사용시
//    Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class)

    //실제 동작 행위
    @Override
    public Object run() throws ZuulException {
        log.info("************** printing logs: {} **************");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("************** request URI :: {}", request.getRequestURI());

        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    // filter 지정
    @Override
    public boolean shouldFilter() {
        return true;
    }

}
