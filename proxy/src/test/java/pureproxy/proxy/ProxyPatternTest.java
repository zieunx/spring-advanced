package pureproxy.proxy;

import org.junit.jupiter.api.Test;

import pureproxy.proxy.code.CacheProxy;
import pureproxy.proxy.code.ProxyPatternClient;
import pureproxy.proxy.code.RealSubject;

public class ProxyPatternTest {

	@Test
	void noProxyTest() {
		RealSubject realSubject = new RealSubject();
		ProxyPatternClient client = new ProxyPatternClient(realSubject);
		client.execute();
		client.execute();
		client.execute();
	}

	@Test
	void cacheProxyTest() {
		RealSubject realSubject = new RealSubject();
		CacheProxy cacheProxy = new CacheProxy(realSubject);
		ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

		client.execute();
		client.execute();
		client.execute();
	}
}
