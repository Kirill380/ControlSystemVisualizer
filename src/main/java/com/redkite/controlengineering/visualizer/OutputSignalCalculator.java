package com.redkite.controlengineering.visualizer;


import com.redkite.controlengineering.visualizer.model.Parameters;
import com.redkite.controlengineering.visualizer.model.SignalData;
import com.redkite.controlengineering.visualizer.ode.ControlSystemODEV1;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.springframework.stereotype.Component;

@Component
public class OutputSignalCalculator {

    public SignalData calculate(Parameters parameters) {
        FirstOrderIntegrator integrator = new ClassicalRungeKuttaIntegrator(parameters.getStep());
        int size = (int) ((parameters.getTo() - parameters.getFrom()) / parameters.getStep());
        double[] result = new double[size];
        double[] time = new double[size];
        StepHandler stepHandler = new StepHandler() {

            private int iteration = 0;

            public void init(double t0, double[] y0, double t) {
            }

            public void handleStep(StepInterpolator interpolator, boolean isLast) {
                double t = interpolator.getCurrentTime();
                double[] y = interpolator.getInterpolatedState();
                result[iteration] = y[0];
                time[iteration] = t;
                iteration++;
            }
        };

        ControlSystemODEV1 systemV1 = new ControlSystemODEV1(parameters);
        double[] y0 = new double[]{0.0, 0.0};
        integrator.addStepHandler(stepHandler);
        integrator.integrate(systemV1, parameters.getFrom(), y0, parameters.getTo(), y0);
        double[][] coordinates = new double[result.length][2];
        for (int i = 0; i < result.length; i++) {
            coordinates[i] = new double[2];
            coordinates[i][0] = time[i];
            coordinates[i][1] = result[i];
        }
        return new SignalData(coordinates);
    }
}
