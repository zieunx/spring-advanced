package hello.proxy.jdcdinamic;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionTest {

	@Test
	void reflection0() {
		Hello target = new Hello();

		// 공통 로직1 시작
		String result1 = target.callA();
		log.info("result={}", result1);
		// 공통 로직1 종료

		// 공통 로직2 시작
		String result2 = target.callB();
		log.info("result={}", result1);
		// 공통 로직2 종료
	}

	@Slf4j
	static class Hello {
		public String callA() {
			log.info("callA");
			return "A";
		}
		public String callB() {
			log.info("callB");
			return "B";
		}
	}
}
