package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Enigme0 extends EnigmeArea{

    private boolean levelBegan = false;
    private Door door1;
    private Door door2;
    private Door door3;
    private Door door4;
    private Door door5;

    private HelpingPerson person1;
    private HelpingPerson person2;
    private HelpingPerson person3;
    private HelpingPerson person4;
    private HelpingPerson person5;

    private PressureSwitch switch1;
    private SignalRock rock1;
    private Gelly gelly1;

    private Torch torch1;

    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);

        door1 = new Door(this, "Enigme1", new DiscreteCoordinates(15,1), Orientation.UP,
                new DiscreteCoordinates(9,29), new Circle(1f, new Vector(9,29)));
        this.registerActor(door1);

        door2 = new Door(this, "Enigme1", new DiscreteCoordinates(16,1), Orientation.UP,
                new DiscreteCoordinates(10,29), new Circle(1f, new Vector(10 ,29)));
        this.registerActor(door2);

        door3 = new Door(this, "Enigme1", new DiscreteCoordinates(17,1), Orientation.UP,
                new DiscreteCoordinates(11,29), new Circle(1f, new Vector(11,29)));
        this.registerActor(door3);

        door4 = new Door(this, "LevelSelector", new DiscreteCoordinates(5,5), Orientation.UP,
                new DiscreteCoordinates(0,12), new Circle(1f, new Vector(0,12)));
        this.registerActor(door4);

        door5 = new Door(this, "LevelSelector", new DiscreteCoordinates(5,5), Orientation.UP,
                new DiscreteCoordinates(0,11), new Circle(1f, new Vector(0,11)));
        this.registerActor(door5);

        person1 = new HelpingPerson(this, Orientation.RIGHT,new DiscreteCoordinates(14,11), "boy.2");
        this.registerActor(person1);

        person2 = new HelpingPerson(this, Orientation.DOWN,new DiscreteCoordinates(20,13), "old.man.2");
        this.registerActor(person2);

        person3 = new HelpingPerson(this, Orientation.LEFT,new DiscreteCoordinates(21,21), "girl.2");
        this.registerActor(person3);

        person4 = new HelpingPerson(this, Orientation.RIGHT,new DiscreteCoordinates(1,13), "boy.4");
        this.registerActor(person4);

        person5 = new HelpingPerson(this, Orientation.DOWN,new DiscreteCoordinates(12,28) , "old.man.3");
        this.registerActor(person5);

        torch1 = new Torch(this, new DiscreteCoordinates(8,28), Logic.TRUE);
        this.registerActor(torch1);

        switch1 = new PressureSwitch(this, new DiscreteCoordinates(10,2));
        this.registerActor(switch1);

        rock1 = new SignalRock(this, Orientation.UP, new DiscreteCoordinates(13,3), switch1);
        this.registerActor(rock1);

        gelly1 = new Gelly(this, Orientation.UP, new DiscreteCoordinates(14,2));
        this.registerActor(gelly1);


        return super.begin(window, fileSystem);

    }


    @Override
    public String getTitle() {
        return "Enigme0";
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
