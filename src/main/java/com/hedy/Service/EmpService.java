package com.hedy.Service;
import com.hedy.Entity.Emp;
import com.hedy.Entity.PageBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    PageBean page(String name,
                  Short gender,
                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime begin,
                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime end,
                  @RequestParam(defaultValue = "1") Integer page,
                  @RequestParam(defaultValue = "10")Integer pageSize);

    void deleteById(List<Integer> ids);

    void save(Emp emp);

    /*
     * @description: 根据id查询员工
     * @param null: 
     * @return
     * @author: hedy
     * @date: 2023/11/21 20:07
     */

    Emp getById(Integer id);


    /*
     * @description: 更新员工
     * @param null:
     * @return
     * @author: hedy
     * @date: 2023/11/21 20:33
     */
    void update(Emp emp);
}
