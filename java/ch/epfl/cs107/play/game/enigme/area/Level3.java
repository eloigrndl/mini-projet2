package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;
import com.sun.tools.javac.util.List;

public class Level3 extends EnigmeArea {

    boolean levelBegan = false;

    @Override
    public String getTitle() {
        return "Level3";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);

        Key key = new Key(this, new DiscreteCoordinates(1,3));
        this.registerActor(key);

        Torch torch = new Torch(this, new DiscreteCoordinates(7,5), Logic.TRUE);
        this.registerActor(torch);

        PressurePlate pressurePlate = new PressurePlate(this, new DiscreteCoordinates(9,8));
        this.registerActor(pressurePlate);

        PressureSwitch pressureSwitch1 = new PressureSwitch(this, new DiscreteCoordinates(4,4));
        this.registerActor(pressureSwitch1);

        PressureSwitch pressureSwitch2 = new PressureSwitch(this, new DiscreteCoordinates(5,4));
        this.registerActor(pressureSwitch2);

        PressureSwitch pressureSwitch3 = new PressureSwitch(this, new DiscreteCoordinates(6,4));
        this.registerActor(pressureSwitch3);

        PressureSwitch pressureSwitch4 = new PressureSwitch(this, new DiscreteCoordinates(5,5));
        this.registerActor(pressureSwitch4);

        PressureSwitch pressureSwitch5 = new PressureSwitch(this, new DiscreteCoordinates(4,6));
        this.registerActor(pressureSwitch5);

        PressureSwitch pressureSwitch6 = new PressureSwitch(this, new DiscreteCoordinates(5,6));
        this.registerActor(pressureSwitch6);

        PressureSwitch pressureSwitch7 = new PressureSwitch(this, new DiscreteCoordinates(6,6));
        this.registerActor(pressureSwitch7);

        return super.begin(window, fileSystem);
    }
}
