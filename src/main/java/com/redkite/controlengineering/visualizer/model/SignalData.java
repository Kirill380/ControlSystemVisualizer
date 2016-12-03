package com.redkite.controlengineering.visualizer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SignalData {

    private double[][] coordinates;

    public SignalData(double[][] coordinates) {
        this.coordinates = new double[coordinates.length][2];
        for (int i = 0; i < coordinates.length; i++) {
            this.coordinates[i] = new double[2];
            for (int j = 0; j < 2; j++) {
                this.coordinates[i][j] = coordinates[i][j];
            }
        }
    }
}
