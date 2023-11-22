package com.hedy.controller;

import com.hedy.Entity.Dept;
import com.hedy.Entity.Result;
import com.hedy.Service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * @description: 展示所有部门信息
     * @return com.hedy.Entity.Result
     * @author: hedy
     * @date: 2023/10/21 20:45
     */
    @GetMapping("/depts")
    public Result showDept(){
        log.info("show all dept info");
        List<Dept> deptList = deptService.listDept();
        return Result.success(deptList);
    }


    /**
     * @description: 删除部门
     * @return com.hedy.Entity.Result
     * @author: hedy
     * @date: 2023/10/21 20:54
     */
    @DeleteMapping("/depts/{id}")
    public Result deleteDept(@PathVariable Integer id){
        log.info("delete dept by id:{}", id);
        deptService.deleteDept(id);
        return Result.success();
    }


    /**
     * @description: 新增部门
     * @param dept:
     * @return com.hedy.Entity.Result
     * @author: hedy
     * @date: 2023/10/23 16:38
     */
    @PostMapping("/depts")
    public Result InsertDept( @RequestBody Dept dept){
        log.info("Insert dept");
        deptService.InsertDept(dept);
        return Result.success();
    }

}
