package com.hedy.controller;

import com.hedy.Entity.Emp;
import com.hedy.Entity.Result;
import com.hedy.Entity.PageBean;
import com.hedy.Service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {

    @Autowired(required=false)
    private EmpService empService;

    /**
     * @description: 分页查询员工信息，并将总数目和员工信息（两个种类的信息），封装为pageBean返回给前端
     * @param page:前端传回的起始页面
     * @param pageSize:前端传回的每页展示的数目
     * @return com.hedy.Entity.Result
     * @author: hedy
     * @date: 2023/10/21 22:14
     */
    @GetMapping
    public Result showEmp(String name,
                          Short gender,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime begin,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime end,
                          @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10")Integer pageSize
                            ){

        log.info("分页查询，参数：{},{},{},{},{},{}",name, gender, begin, end, page,pageSize);

        PageBean pageBean  = empService.page(name, gender, begin, end, page,pageSize);
        return Result.success(pageBean);
    }

    /**
     * @description: 批量删除
     * @param ids:
     * @return com.hedy.Entity.Result
     * @author: hedy
     * @date: 2023/10/24 19:30
     */
    @DeleteMapping("/{ids}")
    public Result deleteEmp(@PathVariable List<Integer> ids){
        log.info("删除员工，id：{}",ids);
        empService.deleteById(ids);
        return Result.success();
    }



    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}", emp);
        empService.save(emp);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据Id查询员工信息:Id = {}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }


    @PutMapping
    public Result update(@RequestBody  Emp emp){
        log.info("更新员工信息");
        empService.update(emp);
        return Result.success();
    }
}
