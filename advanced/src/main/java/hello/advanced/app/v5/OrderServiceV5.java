package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

	private final OrderRepositoryV5 orderRepository;
	private final TraceTemplate template;

	public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace logTrace) {
		this.orderRepository = orderRepository;
		this.template = new TraceTemplate(logTrace);
	}

	public void orderItems(String itemId) {
		template.execute("OrderService.orderItems()", () -> {
			orderRepository.save(itemId);
			return null;
		});
	}
}
