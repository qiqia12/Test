package com.design.Prototype;

/**
 * @projectName: Test
 * @package: com.design.Prototype
 * @className: PrototypePatternDemo
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:18
 * @version: 1.0
 */

public class PrototypePatternDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}

