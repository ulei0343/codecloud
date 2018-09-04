package club.codecloud.demo;

import org.hibernate.validator.constraints.Range;

public class User {


    private String name;

    @Range(min = 24L, max = 100, message = "请输入有效年龄")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
