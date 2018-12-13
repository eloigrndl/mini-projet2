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
    private SignalDoor finalDoor;

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
    //(7,4)
    private Torch torch7;
    //(8,4)
    private Torch torch8;
    //(9,4)
    private Torch torch9;
    //(7,5)
    private Torch torch10;
    //(8,5)
    private Torch torch11;
    //(9,5)
    private Torch torch12;
    //(7, 6)
    private Torch torch13;
    //(8,6,)
    private Torch torch14;
    //(9,6)
    private Torch torch15;
    //(4,14)
    private Torch torch16;

    //(22,2)
    private PressureSwitch pressureSwitch1;
    //(23,34)
    private PressureSwitch pressureSwitch2;
    //(2,2)
    private PressureSwitch pressureSwitch3;
    //(3,2)
    private PressureSwitch pressureSwitch4;
    //(4,2)
    private PressureSwitch pressureSwitch5;
    //(2,3)
    private PressureSwitch pressureSwitch6;
    //(3,3)
    private PressureSwitch pressureSwitch7;
    //(3,4)
    private PressureSwitch pressureSwitch8;
    //(4,2)
    private PressureSwitch pressureSwitch9;
    //(4,3)
    private PressureSwitch pressureSwitch10;
    //(4,4)
    private PressureSwitch pressureSwitch11;
    //(2,12)
    private PressureSwitch pressureSwitch12;
    //(12,11)
    private PressureSwitch pressureSwitch13;
    //(10,16)
    private PressureSwitch pressureSwitch14;
    //(2,15)
    private PressureSwitch pressureSwitch15;
    //(10,14)
    private PressureSwitch pressureSwitch16;









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
    //(6,9)
    private SignalRock signalRock10;
    //(7,9)
    private SignalRock signalRock11;
    //(7,10)
    private SignalRock signalRock12;
    //(7,11)
    private SignalRock signalRock13;
    //(11,12)
    private SignalRock signalRock14;
    //(12,14)
    private SignalRock signalRock15;
    //(2,16)
    private SignalRock signalRock16;
    //(8,15)
    private SignalRock signalRock17;
    //(6,17)
    private SignalRock signalRock18;
    //(7,17)
    private SignalRock signalRock19;

    // Rock du labyrinthe mis sous forme de tableau car ils sont immobiles et n'ont pas
    // de signaux particuliers
    SignalRock[] mazeRock = new SignalRock[36];

    //(20,11)
    private Lever lever1;
    //(19,11)
    private Lever lever2;
    //(18,11)
    private Lever lever3;
    //(11,7)
    private Lever lever4;

    //(22,35)
    private Key key;
    //(6,23)
    private Key finalKey;

    //(17,25)
    private Apple apple;

    //(11,8)
    private HelpingPerson person1;

    private boolean levelBegan = false;

    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);

        //Helping Person
        person1 = new HelpingPerson(this, Orientation.LEFT,new DiscreteCoordinates(11,2), "girl.3");
        this.registerActor(person1);
        //Key
        key = new Key(this, new DiscreteCoordinates(22,36));
        this.registerActor(key);

        finalKey = new Key(this, new DiscreteCoordinates(6,23));
        this.registerActor(finalKey);

        door1 = new Door(this, "Enigme0", new DiscreteCoordinates(9,28), Orientation.UP,
                new DiscreteCoordinates(15,0), new Circle(1f, new Vector(15,0)));
        this.registerActor(door1);

        door2 = new Door(this, "Enigme0", new DiscreteCoordinates(10,28), Orientation.UP,
                new DiscreteCoordinates(16,0), new Circle(1f, new Vector(17,0)));
        this.registerActor(door2);

        door3 = new Door(this, "Enigme0", new DiscreteCoordinates(11,28), Orientation.UP,
                new DiscreteCoordinates(17,0), new Circle(1f, new Vector(16,0)));
        this.registerActor(door3);

        finalDoor = new SignalDoor(this, "Enigme2-AB", new DiscreteCoordinates(7,3), Orientation.UP,
                new DiscreteCoordinates(6,32), new Circle(1f, new Vector(6,32)), finalKey);
        this.registerActor(finalDoor);

        //Torch
        torch1 = new Torch(this, new DiscreteCoordinates(15,3), Logic.TRUE);
        this.registerActor(torch1);

        torch2 = new Torch(this, new DiscreteCoordinates(17,3), Logic.FALSE);
        this.registerActor(torch2);

        torch3 = new Torch(this, new DiscreteCoordinates(19,37), Logic.FALSE);
        this.registerActor(torch3);

        torch4 = new Torch(this, new DiscreteCoordinates(16,35), Logic.FALSE);
        this.registerActor(torch4);

        torch5 = new Torch(this, new DiscreteCoordinates(10,25), Logic.TRUE);
        this.registerActor(torch5);

        torch6 = new Torch(this, new DiscreteCoordinates(10,30), Logic.FALSE);
        this.registerActor(torch6);

        torch7 = new Torch(this, new DiscreteCoordinates(7,4), Logic.FALSE);
        this.registerActor(torch7);
        torch8 = new Torch(this, new DiscreteCoordinates(8,4), Logic.TRUE);
       this.registerActor(torch8);
        torch9 = new Torch(this, new DiscreteCoordinates(9,4), Logic.TRUE);
        this.registerActor(torch9);
        torch10 = new Torch(this, new DiscreteCoordinates(7,5), Logic.TRUE);
        this.registerActor(torch10);
        torch11 = new Torch(this, new DiscreteCoordinates(8,5), Logic.TRUE);
        this.registerActor(torch11);
        torch12 = new Torch(this, new DiscreteCoordinates(9,5), Logic.FALSE);
        this.registerActor(torch12);
        torch13 = new Torch(this, new DiscreteCoordinates(7,6), Logic.TRUE);
        this.registerActor(torch13);
        torch14 = new Torch(this, new DiscreteCoordinates(8,6), Logic.FALSE);
        this.registerActor(torch14);
        torch15 = new Torch(this, new DiscreteCoordinates(9,6), Logic.FALSE);
        this.registerActor(torch15);
        torch16 = new Torch(this, new DiscreteCoordinates(4,14), Logic.TRUE);
        this.registerActor(torch16);

        //PressureSwitch
        pressureSwitch1 = new PressureSwitch(this, new DiscreteCoordinates(22,2));
        this.registerActor(pressureSwitch1);
        pressureSwitch2 = new PressureSwitch(this, new DiscreteCoordinates(23,35));
        this.registerActor(pressureSwitch2);
        pressureSwitch3 = new PressureSwitch(this, new DiscreteCoordinates(2,4));
        this.registerActor(pressureSwitch3);
        pressureSwitch4 = new PressureSwitch(this, new DiscreteCoordinates(3,4));
        this.registerActor(pressureSwitch4);
        pressureSwitch5 = new PressureSwitch(this, new DiscreteCoordinates(4,4));
        this.registerActor(pressureSwitch5);
        pressureSwitch6 = new PressureSwitch(this, new DiscreteCoordinates(2,5));
        this.registerActor(pressureSwitch6);
        pressureSwitch7 = new PressureSwitch(this, new DiscreteCoordinates(3,5));
        this.registerActor(pressureSwitch7);
        pressureSwitch8 = new PressureSwitch(this, new DiscreteCoordinates(4,5));
        this.registerActor(pressureSwitch8);
        pressureSwitch9 = new PressureSwitch(this, new DiscreteCoordinates(2,6));
        this.registerActor(pressureSwitch9);
        pressureSwitch10 = new PressureSwitch(this, new DiscreteCoordinates(3,6));
        this.registerActor(pressureSwitch10);
        pressureSwitch11 = new PressureSwitch(this, new DiscreteCoordinates(4,6));
        this.registerActor(pressureSwitch11);
        pressureSwitch12 = new PressureSwitch(this, new DiscreteCoordinates(2,12));
        this.registerActor(pressureSwitch12);
        pressureSwitch13 = new PressureSwitch(this, new DiscreteCoordinates(12,11));
        this.registerActor(pressureSwitch13);
        pressureSwitch14= new PressureSwitch(this, new DiscreteCoordinates(10,16));
        this.registerActor(pressureSwitch14);
        pressureSwitch15 = new PressureSwitch(this, new DiscreteCoordinates(2,15));
        this.registerActor(pressureSwitch15);
        pressureSwitch16 = new PressureSwitch(this, new DiscreteCoordinates(10,14));
        this.registerActor(pressureSwitch16);



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

        lever4 = new Lever(this, new DiscreteCoordinates(11,7));
        this.registerActor(lever4);

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
        
        signalRock5 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(16,34), torch3);
        this.registerActor(signalRock5);

        signalRock6 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(15,35), torch3);
        this.registerActor(signalRock6);

        signalRock7 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(20,35), torch4);
        this.registerActor(signalRock7);
      
        signalRock8 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(12,26), new Not(torch5));
        this.registerActor(signalRock8);

        signalRock9 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(12,33), torch6);
        this.registerActor(signalRock9);

        List<Logic> signalRock10_11List = Arrays.asList(pressureSwitch4, pressureSwitch5, pressureSwitch6, pressureSwitch7, pressureSwitch9,
                new Not(pressureSwitch3), new Not(pressureSwitch8), new Not(pressureSwitch10), new Not(pressureSwitch11));
        signalRock10 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(6,9), new MultipleAnd(signalRock10_11List));
        this.registerActor(signalRock10);

        signalRock11 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(7,9), new MultipleAnd(signalRock10_11List));
        this.registerActor(signalRock11);

        signalRock12 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(6,10), lever4);
        this.registerActor(signalRock12);

        signalRock13 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(7,10), lever4);
        this.registerActor(signalRock13);

        signalRock14 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(11,12), pressureSwitch12);
        this.registerActor(signalRock14);

        signalRock15 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(12,14), pressureSwitch13);
        this.registerActor(signalRock15);

        signalRock16 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(2,16), pressureSwitch14);
        this.registerActor(signalRock16);

        signalRock17 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(8,15), pressureSwitch15);
        this.registerActor(signalRock17);

        signalRock18 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(6,17), pressureSwitch16);
        this.registerActor(signalRock18);

        signalRock19 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(7,17), pressureSwitch16);
        this.registerActor(signalRock19);

        for(int i = 1; i<= 5; ++i){
            mazeRock[i-1] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(i,11), Logic.FALSE);
        }
        for(int i = 5; i <= 8; ++i){
         mazeRock[i] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(i+3 ,11), Logic.FALSE);
        }
        mazeRock[9] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(1,12), Logic.FALSE);
        for(int i = 10; i<=14; ++i){
            mazeRock[i] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(i-9,13), Logic.FALSE);
        }
        for (int i = 15; i<=19; ++i){
           mazeRock[i] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(i-8,13), Logic.FALSE);
        }
        for(int i = 20; i<=22; ++i){
          mazeRock[i] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(i-19,14), Logic.FALSE);
        }
        mazeRock[23] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(5,14), Logic.FALSE);
        mazeRock[24] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(7,14), Logic.FALSE);
        mazeRock[25] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(11,14), Logic.FALSE);
        mazeRock[26] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(1,15), Logic.FALSE);

        for(int i = 27; i<=29; ++i){
          mazeRock[i] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(i-24,15), Logic.FALSE);
        }
        mazeRock[30] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(7,15), Logic.FALSE);
        for(int i = 31; i<=33; ++i){
            mazeRock[i] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(i-22,15), Logic.FALSE);
        }
        mazeRock[34] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(1,16), Logic.FALSE);
        mazeRock[35] = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(9,16), Logic.FALSE);

        for(int i = 0; i < mazeRock.length; ++i){
          this.registerActor(mazeRock[i]);
        }

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
