package com.qiqi.controller;

import com.qiqi.pojo.User;
import com.sun.crypto.provider.AESKeyGenerator;
import sun.misc.Launcher;

/**
 * @projectName: Test
 * @package: com.qiqi.controller
 * @className: MyClassLoader
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/23 17:59
 * @version: 1.0
 */
public class MyClassLoader {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(AESKeyGenerator.class.getClassLoader());
        System.out.println(User.class.getClassLoader());
        System.out.println();

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootStrap = extClassLoader.getParent();
        System.out.println(appClassLoader);
        System.out.println(extClassLoader);
        System.out.println(bootStrap);
        System.out.println();
    }

    public class MyLoaderClass extends ClassLoader{
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.loadClass(name);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            return super.findClass(name);
        }
    }
}
