package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;

import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.*;
import ch.epfl.cs107.play.window.Window;

import java.util.Arrays;
import java.util.List;

public class Enigme1 extends EnigmeArea {

    private Door door1;
    private Door door2;
    private Door door3;

    //(15,3)
    private Torch torch1;
    //(17,3)
    private Torch torch2;
    //(20,37)
    private Torch torch3;
    //(16,34)
    private Torch torch4;
    //(10,24)
    private Torch torch5;
    //(10,28)
    private Torch torch6;

    //(22,2)
    private PressureSwitch pressureSwitch1;
    //(23,34)
    private PressureSwitch pressureSwitch2;

    //(18,18)
    private PressurePlate pressurePlate1;

    //(21,6)
    private SignalRock signalRock1;
    //(23, 14)
    private SignalRock signalRock2;
    //(15, 14)
    private SignalRock signalRock3;
    //(14, 5)
    private SignalRock signalRock4;
    //(15, 32)
    private SignalRock signalRock5;
    //(15, 35)
    private SignalRock signalRock6;
    //(20, 35)
    private SignalRock signalRock7;
    //(10, 26)
    private SignalRock signalRock8;
    //(12, 33)
    private SignalRock signalRock9;

    //(20,11)
    private Lever lever1;
    //(19,11)
    private Lever lever2;
    //(18,11)
    private Lever lever3;

    //(22,35)
    private Key key;

    //(17,25)
    private Apple apple;

    private boolean levelBegan = false;

    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);

        door1 = new Door(this, "Enigme0", new DiscreteCoordinates(9,28), Orientation.UP,
                new DiscreteCoordinates(15,0), new Circle(1f, new Vector(15,0)));
        this.registerActor(door1);

        door2 = new Door(this, "Enigme0", new DiscreteCoordinates(10,28), Orientation.UP,
                new DiscreteCoordinates(16,0), new Circle(1f, new Vector(17,0)));
        this.registerActor(door2);

        door3 = new Door(this, "Enigme0", new DiscreteCoordinates(11,28), Orientation.UP,
                new DiscreteCoordinates(17,0), new Circle(1f, new Vector(16,0)));
        this.registerActor(door3);

        //Torch
        torch1 = new Torch(this, new DiscreteCoordinates(15,3), Logic.TRUE);
        this.registerActor(torch1);

        torch2 = new Torch(this, new DiscreteCoordinates(17,3), Logic.FALSE);
        this.registerActor(torch2);

        torch3 = new Torch(this, new DiscreteCoordinates(19,37), Logic.FALSE);
        this.registerActor(torch3);

        torch4 = new Torch(this, new DiscreteCoordinates(16,35), Logic.FALSE);
        this.registerActor(torch4);

        torch5 = new Torch(this, new DiscreteCoordinates(10,25), Logic.FALSE);
        this.registerActor(torch5);

        torch6 = new Torch(this, new DiscreteCoordinates(10,30), Logic.FALSE);
        this.registerActor(torch6);

        //PressureSwitch
        pressureSwitch1 = new PressureSwitch(this, new DiscreteCoordinates(22,2));
        this.registerActor(pressureSwitch1);

        pressureSwitch2 = new PressureSwitch(this, new DiscreteCoordinates(23,35));
        this.registerActor(pressureSwitch2);

        //PressurePlate
        pressurePlate1 = new PressurePlate(this, new DiscreteCoordinates(17,18));
        this.registerActor(pressurePlate1);

        //Lever
        lever1 = new Lever(this, new DiscreteCoordinates(20,11));
        this.registerActor(lever1);

        lever2 = new Lever(this, new DiscreteCoordinates(19,11));
        this.registerActor(lever2);

        lever3 = new Lever(this, new DiscreteCoordinates(18,11));
        this.registerActor(lever3);

        //Key
        key = new Key(this, new DiscreteCoordinates(22,36));
        this.registerActor(key);

        //SignalRock
        List<Logic> signalRock1List = Arrays.asList(torch1, torch2, pressureSwitch1);
        signalRock1 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(21,6), new MultipleAnd(signalRock1List));
        this.registerActor(signalRock1);

        List<Logic> signalRock2List = Arrays.asList(lever3, lever2, lever1);
        signalRock2 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(23,14), new LogicNumber(6, signalRock2List));
        this.registerActor(signalRock2);

        signalRock3 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(15,14), new Or(pressurePlate1, pressureSwitch2));
        this.registerActor(signalRock3);

        signalRock4 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(14,5), key);
        this.registerActor(signalRock4);

        signalRock5 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(16,32), torch3);
        this.registerActor(signalRock5);

        signalRock6 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(15,35), torch3);
        this.registerActor(signalRock6);

        signalRock7 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(20,35), torch4);
        this.registerActor(signalRock7);

        signalRock8 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(12,26), torch5);
        //this.registerActor(signalRock8);

        signalRock9 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(12,33), torch6);
        this.registerActor(signalRock9);

        apple = new Apple(this, Orientation.UP, new DiscreteCoordinates(17,25));
        this.registerActor(apple);

        return super.begin(window, fileSystem);

    }


    @Override
    public String getTitle() {
        return "Enigme1";
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void setLevelBegan(boolean began) {
        levelBegan = true;
    }

    @Override
    public boolean isLevelBegan() {
        return levelBegan;
    }

    @Override
    public float getCameraScaleFactor() {
        return 30;
    }
}
