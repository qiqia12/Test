package com.design.factory;

/**
 * @projectName: Test
 * @package: com.design.factory
 * @className: Circle
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:12
 * @version: 1.0
 */
public class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Circle 实现 drown方法");
    }
}
