package com.cl.agree.springcloud.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>Descriptions...
 *
 * @author RalphCheng
 * @date 2019/12/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJDBCTest {
    @Autowired
    DataSource dataSource;
    
    @Test
    public  void test01() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        conn.close();
    }
}
