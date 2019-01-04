package bit;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.BitSet;
import java.util.Set;

public class BitTest {

    // VIP人群：1\3\5\63\65\67\69\127
    private static final Set<Integer> VIP_USERS_ID = Sets.newHashSet(1, 3, 5, 63, 65, 67, 69, 127);
    // 活跃人群：5\65\68\127
    private static final Set<Integer> ACTIVE_USERS_ID = Sets.newHashSet(5, 65, 68, 127);

    private BitSet vipUsers = new BitSet();
    private BitSet activeUsers = new BitSet();


    @Before
    public void init() {
        VIP_USERS_ID.forEach(userId -> vipUsers.set(userId, true));
        ACTIVE_USERS_ID.forEach(userId -> activeUsers.set(userId, true));
    }


    /**
     * 提取出既是VIP又是活跃的会员
     * {5, 65, 127}
     */
    @Test
    public void getVipAndActiveUsers() {
        vipUsers.and(activeUsers);
        System.out.println(vipUsers);
        System.out.println(vipUsers.cardinality());
    }


    /**
     * 提取出VIP和活跃两部分会员，但是要保证不重复
     * {1, 3, 5, 63, 65, 67, 68, 69, 127}
     */
    @Test
    public void getVipOrActiveUsers() {
        vipUsers.or(activeUsers);
        System.out.println(vipUsers);
    }

    /**
     * VIP人群中不活跃的会员
     * {1, 3, 63, 67, 69}
     */
    @Test
    public void getVIPAndNotActiveUsers() {
        vipUsers.andNot(activeUsers);
        System.out.println(vipUsers);
    }


}
