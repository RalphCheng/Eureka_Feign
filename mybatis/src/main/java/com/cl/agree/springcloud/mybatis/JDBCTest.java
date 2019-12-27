package com.cl.agree.springcloud.mybatis;

import javax.print.DocFlavor;
import java.sql.*;

/**
 * <p>Descriptions...
 *
 * @author RalphCheng
 * @date 2019/12/21.
 */
public class JDBCTest {
    /**
     * 原生JDBC使用步骤
     * 1、加载驱动
     * 2、创建数据库连接
     * 3、编写sql语句
     * 4、创建PreparedStatement
     * 5、设置参数
     * 6、执行语句得到结果集
     * 7、遍历结果集，输出结果
     * 8、断开连接释放资源
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&characterEncoding=utf-8", "root", "root");
//        String sql = "select * from user where sex = ?";
        //查询user表
        String sql = "select * from user u left join order o on u.id = o.user_id where o.number = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 888888);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Date birth = rs.getDate(3);
            String address = rs.getString(5);
            System.out.println("id: " + id + ", name: " + name + ", birth: " + birth.toString() + ", address: " + address);
        }
        rs.close();
        conn.close();
    }
}
