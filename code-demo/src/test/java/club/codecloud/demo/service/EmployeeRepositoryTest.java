package club.codecloud.demo.service;

import club.codecloud.base.util.number.MathUtils;
import club.codecloud.demo.DemoApplication;
import club.codecloud.demo.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.RoundingMode;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    private ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();


    @Test
    public void findEmployeesByLastName() throws JsonProcessingException {
        String lastName = "grKwMSdk";
        List<Employee> employeeList = employeeRepository.findByLastName(lastName);
        String data = objectWriter.writeValueAsString(employeeList);
        log.info("\n" + data);
    }

    @Test
    public void findEmployeesByAgeBetweenOrderByAgeDesc() throws JsonProcessingException {
        List<Employee> employeeList = employeeRepository.findByAgeBetweenOrderByAgeDesc(24, 28);
        String data = objectWriter.writeValueAsString(employeeList);
        log.info("\n" + data);
    }

    @Test
    public void findEmployeesByLastNameLike() throws JsonProcessingException {
        String lastName = "ab";
        List<Employee> employeeList = employeeRepository.findByLastNameLike(lastName);
        String data = objectWriter.writeValueAsString(employeeList);
        log.info("\n" + data);
    }

    @Test
    public void findByLastNameIsContaining() throws JsonProcessingException {
        List<Employee> employeeList = employeeRepository.findByLastNameIsContaining("ab", "dd");
        String data = objectWriter.writeValueAsString(employeeList);
        log.info("\n" + data);
    }

    @Test
    public void findByLastNameAndAge() throws JsonProcessingException {
        List<Employee> employeeList = employeeRepository.findByLastNameLikeAndAge("dd", 13);
        String data = objectWriter.writeValueAsString(employeeList);
        log.info("\n" + data);
    }

    @Test
    public void findByAgeBetweenOrderByAgeDesc() throws JsonProcessingException {
        int pageSize = 100;
        Pageable page = PageRequest.of(4, pageSize);
        Page<Employee> employeePage = employeeRepository.findByAgeBetweenOrderByAgeDesc(24, 28, page);
        String data = objectWriter.writeValueAsString(employeePage.getContent());
        long pageTotal = MathUtils.divide(employeePage.getTotalElements(), pageSize, RoundingMode.UP);
        log.info("{}/{}-{}:\n{}", employeePage.getTotalPages(), pageTotal, employeePage.getTotalElements(), data);
    }
}