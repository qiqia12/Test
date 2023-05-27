package com.design.Observer;

/**
 * @projectName: Test
 * @package: com.design.Observer
 * @className: Observer
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:21
 * @version: 1.0
 */

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

