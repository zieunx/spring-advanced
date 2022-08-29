package pureproxy.decorator;

import org.junit.jupiter.api.Test;

import pureproxy.decorator.code.DecoratorPatternClient;
import pureproxy.decorator.code.MessageDecorator;
import pureproxy.decorator.code.RealComponent;
import pureproxy.decorator.code.TimeDecorator;
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

	@Test
	void decorator2() {
		RealComponent realComponent = new RealComponent();
		MessageDecorator messageDecorator = new MessageDecorator(realComponent);
		TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
		DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

		client.execute();
	}
}
