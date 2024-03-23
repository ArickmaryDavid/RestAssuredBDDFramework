package com.utilities;

import com.stepDefinitions.RequestContext;
import org.testng.log4testng.Logger;


public class Context {


    private static ThreadLocal<RequestContext> requestContextThreadLocal=new ThreadLocal<RequestContext>();

    public static RequestContext getRequestContextThreadLocal() {
        RequestContext requestContextObj=requestContextThreadLocal.get();
        if (requestContextObj==null){
            requestContextObj=new RequestContext();
            requestContextThreadLocal.set(requestContextObj);
        }
        return requestContextObj;
    }

    public static void setRequestContextThreadLocal(ThreadLocal<RequestContext> requestContextThreadLocal) {
        Context.requestContextThreadLocal = requestContextThreadLocal;
    }
}
