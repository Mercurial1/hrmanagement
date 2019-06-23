package com.syh.mchr.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.syh.mchr.interceptor.LoginInterceptor;

/**
 * 配置拦截器
 * @author SYH
 *
 */
@Configuration
public class LoginInterceptorConfigration implements WebMvcConfigurer{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		HandlerInterceptor interceptor = new LoginInterceptor();
		
		List<String> excludePaths = new ArrayList<>();
		excludePaths.add("/web/register.html");
		excludePaths.add("/web/login.html");
		excludePaths.add("/users/login");
		excludePaths.add("/dept/*");
		excludePaths.add("/js/**");
		excludePaths.add("/css/**");
		excludePaths.add("/images/**");
		excludePaths.add("/bootstrap3/**");
		
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**")
		.excludePathPatterns(excludePaths);
	}
	
}
