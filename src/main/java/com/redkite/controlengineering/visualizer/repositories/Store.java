package com.redkite.controlengineering.visualizer.repositories;


import com.redkite.controlengineering.visualizer.model.Parameters;
import com.redkite.controlengineering.visualizer.model.SignalData;

public interface Store {

    SignalData getInputSignal();

    SignalData getOutputSignal();

    Parameters getParameters();

    void storeParameters(Parameters parameters);

    void storeOutPutSignal(SignalData signal);

}
