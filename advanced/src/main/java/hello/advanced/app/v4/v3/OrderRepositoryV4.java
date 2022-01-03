package hello.advanced.app.v4.v3;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

	private final LogTrace trace;

	public void save(String itemId) {
		AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
			@Override
			protected Void call() {
				if (itemId.equals("ex")) {
					throw new IllegalStateException("예외발생!");
				}
				sleep(1000);
				return null;
			}
		};
		template.execute("OrderRepository.save()");
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
