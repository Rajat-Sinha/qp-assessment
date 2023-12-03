package com.grocery.mgmt.platform.app.config;

import com.grocery.mgmt.platform.common.util.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if(profile.equals(Constant.PROFILE_DEV)) {
            registry.addMapping("/**")
                    .allowedMethods("*").allowedOrigins("*");
        }
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
