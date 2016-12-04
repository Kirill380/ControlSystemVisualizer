package com.redkite.controlengineering.visualizer;

import com.redkite.controlengineering.visualizer.model.Parameters;
import com.redkite.controlengineering.visualizer.model.SignalData;
import com.redkite.controlengineering.visualizer.repositories.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControlSystemsController {

    @Autowired
    private Store store;

    @Autowired
    private OutputSignalCalculator calculator;

    @RequestMapping(value = "/signal/input", method = RequestMethod.GET)
    public SignalData getInputSignal() {
        return store.getInputSignal();
    }

    @RequestMapping(value = "/signal/output", method = RequestMethod.GET)
    public SignalData getOutputSignal() {
        return store.getOutputSignal();
    }


    @RequestMapping(value = "/signal/parameters", method = RequestMethod.GET)
    public Parameters getParameters() {
        return store.getParameters();
    }


    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public ResponseEntity<String> calculate(@RequestBody Parameters parameters) {
        Result result = validate(parameters);
        if (!result.valid) {
            return new ResponseEntity<String>(result.message, HttpStatus.BAD_REQUEST);
        }
        SignalData outputSignal = calculator.calculate(parameters);
        store.storeParameters(parameters);
        store.storeOutPutSignal(outputSignal);
        return new ResponseEntity<String>(result.message, HttpStatus.OK);
    }

    @RequestMapping(value = "/signal/default", method = RequestMethod.POST)
    public void resetToDefault() {
        SignalData outputSignal = calculator.calculate(Parameters.getDefault());
        store.storeParameters(Parameters.getDefault());
        store.storeOutPutSignal(outputSignal);
    }


    //TODO replace with validators
    private Result validate(Parameters parameters) {
        if (parameters.getStep() <= 0) {
            return new Result(false, "Parameter Step must be greater than 0");
        }

        if(parameters.getFrom() < 0) {
            return new Result(false, "Parameter From must be greater than or equal to 0");
        }

        if(parameters.getTo() <= 0) {
            return new Result(false, "Parameter TO must be greater than 0");
        }

        if (parameters.getFrom() > parameters.getTo()) {
            return new Result(false, "Parameter From must be less then To");
        }

        if (parameters.getT() == 0) {
            return new Result(false, "Parameter T can't be equal to 0");
        }

        return new Result(true, "Success");
    }


    public static class Result {
        final boolean valid;
        final String message;

        public Result(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }
    }
}
