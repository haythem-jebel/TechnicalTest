package com.haythem.atos.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 
 * @author HJEBEL
 * 
 *         This class follows AOP. It executes before and after any method
 *         inside the package com.haythem to provide the execution time of each
 *         of it.
 */
@Aspect
@Component
public class LoggingAspect {
	private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

	@Around("execution(* com.haythem..*(..)))")
	public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		final StopWatch stopWatch = new StopWatch();

		// calculate method execution time
		stopWatch.start();
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();

		// Log method execution time
		LOGGER.info("Execution time of " + methodSignature.getDeclaringType().getSimpleName() // Class Name
				+ "." + methodSignature.getName() + " " // Method Name
				+ ":: " + stopWatch.getTotalTimeMillis() + " ms");

		return result;
	}
}
