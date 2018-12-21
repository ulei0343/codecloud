package club.codecloud.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @author ulei
 * @date 2018/11/15
 */
@Setter
@Getter
@Document(indexName = "codecloud", type = "employee")
public class Employee implements Serializable {

    @Id
    private Integer id;
    @Field
    private String firstName;
    @Field
    private String lastName;
    @Field
    private Integer age;
    @Field
    private String about;
    @Field
    private List<String> interests;
}
