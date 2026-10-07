package G_Aop.A_Aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

   /* @Around("execution(* G_Aop.A_Aop.service.*.*(..))")
    public Object logMethodDetails(ProceedingJoinPoint joinPoint) throws Throwable
    {
        logger.info("Entering method:"+ joinPoint.getSignature());
        logger.info("arguments:"+ Arrays.toString(joinPoint.getArgs()));

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("exiting method:"+joinPoint.getSignature());
        logger.info("execution time :"+ (endTime-startTime)+ "ms");

        return result;
    }*/

    private final ThreadLocal<Long> stime = new ThreadLocal<>();

    @Before("execution(* G_Aop.A_Aop.service.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint)
    {
        stime.set(System.currentTimeMillis());

        logger.info("Entering method:"+ joinPoint.getSignature());
        logger.info("arguments:"+ Arrays.toString(joinPoint.getArgs()));
    }


    @AfterReturning("execution(* G_Aop.A_Aop.service.*.*(..))")
    public void logMethodExit(JoinPoint joinPoint)
    {
        logger.info("exiting method:"+joinPoint.getSignature());
    }

    @After("execution(* G_Aop.A_Aop.service.*.*(..))")
    public void logExecutionTime(JoinPoint joinPoint)
    {
        Long start = stime.get();
        if(start!= null)
        {
            long end = System.currentTimeMillis();
            logger.info("execution time :"+ (end-start)+ "ms");
            stime.remove();
        }
    }

}
