package com.design.Prototype;

/**
 * @projectName: Test
 * @package: com.design.Prototype
 * @className: Circle
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:17
 * @version: 1.0
 */

public class Circle extends Shape {

    public Circle(){
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

