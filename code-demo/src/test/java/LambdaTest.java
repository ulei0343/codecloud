import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {
    static class Student {
        private Integer id;
        private String name;
        private Integer age;

        public Student(Integer id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        List<Student> studentList = Lists.newArrayList(
                new Student(150, "lisi", 24),
                new Student(101, "zhangsan", 18),
                new Student(202, "laowang", 50));


        // 大于18岁并且姓李的同学
        studentList.stream()
                .filter(student -> student.getAge() > 18 && student.getName().startsWith("li"))
                .collect(Collectors.toList())
                .forEach(student -> System.out.println(student.getName()));


        studentList.stream()
                .filter(student -> student.getId() > 120 && student.getAge() > 30)
//                .map(student -> student.getName())
                .map(Student::getName)
                .collect(Collectors.toList())
                .forEach(s -> System.out.println(s));

        long count = studentList.stream()
                .mapToInt(Student::getAge).count();
        System.out.println(count);

        studentList.stream()
                .sorted(Comparator.comparing(Student::getAge).reversed())
                .mapToInt(Student::getAge)
                .forEach(System.out::println);

    }
}
