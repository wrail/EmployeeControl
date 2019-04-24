package com.wrial.demo.controller;

import com.wrial.demo.dao.DepartmentDao;
import com.wrial.demo.dao.EmployeeDao;
import com.wrial.demo.entity.Department;
import com.wrial.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping(value = "/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //来到员工添加的页面
    @GetMapping(value = "/emp")
    public String addPage(Model model) {

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //员工添加
//  @RequestMapping("/addemp")
    @PostMapping("/addemp")
    //SpringMVc自动绑定
    public String addEmp(Employee employee) {

        System.out.println(employee.toString());
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String updatePage(@PathVariable("id") Integer id, Model model) {
//        在回显的时候也需要查部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        Employee employee = employeeDao.get(id);
        model.addAttribute("employee", employee);

        return "emp/edit";
    }

    //信息修改
    @PutMapping("/emp")
    public String updateEmp(Employee employee) {

        //修改方法（是一个懒方法，在内部进行id判断，如果有id就是修改，没id就是增加）
        employeeDao.save(employee);
        System.out.println("update:" + employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:emps";

    }
}
