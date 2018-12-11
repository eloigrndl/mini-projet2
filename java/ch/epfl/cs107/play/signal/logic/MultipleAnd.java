package ch.epfl.cs107.play.signal.logic;

import java.util.List;

public class MultipleAnd extends LogicSignal {

    private List<Logic> signals;

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
