package com.nowcoder.community.DAO;

import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

//不推荐使用
@Mapper
@Deprecated
public interface LoginTicketMapper {
    //登陆成功，插入一个数据
    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    //主键自动生成，useGeneratedKeys.指定注入哪个对象用keyProperty
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    //查询方法,利用ticket为条件来查询
    @Select({
            "select id,user_id,ticket,status,expired ",
            "from login_ticket where ticket=#{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    //修改凭证的状态
    @Update({
            "<script>",
            "update login_ticket set status=#{status} where ticket=#{ticket} ",
            "<if test=\"ticket!=null\"> ",
            "and 1=1 ",
            "</if>",
            "</script>"
    })
    int updateStatus(String ticket, int status);
}
