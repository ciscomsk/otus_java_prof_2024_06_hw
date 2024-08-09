package calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class CalcDemo {
    private static final Logger log = LoggerFactory.getLogger(CalcDemo.class);

    public static void main(String[] args) {
        long counter = 500_000_000;
        Summator summator = new Summator();
        long startTime = System.currentTimeMillis();

        for (int idx = 0; idx < counter; idx++) {
            Data data = new Data(idx);
            summator.calc(data);

            if (idx % 10_000_000 == 0) {
                log.info("{}: current idx: {}", LocalDateTime.now(), idx);
            }
        }

        long delta = System.currentTimeMillis() - startTime;
        log.info("PrevValue: {}", summator.getPrevValue());
        log.info("PrevPrevValue: {}", summator.getPrevPrevValue());
        log.info("SumLastThreeValues: {}", summator.getSumLastThreeValues());
        log.info("SomeValue: {}", summator.getSomeValue());
        log.info("Sum: {}", summator.getSum());
        log.info("spend msec: {}, sec: {}", delta, delta / 1000);
    }
}
