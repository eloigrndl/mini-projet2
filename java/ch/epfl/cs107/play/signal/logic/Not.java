package ch.epfl.cs107.play.signal.logic;

public class Not extends LogicSignal {

    private Logic s;

    public Not(Logic s){
        if(s == TRUE){
            this.s = FALSE;
        }else{
            this.s = TRUE;
        }
    }

    @Override
    public boolean isOn() {
        if(s != null && !s.isOn()){
            return true;
        }else{
            return false;
        }
    }
}
