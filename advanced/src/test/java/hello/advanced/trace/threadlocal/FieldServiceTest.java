package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA  = () -> {
            fieldService.logic("userA");
        };

        Runnable userB  = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thead-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thead-B");

        threadA.start();
//        sleep(2000); // 동시성문제 발생하지 않도록
        sleep(100); // 동시성문제 발생!
        threadB.start();
        sleep(3000); // 메인스레드 종료 대기

        /*
        * 스프링빈처럼 싱글톤 객체의 필드를 변경하며 사용할 때 이러한 동시성 문제를 조심해야한다.
        * */
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
