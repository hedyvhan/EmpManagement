package com.hedy.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 分页查询结果的封装类，结果包含查询数据类和总数目类，一起封装到这个类中
 * @param null:
 * @return
 * @author: hedy
 * @date: 2023/10/21 21:25
 */



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    //变量名不要有误，否则前端页面不展示

    //总记录数
    private Long total;
    //数据列表
    private List rows;

}
