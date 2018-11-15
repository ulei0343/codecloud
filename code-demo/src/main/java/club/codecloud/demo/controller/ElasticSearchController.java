package club.codecloud.demo.controller;

import club.codecloud.base.util.base.Result;
import club.codecloud.demo.entity.Employee;
import club.codecloud.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ulei
 * @date 2018/11/15
 */
@RestController
@RequestMapping("/es/employee")
public class ElasticSearchController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/lastName/{lastName}")
    public Result findEmployeesByLastName(@PathVariable String lastName) {
        List<Employee> employeesByLastName = employeeService.findEmployeesByLastName(lastName);
        return Result.success(employeesByLastName);
    }

    @RequestMapping("add")
    public String add() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("xuxu");
        employee.setLastName("zh");
        employee.setAge(26);
        employee.setAbout("i am in peking");
        employeeService.save(employee);
        System.err.println("add a obj");
        return "success";
    }


}
