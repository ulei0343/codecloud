/**
 * @author ulei
 * @date 2018/10/9
 */
public abstract class AbstractPlay {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractPlay(String name) {
    }

    public abstract void play();
}
