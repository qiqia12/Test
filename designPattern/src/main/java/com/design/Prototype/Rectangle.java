package com.design.Prototype;

/**
 * @projectName: Test
 * @package: com.design.Prototype
 * @className: Rectangle
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:17
 * @version: 1.0
 */

public class Rectangle extends Shape {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

