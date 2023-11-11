package com.nowcoder.community.DAO;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //打了mapper才能被容器扫描这个接口，才能去装配他
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //Param这个注解适用于给参数取别名，如果这个方法只有一个参数并且在<if>里使用，就必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);

}
