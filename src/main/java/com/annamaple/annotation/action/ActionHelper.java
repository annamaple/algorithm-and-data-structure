package com.annamaple.annotation.action;

import com.annamaple.utils.ClassUtil;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author xionglei
 * @create 2022-04-26 11:37
 */
public class ActionHelper {

    public static void main(String[] args) {
        ClassUtil.scanClasses(ActionHelper.class, "com.annamaple").forEach(System.out::println);
    }

    private static final String PACK = "com.annamaple";

    public void register() {
        List<String> classPathList = ClassUtil.scanClasses(ActionHelper.class, PACK);
        for (String classPath : classPathList) {
            try {
                Class<?> clazz = Class.forName(classPath);
                Action classAction = clazz.getAnnotation(Action.class);
                String urlPrefix = "";
                if (classAction != null) {
                    urlPrefix += classAction.value();
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    Action action = method.getAnnotation(Action.class);
                    if (action != null) {
                        
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
