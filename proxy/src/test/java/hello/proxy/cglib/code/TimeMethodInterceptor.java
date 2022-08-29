package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private Object target;

    public TimeMethodInterceptor(Object object) {
        this.target = object;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime =  System.currentTimeMillis();

        Object result = methodProxy.invoke(target, args);
        // cglib는 method.invoke(target, args); 보다 위의 방법을 권장하고 있다.

        long endTime =  System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeProxy 종료. resultTime={}", resultTime);
        return result;
    }
}
