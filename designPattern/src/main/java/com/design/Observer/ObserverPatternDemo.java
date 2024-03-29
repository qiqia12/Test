package com.design.Observer;

/**
 * @projectName: Test
 * @package: com.design.Observer
 * @className: ObserverPatternDemo
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:22
 * @version: 1.0
 */

public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}

