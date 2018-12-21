package club.codecloud.demo.service;

import club.codecloud.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ulei
 */
@Service
public interface EmployeeService extends ElasticsearchRepository<Employee, Integer> {

    List<Employee> findByLastName(String lastName);

    List<Employee> findByLastNameLike(String lastName);

    List<Employee> findByLastNameIsContaining(String... lastName);

    List<Employee> findByLastNameLikeAndAge(String lastName, Integer age);

    List<Employee> findByAgeBetweenOrderByAgeDesc(Integer minAge, Integer maxAge);

    Page<Employee> findByAgeBetweenOrderByAgeDesc(Integer minAge, Integer maxAge, Pageable page);

}