package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {

    private boolean levelBegan = false;

    private Key key;

    private Torch torch;

    private PressurePlate pressurePlate;

    private PressureSwitch pressureSwitch1;
    private PressureSwitch pressureSwitch2;
    private PressureSwitch pressureSwitch3;
    private PressureSwitch pressureSwitch4;
    private PressureSwitch pressureSwitch5;
    private PressureSwitch pressureSwitch6;
    private PressureSwitch pressureSwitch7;

    private Lever lever1;
    private Lever lever2;
    private Lever lever3;

    private SignalDoor signalDoor1;

    private SignalRock signalRock1;
    private SignalRock signalRock2;
    private SignalRock signalRock3;

    @Override
    public String getTitle() {
        return "Level3";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);

        key = new Key(this, new DiscreteCoordinates(1,3));
        this.registerActor(key);

        torch = new Torch(this, new DiscreteCoordinates(7,5), Logic.TRUE);
        this.registerActor(torch);

        pressurePlate = new PressurePlate(this, new DiscreteCoordinates(9,8));
        this.registerActor(pressurePlate);

        pressureSwitch1 = new PressureSwitch(this, new DiscreteCoordinates(4,4));
        this.registerActor(pressureSwitch1);

        pressureSwitch2 = new PressureSwitch(this, new DiscreteCoordinates(5,4));
        this.registerActor(pressureSwitch2);

        pressureSwitch3 = new PressureSwitch(this, new DiscreteCoordinates(6,4));
        this.registerActor(pressureSwitch3);

        pressureSwitch4 = new PressureSwitch(this, new DiscreteCoordinates(5,5));
        this.registerActor(pressureSwitch4);

        pressureSwitch5 = new PressureSwitch(this, new DiscreteCoordinates(4,6));
        this.registerActor(pressureSwitch5);

        pressureSwitch6 = new PressureSwitch(this, new DiscreteCoordinates(5,6));
        this.registerActor(pressureSwitch6);

        pressureSwitch7 = new PressureSwitch(this, new DiscreteCoordinates(6,6));
        this.registerActor(pressureSwitch7);

        lever1 = new Lever(this, new DiscreteCoordinates(10,5));
        this.registerActor(lever1);

        lever2 = new Lever(this, new DiscreteCoordinates(9,5));
        this.registerActor(lever2);

        lever3 = new Lever(this, new DiscreteCoordinates(8,5));
        this.registerActor(lever3);

        signalDoor1 = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(3,6),
                Orientation.UP, new DiscreteCoordinates(5,9), new Circle(0.25f, new Vector(5,9)), Logic.FALSE);
        this.registerActor(signalDoor1);

        signalRock1 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(6,8), Logic.FALSE);
        this.registerActor(signalRock1);

        signalRock2 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(5,8), Logic.FALSE);
        this.registerActor(signalRock2);

        signalRock3 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(4,8), Logic.FALSE);
        this.registerActor(signalRock3);

        return super.begin(window, fileSystem);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (key.getSignal() == Logic.TRUE) {
            //update Door
        }

    }

    @Override
    public void setLevelBegan(boolean began) {
        levelBegan = true;
    }

    @Override
    public boolean isLevelBegan() {
        return levelBegan;
    }


}
