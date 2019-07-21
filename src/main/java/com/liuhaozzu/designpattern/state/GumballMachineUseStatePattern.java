package com.liuhaozzu.designpattern.state;

/**
 * @Author Administrator
 * @create 2019/5/12 0012 10:41
 */
public class GumballMachineUseStatePattern {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;

    State state=soldOutState;
    int count = 0;

    public GumballMachineUseStatePattern(int numberGumballs) {
        this.count = numberGumballs;
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        this.count=numberGumballs;
        if (numberGumballs > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
    }

    public GumballMachineUseStatePattern setState(State state) {
        this.state = state;
        return this;
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count--;
        }
    }
}
