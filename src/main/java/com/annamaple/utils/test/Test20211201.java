package com.annamaple.utils.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.*;

/**
 * @author xionglei
 * @create 2021-12-01 10:02
 */
public class Test20211201 {

    private static final String OLD = "1";
    private static final String NEW = "2";
    private static final String MODIFY_TYPE = "3";

    public static void main(String[] args) {
        FmZt fmZt = new FmZt();
        fmZt.setPtyClass("负面主体类型编码");
        fmZt.setAjBh("按键编号");
        fmZt.setDeptCode("外汇部门");
        fmZt.setHwClock(new Date());
        fmZt.setFkAmt(BigDecimal.valueOf(111.22));

        FmZt oldFmZt = new FmZt();
        oldFmZt.setPtyClass("  负面主体类型编码".trim());
        oldFmZt.setAjBh("原来的---按键编号");
        oldFmZt.setDeptCode("原来的---外汇部门");
        oldFmZt.setHwDj("原来的外汇等级");

        checkUpdateFiled(fmZt, oldFmZt);
        System.out.println(oldFmZt);
    }

    private static List<Map<String, String>> checkUpdateFiled(FmZt fmZt, FmZt oldFmZt) {
        if (fmZt == null || oldFmZt == null) {
            return null;
        }
        List<Map<String, String>> list = new LinkedList<>();
        for (FmZtModifyColumn modifyColumn : FmZtModifyColumn.values()) {
            StringBuilder methodName = new StringBuilder();
            methodName.append("get")
                    .append(modifyColumn.getMethodName().substring(0, 1).toUpperCase())
                    .append(modifyColumn.getMethodName().substring(1));
            try {
                Object newValue = FmZt.class.getMethod(methodName.toString()).invoke(fmZt);
                Object oldValue = FmZt.class.getMethod(methodName.toString()).invoke(oldFmZt);
                if (!Objects.equals(newValue, oldValue)) {
                    String setMethodName = "set" +
                            modifyColumn.getMethodName().substring(0, 1).toUpperCase() +
                            modifyColumn.getMethodName().substring(1);
                    
                    
//                    System.out.println(modifyColumn.getDesc() + "====" + newValue + " - " + oldValue);
                    if (newValue == null) {
                        Method method = FmZt.class.getMethod(setMethodName, String.class);
                        System.out.println("null");
                        method.invoke(oldFmZt, (String) null);
                    } else if (newValue instanceof BigDecimal){
                        Method method = FmZt.class.getMethod(setMethodName, BigDecimal.class);
                        System.out.println(newValue.toString());
                        method.invoke(oldFmZt, BigDecimal.valueOf(1234.11));
                    } else if (newValue instanceof Date){
                        Method method = FmZt.class.getMethod(setMethodName, Date.class);
                        DateFormat format = DateFormat.getDateTimeInstance();
                        System.out.println("newValue:" + format.format(newValue));
                        method.invoke(oldFmZt, (Date) newValue);
                    } else {
                        Method method = FmZt.class.getMethod(setMethodName, String.class);
                        System.out.println(newValue.toString());
                        method.invoke(oldFmZt, newValue.toString());
                    }
                    
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean equals(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj1);
    }
}
