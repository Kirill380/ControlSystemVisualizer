package com.redkite.controlengineering.visualizer.model;

import lombok.Data;

@Data
public class Parameters {

    private Double k1;

    private Double k2;

    private Double k3;

    private Double t;

    private Double ksi;

    private Functions function;

    private int from;

    private int to;


    private double step;


    public static Parameters getDefault() {
        Parameters parameters = new Parameters();
        parameters.setK1(1.0);
        parameters.setK2(1.0);
        parameters.setK3(2.0);
        parameters.setT(100.0);
        parameters.setKsi(1.0);
        parameters.setFunction(Functions.SIN);
        parameters.setFrom(1);
        parameters.setTo(30);
        parameters.setStep(0.1);
        return parameters;
    }

}
