package com.yujunquan.studylife.toolbox.common.util;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author yujunquan
 * @Description 描述  AsposeWords 20.5 版本 javassist 破解部分
 * @createTime 2020-06-07 19:47:47
 */
public class AsposeWordsCrack20_5 {
    public static void main(String[] args) {
        try {
            AsposeWordsCrack20_5.changeMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeMethod() throws Exception {
//        ClassPool.getDefault().insertClassPath("E:\\test\\JByteMod205.jar");
        ClassPool.getDefault().insertClassPath("E:\\test\\202006081459.jar");
        // 方法一
        changeMethod1();
        // 方法二
        //changeMethod2();

    }
    public static void changeMethod1() throws Exception{
        CtClass c2 = ClassPool.getDefault()
                .getCtClass("com.aspose.words.zzZJA");
        CtMethod[] ms = c2.getDeclaredMethods();
        for (CtMethod c : ms) {
            System.out.println("method name: " + c.getName() + "() ,Parameter:");
            CtClass[] ps = c.getParameterTypes();
//            if(c.getName().equals("zzZ")){
//                for (CtClass p : ps) {
//                    String name = p.getName();
//                    System.out.println(name);
//                }
//            }
            if (c.getName().equals("zzZ") && ps.length == 3
                    && ps[0].getName().equals("org.w3c.dom.Node")
                    && ps[1].getName().equals("org.w3c.dom.Node")
                    && ps[2].getName().equals("java.lang.String")) {
                System.out.println("I got you！zzZ");
                c.insertBefore("{return true;}");
            }
            if (c.getName().equals("zzZ") && ps.length == 3
                    && ps[0].getName().equals("byte[]")
                    && ps[1].getName().equals("byte[]")
                    && ps[2].getName().equals("java.lang.String")) {
                System.out.println("I got you！zzZ");
                c.insertBefore("{return true;}");
            }
            if (c.getName().equals("zzU") && ps.length == 1
                    && ps[0].getName().equals("java.io.InputStream")) {
                System.out.println("I got you！zzU");
                c.insertBefore("{return true;}");
            }

            if (c.getName().equals("zzZbg")) {
                System.out.println("I got you！zzZbg");
                c.insertBefore("{return 1;}");
            }

            if (c.getName().equals("zzZbf")) {
                System.out.println("I got you！zzZbf");
                c.insertBefore("{return 1;}");
            }

        }
        //输出到当前目录下
        c2.writeFile();

    }
    public static void changeMethod2() throws Exception{
        CtClass c2 = ClassPool.getDefault()
                .getCtClass("com.aspose.words.internal.zzON");
        CtMethod[] ms = c2.getDeclaredMethods();
        for (CtMethod c : ms) {
            System.out.println("method name: " + c.getName() + "() ,Parameter:");
            CtClass[] ps = c.getParameterTypes();

            if (c.getName().equals("add") && ps.length == 1) {
                System.out.println("I got you！add");
                c.insertBefore("{return true;}");
            }
        }
        //输出到当前目录下
        c2.writeFile();

    }
}
