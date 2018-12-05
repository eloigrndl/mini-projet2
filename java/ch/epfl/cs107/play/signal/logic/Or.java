package ch.epfl.cs107.play.signal.logic;

public class Or extends LogicSignal {

    private Logic a;
    private Logic b;

    @Override
    public boolean isOn() {
        if((a != null && b != null) && (a.isOn() || b.isOn())){
            return true;
        }else{
            return false;
        }
    }
}
