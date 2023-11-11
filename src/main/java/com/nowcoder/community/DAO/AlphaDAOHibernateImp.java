package com.nowcoder.community.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository("alphaHi")
public class AlphaDAOHibernateImp implements AlphaDAO{
    @Override
    public String select() {
        return "Hibernate";
    }
}
