package com.gx.task.test;

import com.tencent.mars.xlog.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.annotation.Nullable;

public class ProxyTest {

    public static <T> T create(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {
                    @Override
                    public @Nullable
                    Object invoke(Object proxy, Method method,
                                  @Nullable Object[] args) throws Throwable {
//                        Log.d("ProxyTest", "proxy" + proxy.toString());
//                        Log.d("ProxyTest", "method" + method.getName());
//                        Log.d("ProxyTest", "args" + args.length);
                        return args[0];
                    }
                });
    }
}
