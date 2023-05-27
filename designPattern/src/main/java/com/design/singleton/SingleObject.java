package com.design.singleton;

/**
 * @projectName: Test
 * @package: com.design.singleton
 * @className: SingleObject
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:15
 * @version: 1.0
 */
public class SingleObject {
    //创建 SingleObject 的一个对象
    private static SingleObject instance = new SingleObject();
    private SingleObject(){}

    //获取唯一可用的对象
    public static SingleObject getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}
