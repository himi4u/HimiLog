package com.himi.loglibrary.util;

public class HimiStackTraceUtil {
    /**
     * 获取排除包名以及裁剪后的堆栈信息
     *
     * @param stackTraceElements
     * @param ignorePackageName
     * @param maxDepth
     * @return
     */
    public static StackTraceElement[] getCroppedStackTrace(StackTraceElement[] stackTraceElements, String ignorePackageName, int maxDepth) {
        return cropStackTrace(getRealStackTrace(stackTraceElements, ignorePackageName), maxDepth);
    }

    /**
     * 获取除传入包名以外的堆栈信息
     *
     * @param stackTraceElements
     * @param ignorePackageName
     * @return
     */
    private static StackTraceElement[] getRealStackTrace(StackTraceElement[] stackTraceElements, String ignorePackageName) {
        int stackDepth = stackTraceElements.length;
        int ignoreDepth = 0;
        String className;
//        for (int i = 0; i < stackDepth; i++) {
//            className = stackTraceElements[i].getClassName();
//            if (className != null && className.equals(ignorePackageName)) {
//                ignoreDepth++;
//            }
//        }
        for (int i = stackDepth - 1; i > 0; i--) {
            className = stackTraceElements[i].getClassName();
            if (className != null && className.startsWith(ignorePackageName)) {
                ignoreDepth = i + 1;
            }
        }
        int realDepth = stackDepth - ignoreDepth;
        StackTraceElement[] realStackElements = new StackTraceElement[realDepth];
        System.arraycopy(stackTraceElements, ignoreDepth, realStackElements, 0, realDepth);
        return realStackElements;
    }

    /**
     * 裁剪 堆栈
     *
     * @param stackTraceElements 原堆栈
     * @param maxDepth           最大堆栈深度
     * @return
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] stackTraceElements, int maxDepth) {
        int stackDepth = stackTraceElements.length;
        if (maxDepth > 0) {
            stackDepth = Math.min(stackDepth, maxDepth);
        }
        StackTraceElement[] realStack = new StackTraceElement[stackDepth];
        System.arraycopy(stackTraceElements, 0, realStack, 0, stackDepth);
        return realStack;
    }
}
