package club.codecloud.task.finance.task;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

/**
 * @author ulei
 * @date 2018/4/17
 */
public class GoldPriceObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        BigDecimal currentGoldPrice = (BigDecimal) arg;

    }
}
