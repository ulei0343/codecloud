import com.google.common.collect.Lists;

import java.util.List;

public class ObjectUtilsTest {

    public static void main(String[] args) {
        Student zhangsan = new Student("zhangsan", 12);
        List<Student> students = Lists.newArrayList(zhangsan, zhangsan);
        System.out.println(students.toString());
        System.out.println(org.apache.commons.lang3.ObjectUtils.toString(students));
        System.out.println(org.apache.commons.lang3.ObjectUtils.identityToString(students));
    }
}
