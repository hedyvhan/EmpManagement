package com.hedy.Mapper;

import com.hedy.Entity.Emp;
import com.hedy.Entity.PageBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {

/*
    *//**
     * @description: 查询员工总记录数
     * @return java.lang.Long
     * @author: hedy
     * @date: 2023/10/21 21:29
     *//*

    @Select("select count(*) from emp")
    public Long count();*/

    /**
     * @description: 查询（start，pagesize）的员工信息
     * @param start:
     * @param pageSize:
     * @return java.util.List<com.hedy.Entity.Emp>
     * @author: hedy
     * @date: 2023/10/23 16:48
     *//*

    @Select("SELECT * from emp limit #{start}, #{pageSize}")
    public List<Emp> pageEmp (Integer start, Integer pageSize);
*/
    //使用pagehelper插件实现员工信息的分页查询
    //@Select("select * from emp")
    List<Emp> list(String name,
                          Short gender,
                          LocalDateTime begin,
                          LocalDateTime end);



    /**
     * @description: 批量删除员工
     * @param ids:
     * @return void
     * @author: hedy
     * @date: 2023/10/24 19:09
     */
    void deleteById( List<Integer> ids);


    @Insert("insert into emp (username, name, gender, job, image, entrydate, dept_id, create_time, update_time) " +
            "VALUES ( #{username}, #{name}, #{gender}, #{job}, #{image}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime} )")
    void save(Emp emp);

    /*
     * @description: 根据id查询员工
     * @param null:
     * @return
     * @author: hedy
     * @date: 2023/11/21 20:36
     */
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);


    /*
     * @description: 更新员工 xml
     * @param null:
     * @return
     * @author: hedy
     * @date: 2023/11/21 20:36
     */
    void update(Emp emp);
}
