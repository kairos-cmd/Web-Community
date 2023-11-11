package com.nowcoder.community.DAO;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class AlphaDaoMyBatisImpl implements AlphaDAO{
    @Override
    public String select() {
        return "MyBatis";
    }
}
