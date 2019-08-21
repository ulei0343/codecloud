package club.codecloud.demo.controller;

import club.codecloud.base.util.base.Result;
import club.codecloud.base.util.number.RandomUtils;
import club.codecloud.demo.entity.Employee;
import club.codecloud.demo.service.EmployeeRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author ulei
 * @date 2018/11/15
 */
@RestController
@RequestMapping("es/employee")
public class ElasticSearchController {

    @Autowired
    EmployeeRepository employeeRepository;


    @RequestMapping("/lastName/{lastName}")
    public Result findEmployeesByLastName(@PathVariable String lastName) {
        List<Employee> employeeList = employeeRepository.findByLastName(lastName);
        return Result.success(employeeList);
    }

    @RequestMapping("/{id}")
    public Result findEmployeesById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return Result.success(employee);
    }

    @RequestMapping("/age/{minAge}-{maxAge}")
    public Result findEmployeesByAgeBetweenOrderByAgeDesc(@PathVariable Integer minAge, @PathVariable Integer maxAge) {
        List<Employee> employeeList = employeeRepository.findByAgeBetweenOrderByAgeDesc(minAge, maxAge);
        return Result.success(employeeList);
    }

    @RequestMapping("add")
    public Result add() {
       /* Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("xuxu");
        employee.setLastName("zh");
        employee.setAge(26);
        employee.setAbout("i am in peking");
        Employee saveEmployee = employeeService.save(employee);*/

        List<Employee> employeeList = Lists.newArrayList();
        for (int i = 2; i < 2000; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            employee.setFirstName(RandomUtils.randomStringFixLength(5));
            employee.setLastName(RandomUtils.randomStringFixLength(8));
            employee.setAge(RandomUtils.nextInt(12, 40));
            employee.setAbout(RandomUtils.randomStringFixLength(20));
            employeeList.add(employee);
        }
        employeeRepository.saveAll(employeeList);

        return Result.success("ok");
    }


}
