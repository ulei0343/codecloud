package club.codecloud.demo.service;

import club.codecloud.demo.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ulei
 */
@Service
public interface EmployeeService extends PagingAndSortingRepository<Employee, Integer> {

    List<Employee> findEmployeesByLastName(String lastName);
}
