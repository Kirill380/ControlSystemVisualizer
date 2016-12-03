package com.redkite.controlengineering.visualizer.ode;


import com.redkite.controlengineering.visualizer.model.Parameters;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

import java.util.function.Function;

import static java.lang.Math.pow;

public class ControlSystemODEV1 implements FirstOrderDifferentialEquations {

    private Double k1;

    private Double k2;

    private Double k3;

    private Double ksi;

    private Double T;

    private Function<Double, Double> derivative;


    public ControlSystemODEV1(Parameters parameters) {
        this.k1 = parameters.getK1();
        this.k2 = parameters.getK2();
        this.k3 = parameters.getK3();
        this.T = parameters.getT();
        this.ksi = parameters.getKsi();
        this.derivative = parameters.getFunction().d();
    }

    @Override
    public int getDimension() {
        return 2;
    }

    @Override
    public void computeDerivatives(double t, double[] y, double[] yDot) throws MaxCountExceededException, DimensionMismatchException {
        yDot[0] = y[1];
        yDot[1] = 0.99 * k1 * k2 * derivative.apply(t) - (0.99 * k1 * k2 * k3 + T * ksi) / pow(T, 2) * y[1] - 1 / pow(T, 2) * y[0];
    }
}
