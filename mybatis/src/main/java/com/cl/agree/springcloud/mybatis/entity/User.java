package com.cl.agree.springcloud.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.Objects;

/**
 * <p>Descriptions...
 *
 * @author RalphCheng
 * @date 2019/12/21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Integer id;
    private String name;
    private Date birth;
    private Integer sex;
    private String address;
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                       Objects.equals(name, user.name) &&
                       Objects.equals(birth, user.birth) &&
                       Objects.equals(sex, user.sex) &&
                       Objects.equals(address, user.address);
    }
    
    @Override
    public int hashCode() {
        
        return Objects.hash(id, name, birth, sex, address);
    }
}
