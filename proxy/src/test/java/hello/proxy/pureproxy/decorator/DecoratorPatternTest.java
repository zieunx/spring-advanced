package hello.proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPatternTest {

	@Test
	void decorator() {
		RealComponent realComponent = new RealComponent();
		DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
		client.execute();
	}

	@Test
	void decorator1() {
		RealComponent realComponent = new RealComponent();
		MessageDecorator messageDecorator = new MessageDecorator(realComponent);
		DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

		client.execute();
	}
}
