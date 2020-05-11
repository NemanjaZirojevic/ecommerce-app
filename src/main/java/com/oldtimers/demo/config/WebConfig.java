package com.oldtimers.demo.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by nemanja.zirojevic on 18/04/2020.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login")
                .setViewName("login");
        registry.addViewController("/home")
                .setViewName("home");
        registry.addViewController("/orders/success")
                .setViewName("success");

    }

}
