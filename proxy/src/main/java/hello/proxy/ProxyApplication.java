package hello.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

// import hello.proxy.app.config.AppV1Config;
import hello.proxy.app.config.AppV2Config;

@Import(AppV2Config.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의 예제 테스트를 위해 config에 생성할 config가 읽히지 않도록 하기 위해
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
