package com.design.Observer;

/**
 * @projectName: Test
 * @package: com.design.Observer
 * @className: Subject
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:20
 * @version: 1.0
 */

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers
            = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

