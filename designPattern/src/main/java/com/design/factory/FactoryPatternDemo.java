package com.design.factory;

/**
 * @projectName: Test
 * @package: com.design.factory
 * @className: FactoryPatternDemo
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:13
 * @version: 1.0
 */
public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        //获取 Circle 的对象，并调用它的 draw 方法
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        //调用 Circle 的 draw 方法
        shape1.draw();

        //获取 Square 的对象，并调用它的 draw 方法
        Shape shape3 = shapeFactory.getShape("SQUARE");
        //调用 Square 的 draw 方法
        shape3.draw();
    }
}
