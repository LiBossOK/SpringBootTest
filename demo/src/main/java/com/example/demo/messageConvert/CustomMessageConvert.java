package com.example.demo.messageConvert;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CustomMessageConvert implements HttpMessageConverter<Object> {
    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        // 当controller中使用@RequestBady注解使用
        return false;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        //限制可以使用该消息转换器的条件
        return clazz instanceof Object;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        // 定义支持的数据类型，最终和请求的数据类型去匹配
        return MediaType.parseMediaTypes("applicaton/x-lsl");
    }

    @Override
    public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream= outputMessage.getBody();
        //定义变换后的消息内容
        outputStream.write(o.hashCode());
    }
}
