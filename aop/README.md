
# 스프링 AOP - 포인트 컷

## this 와 target
> this 와 target 지시자는 단독으로 사용되기 보다는 파라미터 바인딩에서 주로 사용된디ㅏ.

`target`은 실제 target 객체를 대상으로 포인트컷을 매칭한다.  
`this` 는 스프링 빈으로 등록되어있는 프록시 객체를 대상으로 포인트컷 매칭을 한다.


**`this`를 사용할 때 다음의 주의가 필요함.**   
 `JDK 동적 프록시`를 기반으로 생성하는지 `CGLIB`를 기반으로 생성하는지에 따라 타겟을 알 수 있는지 없는지가 달라진다.

다음처럼 `JDK 동적 프록시`를 기반으로 생성하도록 설정하고 `this`로 구현체를 찾으려고 하는 경우 advice 가 적용되지 않는다.

<테스트 클래스의 프로퍼티 설정>
```java
@SpringBootTest(properties = "spring.aop.proxy-target-class = true")
public class ThisTargetTest {
    // 생략
}
```
<포인트컷 지시자>
```java
@Around("this(hello.aop.member.MemberServiceImpl)")
public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("[this-impl] {}", joinPoint.getSignature());
    return joinPoint.proceed();
}
```

<결과>
```
// [this-impl] 출력되지 않음.
[target-impl] String hello.aop.member.MemberService.hello(String)
[target-interface] String hello.aop.member.MemberService.hello(String)
[this-interface] String hello.aop.member.MemberService.hello(String)
```

그 이유는 생성 방식을 고민해보면 알 수 있다.  
동적프록시로 생성되는 경우, 인터페이스기반으로 프록시객체를 생성한다. 그래서 구체클래스를 알 수 없다.  
CGLIB는 구체클래스를 기반으로 프록시를 생성하기 때문에 구체클래스도 알 수 있고 인터페이스도 알 수 있다.(상위 객체)


## 실무 주의사항

### 내부호출문제
클라이언트에서 스프링 빈을 호출할 때 등록된 `프록시 객체`를 호출한다.  
`프록시 객체`는 자신의 역할을 수행하고 실제 `타겟 객체`를 호출하게 된다.  
`타겟 객체` 가 내부 호출을 하게 되면 프록시를 거치지 않게 된다.    
**(강의자료 참고)**

> @Transactional 또한 스프링 프록시 기술 기반인데 내부호출문제가 발생한다.
> 같은 이유인지 공부해보아야겠다.

