package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {

    boolean levelBegan = false;

    @Override
    public String getTitle() {
        return "Level3";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        Door door10 = new Door(this, "LevelSelector", new DiscreteCoordinates(2,6), Orientation.UP,
                new DiscreteCoordinates(5,0),new Circle(0.25f, new Vector(5,0)), true);
        this.registerActor(door10);

        Apple apple1 = new Apple(this, Orientation.UP, new DiscreteCoordinates(5,6));
        this.registerActor(apple1);

        Key key = new Key(this, new DiscreteCoordinates(1,3));
      this.registerActor(key);
//
//        Torch torch = new Torch(this, new DiscreteCoordinates(7,5), Logic.TRUE);
//        this.registerActor(torch);
//
//        PressurePlate pressurePlate = new PressurePlate(this, new DiscreteCoordinates(9,8));
//        this.registerActor(pressurePlate);
//
//        PressureSwitch pressureSwitch1 = new PressureSwitch(this, new DiscreteCoordinates(4,4));
//        this.registerActor(pressureSwitch1);
//
//        PressureSwitch pressureSwitch2 = new PressureSwitch(this, new DiscreteCoordinates(5,4));
//        this.registerActor(pressureSwitch2);
//
//        PressureSwitch pressureSwitch3 = new PressureSwitch(this, new DiscreteCoordinates(6,4));
//        this.registerActor(pressureSwitch3);
//
//        PressureSwitch pressureSwitch4 = new PressureSwitch(this, new DiscreteCoordinates(5,5));
//        this.registerActor(pressureSwitch4);
//
//        PressureSwitch pressureSwitch5 = new PressureSwitch(this, new DiscreteCoordinates(4,6));
//        this.registerActor(pressureSwitch5);
//
//        PressureSwitch pressureSwitch6 = new PressureSwitch(this, new DiscreteCoordinates(5,6));
//        this.registerActor(pressureSwitch6);
//
//        PressureSwitch pressureSwitch7 = new PressureSwitch(this, new DiscreteCoordinates(6,6));
//        this.registerActor(pressureSwitch7);
//
//        Lever lever1 = new Lever(this, new DiscreteCoordinates(10,5));
//        this.registerActor(lever1);
//
//        Lever lever2 = new Lever(this, new DiscreteCoordinates(9,5));
//        this.registerActor(lever2);
//
//        Lever lever3 = new Lever(this, new DiscreteCoordinates(8,5));
//        this.registerActor(lever3);
//
//        SignalDoor signalDoor1 = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(3,6),
//                Orientation.UP, new DiscreteCoordinates(5,9), new Circle(0.25f, new Vector(5,9)), Logic.FALSE);
//        this.registerActor(signalDoor1);
//
//        SignalRock signalRock1 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(6,8), Logic.FALSE);
//        this.registerActor(signalRock1);
//
//        SignalRock signalRock2 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(5,8), Logic.FALSE);
//        this.registerActor(signalRock2);
//
//        SignalRock signalRock3 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(4,8), Logic.FALSE);
//        this.registerActor(signalRock3);

        return super.begin(window, fileSystem);
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
