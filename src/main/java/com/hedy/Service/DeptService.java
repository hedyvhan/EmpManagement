package com.hedy.Service;

import com.hedy.Entity.Dept;

import java.util.List;


public interface DeptService {
    List<Dept> listDept();

    void deleteDept(Integer id);

    void InsertDept(Dept dept);
}
