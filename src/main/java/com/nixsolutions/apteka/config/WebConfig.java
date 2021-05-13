package com.nixsolutions.apteka.config;

import java.time.Duration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.CacheControl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;

import com.nixsolutions.apteka.Constants;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebMvc
@ComponentScan({"com.nixsolutions.apteka.controller"})
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp").viewClass(JstlView.class);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/doctor", "/doctor/index");
        registry.addRedirectViewController("/doctor/", "/doctor/index");
        registry.addViewController("/doctor/index").setViewName("/doctor/index");
        //registry.addViewController("/doctor/login").setViewName("/doctor/login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {

            @Override
            public boolean preHandle(HttpServletRequest request,
                    HttpServletResponse response, Object handler)
                    throws Exception {
                Object currentDoctor = request.getSession().getAttribute(Constants.CURRENT_DOCTOR);
                log.debug("CURRENT DOCTOR: {}", currentDoctor);
                if (currentDoctor == null) {
//                    request.getRequestDispatcher("/WEB-INF/jsp/doctor/login.jsp").forward(request, response);
                    response.sendRedirect(request.getContextPath() + "/doctor/login");
                    return false;
                }
                return true;
            }

        }).addPathPatterns("/doctor/*").excludePathPatterns("/doctor/login");
    }




}
