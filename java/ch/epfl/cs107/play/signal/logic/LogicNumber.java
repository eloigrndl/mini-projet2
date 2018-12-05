package ch.epfl.cs107.play.signal.logic;

import java.util.List;

public class LogicNumber extends LogicSignal {

    private float nb;
    private List<Logic> e;

    @Override
    public boolean isOn() {

        if(e.size()>12 || this.nb <0 || nb > Math.pow(2,e.size())) {
            return false;
        }

        int sum = 0;

        for(int i =0; i < e.size(); ++i) {
            Logic signal = e.get(i);
            if(signal.isOn()){
                sum += Math.pow(2,i);
            }
        }

        if(sum == nb) {
            return true;
        }

        return false;
    }
}
