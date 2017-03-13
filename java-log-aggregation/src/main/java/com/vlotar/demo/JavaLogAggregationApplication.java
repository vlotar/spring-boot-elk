package com.vlotar.demo;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot application launcher
 */
@SpringBootApplication
@EnableScheduling
public class JavaLogAggregationApplication {

    public static void main(String[] args) {
        //starts spring application (embedded tomcat, security, logging etc.)
        SpringApplication.run(JavaLogAggregationApplication.class, args);
    }

    /**
     * A servlet filter that inserts various values retrieved from the incoming http
     * request into the MDC
     *
     * @return {@link FilterRegistrationBean}
     */
    @Bean
    public FilterRegistrationBean userInsertingMdcFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        MDCInsertingServletFilter userFilter = new MDCInsertingServletFilter();
        registrationBean.setFilter(userFilter);
        return registrationBean;
    }

}
