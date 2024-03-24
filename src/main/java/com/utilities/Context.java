package com.utilities;

import com.stepDefinitions.HandleRequestStructure;


public class Context {


    private static ThreadLocal<HandleRequestStructure> requestContextThreadLocal = new ThreadLocal<HandleRequestStructure>();

    public static HandleRequestStructure getRequestContextThreadLocal() {
        HandleRequestStructure handleRequestStructureObj = requestContextThreadLocal.get();
        if (handleRequestStructureObj == null) {
            handleRequestStructureObj = new HandleRequestStructure();
            requestContextThreadLocal.set(handleRequestStructureObj);
        }
        return handleRequestStructureObj;
    }

    public static void setRequestContextThreadLocal(ThreadLocal<HandleRequestStructure> requestContextThreadLocal) {
        Context.requestContextThreadLocal = requestContextThreadLocal;
    }
}
