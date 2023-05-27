package com.design.Prototype;

/**
 * @projectName: Test
 * @package: com.design.Prototype
 * @className: Shape
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:16
 * @version: 1.0
 */

public abstract class Shape implements Cloneable {
    private String id;
    protected String type;
    abstract void draw();
    public String getType(){
        return type;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

