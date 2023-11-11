package com.nowcoder.community.service;

import com.nowcoder.community.DAO.AlphaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AlphaService {

    @Autowired
    private AlphaDAO alphaDAO;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }

    public String find(){
        return alphaDAO.select();
    }
}
