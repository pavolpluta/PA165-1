package cz.muni.fi.pa165;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.concurrent.TimeUnit;

@Aspect
public class MeasurementAspect {

    private static final long NANOS_IN_MS = TimeUnit.MILLISECONDS.toNanos(1);

//    @Around("@annotation(Timed)")
//    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.nanoTime();
//        Object proceed = joinPoint.proceed();
//        long endTime = System.nanoTime();
//
//        double durationInMillis = (double)(endTime - startTime) / NANOS_IN_MS;
//        System.out.println(joinPoint.getSignature() + " executed in " + durationInMillis + "ms");
//        return proceed;
//    }

    @Around("execution(public * *(..))")
    public Object measureAspect(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.nanoTime();
        Object result = pjp.proceed();
        long endTime = System.nanoTime();

        double durationInMillis = (double)(endTime - startTime) / NANOS_IN_MS;
        System.out.printf("Method %s called, took %.3f ms %n", pjp.getSignature().getName(), durationInMillis);

        return result;
    }
}
