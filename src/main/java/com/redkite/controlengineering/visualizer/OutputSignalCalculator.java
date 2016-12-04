package com.redkite.controlengineering.visualizer;


import com.redkite.controlengineering.visualizer.model.Parameters;
import com.redkite.controlengineering.visualizer.model.SignalData;
import com.redkite.controlengineering.visualizer.ode.ControlSystemODEV1;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OutputSignalCalculator {

    public SignalData calculate(Parameters parameters) {
        FirstOrderIntegrator integrator = new ClassicalRungeKuttaIntegrator(parameters.getStep());
        List<Double> result = new ArrayList<>();
        List<Double> time = new ArrayList<>();
        StepHandler stepHandler = new StepHandler() {

            public void init(double t0, double[] y0, double t) {
            }

            public void handleStep(StepInterpolator interpolator, boolean isLast) {
                double t = interpolator.getCurrentTime();
                double[] y = interpolator.getInterpolatedState();
                result.add(y[0]);
                time.add(t);
            }
        };

        ControlSystemODEV1 systemV1 = new ControlSystemODEV1(parameters);
        double[] y0 = new double[]{0.0, 0.0};
        integrator.addStepHandler(stepHandler);
        integrator.integrate(systemV1, parameters.getFrom(), y0, parameters.getTo(), y0);
        double[][] coordinates = new double[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            coordinates[i] = new double[2];
            coordinates[i][0] = time.get(i);
            coordinates[i][1] = result.get(i);
        }
        return new SignalData(coordinates);
    }
}
