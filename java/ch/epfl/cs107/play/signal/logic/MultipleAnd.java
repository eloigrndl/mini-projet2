package ch.epfl.cs107.play.signal.logic;

import java.util.List;

public class MultipleAnd extends LogicSignal {

    //(MultipleAnd) properties
    private List<Logic> signals;

    /**
     * MultipleAnd Constructor
     * @param signals List of Logic
     */
    public MultipleAnd(List<Logic> signals) {
        this.signals = signals;
    }

    @Override
    public boolean isOn() {

        for(int i = 0; i< signals.size(); ++i){
            Logic signal = signals.get(i);
            if(!signal.isOn()){
                return false;
            }
        }
        return true;
    }
}
