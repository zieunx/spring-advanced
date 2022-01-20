package hello.proxy.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;

@Configuration
public class AppV2Config {

	@Bean
	public OrderControllerV2 orderControllerV2() {
		return new OrderControllerV2(orderServiceV2());
	}

	@Bean
	public OrderServiceV2 orderServiceV2() {
		return new OrderServiceV2(orderRepositoryV2());
	}

	@Bean
	public OrderRepositoryV2 orderRepositoryV2() {
		return new OrderRepositoryV2();
	}
}
