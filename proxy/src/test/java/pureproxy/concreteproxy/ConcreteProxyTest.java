package pureproxy.concreteproxy;

import org.junit.jupiter.api.Test;

import pureproxy.concreteproxy.code.ConcreteLogic;
import pureproxy.concreteproxy.code.ConcreteClient;
import pureproxy.concreteproxy.code.TimeProxy;

public class ConcreteProxyTest {

	@Test
	void noProxy() {
		ConcreteLogic concreteLogic = new ConcreteLogic();
		ConcreteClient concreteClient = new ConcreteClient(concreteLogic);
		concreteClient.execute();
	}

	@Test
	void addProxy() {
		ConcreteLogic concreteLogic = new ConcreteLogic();
		TimeProxy timeProxy = new TimeProxy(concreteLogic);
		ConcreteClient concreteClient = new ConcreteClient(timeProxy);
		concreteClient.execute();
	}
}
