package com.redkite.controlengineering.visualizer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SignalData {

    private double[] dots;

    public SignalData(double[] dots) {
        this.dots = new double[dots.length];
        System.arraycopy(dots, 0, this.dots, 0, dots.length);
    }
}
