package com.vlotar.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.marker.Markers.appendEntries;

/**
 * Allows to track requests and log needed information
 *
 * @author lotarvad
 */
@Component
public class RequestLoggingFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String url = request.getServletPath();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        chain.doFilter(req, res);
        stopWatch.stop();

        Map<String, Object> markers = new HashMap<>();
        markers.put("res.status", response.getStatus());
        markers.put("req.executionTime", stopWatch.getTotalTimeMillis());
        LOGGER.info(appendEntries(markers), "Execution time for {} {} is {} ms", url, request.getMethod(), stopWatch.getTotalTimeMillis());
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}