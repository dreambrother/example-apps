package com.github.dreambrother;

import java.lang.instrument.Instrumentation;

public class ObjectSizeGetter {

    private static Instrumentation instrumentation;

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        ObjectSizeGetter.instrumentation = instrumentation;
    }

    public static long getObjectSize(Object object) {
        return instrumentation.getObjectSize(object);
    }
}
