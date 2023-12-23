package com.nowcoder.community.controller.advice;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//只扫描带有Controller注解的那些Bean
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {
    //日志主键实例化
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    //Exception是所有异常的父类，所以意思是所有异常我都用ExceptionHandler来处理.必须是公有的且没有返回值
    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error("服务器发生异常: " + e.getMessage());
        for(StackTraceElement element : e.getStackTrace()){
            logger.error(element.toString());
        }

        //这里要判断是异步请求还是同步请求
        String xRequestWith = request.getHeader("x-requested-with");
        //只有异步请求希望你返回XML类型的数据或者json，普通请求是HTML
        if("XMLHttpRequest".equals(xRequestWith)){
            //说明这是一个异步请求
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(CommunityUtil.getJSONString(1,"服务器异常！"));
        }else{
            //这是一个普通请求
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
