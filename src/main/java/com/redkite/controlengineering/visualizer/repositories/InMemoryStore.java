package com.redkite.controlengineering.visualizer.repositories;

import com.redkite.controlengineering.visualizer.model.Parameters;
import com.redkite.controlengineering.visualizer.model.SignalData;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.IntStream;

@Component
public class InMemoryStore implements Store {

    private ConcurrentMap<String, Object> store = new ConcurrentHashMap<>();
    private final String OUTPUT_SIGNAL = "output_signal";
    private final String INPUT_SIGNAL = "input_signal";
    private final String PARAMETERS = "parameters";


    @PostConstruct
    public void init() {
        Parameters p = Parameters.getDefault();
        storeParameters(p);
        storeOutPutSignal(functionToSignalData(p.getFrom(), p.getTo(), p.getStep(), p.getFunction().d()));
    }

    @Override
    public SignalData getInputSignal() {
        Parameters p = getParameters();
        return functionToSignalData(p.getFrom(), p.getTo(), p.getStep(), p.getFunction().f());
    }

    @Override
    public SignalData getOutputSignal() {
        return (SignalData) store.get(OUTPUT_SIGNAL);
    }

    @Override
    public Parameters getParameters() {
        return (Parameters) store.get(PARAMETERS);
    }

    @Override
    public void storeParameters(Parameters parameters) {
        store.put(PARAMETERS, parameters);
    }

    @Override
    public void storeOutPutSignal(SignalData signal) {
        store.put(OUTPUT_SIGNAL, signal);
    }

    private SignalData functionToSignalData(int from, int to, double step, Function<Double, Double> f) {
        return new SignalData(IntStream
                .range(from, (int) ((to - from) / step))
                .mapToDouble(v -> v * step)
                .map(f::apply)
                .toArray()
        );
    }
}
