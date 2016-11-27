package com.redkite.controlengineering.visualizer.model;


import java.util.function.Function;

public enum Functions {
    SIN(Math::sin, Math::cos),
    EXP(Math::exp, Math::exp),
    COS(Math::cos, v -> -Math.sin(v));

    private final Function<Double, Double> function;
    private final Function<Double, Double> derivative; //TODO refactor using DerivativeStructure

    Functions(Function<Double, Double> function, Function<Double, Double> derivative) {
        this.function = function;
        this.derivative = derivative;
    }

    public Function<Double, Double> f() {
        return function;
    }

    public Function<Double, Double> d() {
        return derivative;
    }

}
