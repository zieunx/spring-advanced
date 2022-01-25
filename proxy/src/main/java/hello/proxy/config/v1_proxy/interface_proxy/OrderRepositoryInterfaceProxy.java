package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

	private final OrderRepositoryV1 target; // 프록시에서는 보통 호출대상을 target 으로 설정
	private final LogTrace logTrace;

	@Override
	public void save(String itemId) {
		TraceStatus status = null;
		try {
			status = logTrace.begin("OrderRepository.request()");
			target.save(itemId);
			logTrace.end(status);
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
