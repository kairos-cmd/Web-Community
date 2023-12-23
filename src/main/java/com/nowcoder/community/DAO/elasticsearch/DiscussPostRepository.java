package com.nowcoder.community.DAO.elasticsearch;

import com.nowcoder.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


//extends ElasticsearchRepository<DiscussPost, Integer>声明处理的实体类是谁，主键是什么类型（整型）
//@Repository
//public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost, Integer> {
//    //这个接口已经有对es访问的增删改查各种方法
//}
