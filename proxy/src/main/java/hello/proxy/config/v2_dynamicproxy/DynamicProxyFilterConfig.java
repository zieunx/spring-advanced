
package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

	private static final String[] PATTERNS = {
			"request*",
			"order*",
			"save*"
	};

	@Bean
	public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
		OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

		OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(orderRepository.getClass().getClassLoader(),
			new Class[] {OrderRepositoryV1.class},
			new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS)); // target이 호출하는 곳 마다 다 다르기 때문에 생성자 주입.

		return proxy;
	}

	@Bean
	public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
		OrderServiceV1Impl orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

		OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(orderService.getClass().getClassLoader(),
			new Class[] {OrderServiceV1.class},
			new LogTraceFilterHandler(orderService, logTrace, PATTERNS));

		return proxy;
	}

	@Bean
	public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
		OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));

		OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(orderController.getClass().getClassLoader(),
			new Class[] {OrderControllerV1.class},
			new LogTraceFilterHandler(orderController, logTrace, PATTERNS));

		return proxy;
	}
}
