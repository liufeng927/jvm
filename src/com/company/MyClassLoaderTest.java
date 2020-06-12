package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @description:
 * @Date: 2020-06-12 20:24
 * @author: liufeng
 **/
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader{

        private String path;

        public MyClassLoader(String path){
            this.path = path;
        }

        private byte[] loadByte(String name) throws IOException {
            name = name.replace(".","/");
            FileInputStream fis = new FileInputStream(path + "/"+ name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name,data,0,data.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
        {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t1 = System.nanoTime();
                    if(!name.startsWith("com.company")){
                        c = this.getParent().loadClass(name);
                    }else{
                        c = findClass(name);
                    }
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }

                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }

        public static void main(String[] args) throws Exception {
            MyClassLoader classLoader = new MyClassLoader("E:/test1");
            Class<?> aClass = classLoader.loadClass("com.company.User1");
            Object obj = aClass.newInstance();
            Method method = aClass.getDeclaredMethod("sout", null);
            method.invoke(obj);
            System.out.println(aClass.getClassLoader());

            MyClassLoader classLoader1 = new MyClassLoader("E:/test");
            Class<?> aClass1 = classLoader1.loadClass("com.company.User");
            Object obj1 = aClass1.newInstance();
            Method method1 = aClass1.getDeclaredMethod("sout", null);
            method1.invoke(obj1);
            System.out.println(aClass1.getClassLoader());
        }
    }

}
