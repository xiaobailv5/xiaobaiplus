package com.lv.xiaobaiplus.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 跨域配置
 * @date 2023/6/22 15:16:14
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry){

        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域的域名
                .allowedOrigins("*")
                //设置允许的请求方式
                .allowedMethods("GET","POST","DELETE","PUT")
                //设置允许的header属性
                .allowedHeaders("*")
                //跨域允许时间 6分钟
                .maxAge(3600);
    }
}
