import java.lang.reflect.Field;

public class ValueTest {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;

        System.out.println("a=" + a + ",b=" + b);
        swap(a, b);
        System.out.println("a=" + a + ",b=" + b);
    }

    private static void swap(Integer numa, Integer numb) {
        int tmp = numa.intValue();
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            field.set(numa, numb);
            System.out.println(tmp);
            System.out.println(Integer.valueOf(tmp));
            System.out.println(Integer.valueOf(1));
            field.set(numb, tmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
