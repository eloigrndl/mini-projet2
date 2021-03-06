package ch.epfl.cs107.play.signal.logic;

public class Not extends LogicSignal {

    //(Not) properties
    private Logic s;

    /**
     * Not Constructor
     * @param s Logic to "Not"
     */
    public Not(Logic s){
        this.s = s;
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
