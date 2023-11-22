package com.hedy.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hedy.Entity.Emp;
import com.hedy.Entity.PageBean;
import com.hedy.Mapper.DeptMapper;
import com.hedy.Mapper.EmpMapper;
import com.hedy.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

 /*   *//**
     * @description: 将总数目和员工信息（两个种类的信息），封装为pageBean返回给前端
     * @param page: 前端传回的起始页面; start: 起始索引！注意转换
     * @param pageSize: 前端传回的每页展示的数目
     * @return com.hedy.Entity.PageBean
     * @author: hedy
     * @date: 2023/10/23 16:44
     *//*
    public PageBean page(Integer page, Integer pageSize){
//        1. 查询总记录数目
        Long count = empMapper.count();

//        2. 获取分页查询列表
        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.pageEmp(start, pageSize);

//        3. 封装成PageBean对象
        PageBean pageBean = new PageBean( count, empList);
        return pageBean;
    }
*/
    @Override
    public PageBean page(String name,
                         Short gender,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime begin,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime end,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10")Integer pageSize){
//        1. 设置分页参数
        PageHelper.startPage(page, pageSize);

//        2. 执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;

//        3. 封装成PageBean对象
        PageBean pageBean = new PageBean( p.getTotal(), p.getResult());

        return pageBean;
    }


    @Override
    public void deleteById(List<Integer> ids){
        empMapper.deleteById(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp getById(Integer id){
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

}
