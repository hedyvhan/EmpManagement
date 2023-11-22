package com.hedy.Mapper;

import com.hedy.Entity.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    public List<Dept> listDept();

    @Delete("delete from dept where id = #{id}")
    void deleteDept(Integer id );

    @Insert("insert into dept (name, create_time, update_time) VALUES (#{name}, #{createTime}, #{updateTime} )")
    void insertDept(Dept dept);
}
