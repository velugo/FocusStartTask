package com.velugo;

public class Main {

    public static void main(String[] args) {
        /*GUI calcGUI = new GUI();
        calcGUI.go();*/

        Calculator calculator = new Calculator();
        System.out.println(calculator.evaluate("254651.1511+0234"));
    }
}
