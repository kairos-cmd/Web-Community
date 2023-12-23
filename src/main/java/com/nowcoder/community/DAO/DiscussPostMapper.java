package com.nowcoder.community.DAO;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //打了mapper才能被容器扫描这个接口，才能去装配他
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit, int orderMode);

    //Param这个注解适用于给参数取别名，如果这个方法只有一个参数并且在<if>里使用，就必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id, int commentCount);

    //修改帖子类型
    int updateType(int id, int type);

    int updateStatus(int id, int status);

    int updateScore(int id, double score);

}
