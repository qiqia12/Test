package com.design.factory;

/**
 * @projectName: Test
 * @package: com.design.factory
 * @className: Square
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:11
 * @version: 1.0
 */
public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Square 调用 drow方法");
    }
}
