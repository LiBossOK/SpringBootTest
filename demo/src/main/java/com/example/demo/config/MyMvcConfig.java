package com.example.demo.config;

import com.example.demo.messageConvert.CustomMessageConvert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MyMvcConfig{

    //配置一些mvc的处理，formater，Converter，interceptors（拦截器）,MessageConverters
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
            return new WebMvcConfigurer(){
                @Override
                public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                    //自定义参数协商策略(内容协商来决定我们可以处理什么类型的请求）
                    //这里只配置了参数的协商策略，所以其他的就不会被匹配到，要用请求的协商策略，需要将其也配置上
                    Map<String, MediaType> mediaTypes = new HashMap<>();
                    mediaTypes.put("json",MediaType.APPLICATION_JSON);
                    mediaTypes.put("xml",MediaType.APPLICATION_XML);
                    mediaTypes.put("lsl",MediaType.parseMediaType("applicaton/x-lsl"));
                    ParameterContentNegotiationStrategy parameterStrategy= new ParameterContentNegotiationStrategy(mediaTypes);
                    configurer.strategies(Arrays.asList(parameterStrategy));
                }

                public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                    converters.add(new CustomMessageConvert());
                }
            };
    }

}
