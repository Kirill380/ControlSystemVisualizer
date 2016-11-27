package com.redkite.controlengineering.visualizer;


import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;

public class Main {

    public static void main(String[] args) {
        int params = 1;
        int order = 1;
        double xRealValue = 3;
        DerivativeStructure x = new DerivativeStructure(params, order, 0, xRealValue);
        DerivativeStructure sin = x.pow(2);
        System.out.println("Value: " + sin.getValue());
        System.out.print("Derivative: " + sin.getPartialDerivative(1));
    }
}
