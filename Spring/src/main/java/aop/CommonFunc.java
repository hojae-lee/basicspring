package aop;

import org.aspectj.lang.ProceedingJoinPoint;

//공통기능을 수행할 클래스정의


public class CommonFunc {

	public Object commonJob(ProceedingJoinPoint joinPoint) {
		Object proxy = null;
		/*
		 현재 호출되는 메소드명을 문자열의 형태로 변환 후 반환해준다. 즉 현재 실행되는
		 메소드명을 로그로 출력 할 수 있따.
		 */
		String joinSignStr = joinPoint.getSignature().toShortString();
		
		/*
		 advice를 around로 지정한 경우 공통기능 수행부분으로 "핵심기능 수행전에 해당"
		 */
		System.out.println("핵싱기능 수행진="+joinSignStr);
		
		try {
			proxy = joinPoint.proceed();
			System.out.println("핵심기능 수행함 joinPoint.proceed()");
		} 
		catch (Exception e) {
			/*
			 advice를 around로 지정할 경우 공통기능 수행부분. "예외발생시"에 해당함.
			 */
			e.printStackTrace();
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("핵심기능수행후="+joinSignStr);
		return proxy;
	}
	
}
