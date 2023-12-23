package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        //请求的路径
        System.out.println(request.getServletPath());
        //请求行是key,value结构,enumeration是一个很老的迭代器
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+": "+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();
        ) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求怎么处理，一般用于获取某些数据
    //查询所有的学生 /students?current=1&limit=20
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "someone like you";
    }

    // /student/123 编号成为路径的一部分，id是变量所以用大括号
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    //从路径中得到这个变量赋值给你的参数
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //Post请求：当浏览器向服务器提交数据的时候，该如何获取里面的参数
    //http://localhost:8080/community/html/student.html
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    //不加requestBody默认就是返回html
    //ModelAndView：主键都是由DispatchServerLet调度的，会调Controller的方法，会返回Model数据，还有View相关的数据，
    //将Model和View数据都提交给模板引擎，有模板引擎进行渲染，生成动态的HTML
    public ModelAndView getTeacher(){
        //返回Model和View两份数据
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",30);
        mav.setViewName("/demo/view");//view指HTML对象
        return mav;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age",80);
        return "/demo/view";
    }

    //响应JSON数据（异步请求当中）
    //java对象，浏览器解析对象用的是JS对象，使用json字符串可以让java和JS对象兼容，JSON是跨语言字符串形式
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    //resonsebody加map类型转成json字符串
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000);
        return emp;
    }

    //cookie示例
    @RequestMapping(path = "cookie/set",method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        //创建Cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        //设置cookie生效的范围
        cookie.setPath("/community/alpha");
        //Cookie默认发送给浏览器，关闭就消失，但是可以设置生效时间，就会存在硬盘里，直到超过这个时间才会失效
        //设置Cookie的生存时间
        cookie.setMaxAge(60 * 10);//10分钟
        //发送Cookie，把它放在响应的头里
        response.addCookie(cookie);
        return "set cookie";
    }

    //ajax示例,不是返回网页，返回的是字符串，所以加上@ResponseBody
    @RequestMapping(path = "/ajax",method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0,"操作成功！");
    }
}
