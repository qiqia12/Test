package com.design.factory;

/**
 * @projectName: Test
 * @package: com.design.factory
 * @className: ShapeFactory
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:13
 * @version: 1.0
 */
public class ShapeFactory {
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }  else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
