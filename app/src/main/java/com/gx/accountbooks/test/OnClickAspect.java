/*
package com.gx.accountbooks.test;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class OnClickAspect {

    //@Pointcut声明切入点
    //切入点是MainActivity的getTime方法，参数不限，前面的*代表返回值不限
    @Pointcut("execution(* com.gx.accountbooks.framework.ui.home.HomeFragment.getTime(..))")
    private void getTime() {

    }

    @Around("getTime()")
    public void handleGetTime(ProceedingJoinPoint point) {
        long startTime = System.currentTimeMillis();
        Log.i("TAG", "handleGetTime: " + (startTime));
        try {
            point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        Log.i("TAG", "handleGetTime: " + (endTime - startTime));
    }

}
*/
