package club.codecloud.base.util.base;

import com.google.common.base.Objects;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * 1. Object打印优化，主要解决数组的打印
 * <p>
 * 2. 多个对象的HashCode串联
 */
public class ObjectUtils {

    /**
     * JDK7 引入的Null安全的equals
     */
    public static boolean equals(@Nullable Object a, @Nullable Object b) {
        return Objects.equal(a, b);
    }

    /**
     * 多个对象的HashCode串联, 组成新的HashCode
     */
    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }

}
