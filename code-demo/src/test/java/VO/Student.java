package VO;

import org.apache.commons.lang3.ClassUtils;

public class Student {


    private String name;

    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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


    public static void main(String[] args) {
        String shortClassName = ClassUtils.getShortClassName(Student.class);
        System.out.println(shortClassName);

        String name = ClassUtils.getName(Student.class);
        System.out.println(name);

        String packageName = ClassUtils.getPackageName(Student.class);
        System.out.println(packageName);



    }
}
