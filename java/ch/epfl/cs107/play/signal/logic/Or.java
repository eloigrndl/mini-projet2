package ch.epfl.cs107.play.signal.logic;

public class Or extends LogicSignal {

    private Logic a;
    private Logic b;

    public Or(Logic a, Logic b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean isOn() {
        if((a != null && b != null) && (a.isOn() || b.isOn())){
            return true;
        }else{
            return false;
        }
    }
}
