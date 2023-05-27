package com.design.singleton;

/**
 * @projectName: Test
 * @package: com.design.singleton
 * @className: SingletonPatternDemo
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:15
 * @version: 1.0
 */
public class SingletonPatternDemo {
    public static void main(String[] args) {
        //获取唯一可用的对象
        SingleObject object = SingleObject.getInstance();
        //显示消息
        object.showMessage();
    }
}
