/**
 * @author ulei
 * @date 2018/10/9
 */
public class PlayFootball extends AbstractPlay {
    public PlayFootball(String name) {
        super(name);
    }

    @Override
    public void play() {
        System.out.println(this.getName());
    }


}
