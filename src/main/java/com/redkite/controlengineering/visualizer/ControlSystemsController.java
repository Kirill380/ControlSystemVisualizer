package com.redkite.controlengineering.visualizer;

import com.redkite.controlengineering.visualizer.model.Parameters;
import com.redkite.controlengineering.visualizer.model.SignalData;
import com.redkite.controlengineering.visualizer.repositories.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControlSystemsController {

    @Autowired
    private Store store;

    @RequestMapping(value = "/signal/input", method = RequestMethod.GET)
    public SignalData getInputSignal() {
        return store.getInputSignal();
    }

    @RequestMapping(value = "/signal/output", method = RequestMethod.GET)
    public SignalData getOutputSignal() {
        return store.getOutputSignal();
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public void calculate(@RequestBody Parameters parameters) {

    }
}
