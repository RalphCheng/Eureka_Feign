package com.cl.agree.springcloud.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
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
public class Order {
    private Integer id;
    private Integer number;
    private Timestamp createtime;
    private String note;
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                       Objects.equals(number, order.number) &&
                       Objects.equals(createtime, order.createtime) &&
                       Objects.equals(note, order.note);
    }
    
    @Override
    public int hashCode() {
        
        return Objects.hash(id, number, createtime, note);
    }
}
