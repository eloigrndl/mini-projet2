package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Enigme2 extends EnigmeArea {

    private boolean levelBegan = false;

    private Torch torch1;
    private Torch torch2;
    private SignalDoor door1;
    private SignalRing signalRing;
    private InvisibleSignalDoor invisibleSignalDoor;
    private HelpingPerson person1;

    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);

        //Torch
        torch1 = new Torch(this, new DiscreteCoordinates(6,5), Logic.FALSE);
        this.registerActor(torch1);

        torch2 = new Torch(this, new DiscreteCoordinates(8,5), Logic.FALSE);
        this.registerActor(torch2);

        signalRing = new SignalRing(this, Orientation.UP, new DiscreteCoordinates(7, 10), torch2);
        this.registerActor(signalRing);

        invisibleSignalDoor = new InvisibleSignalDoor(this, "LevelSelector", new DiscreteCoordinates(5,5), Orientation.DOWN,
                new DiscreteCoordinates(6,4), new Circle(1f, new Vector(6,4)), torch1);
        this.registerActor(invisibleSignalDoor);

        person1 = new HelpingPerson(this,Orientation.LEFT, new DiscreteCoordinates(8,3),"old.man.1");
        this.registerActor(person1);

        door1 = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(5,5), Orientation.UP,
                new DiscreteCoordinates(7,0), new Circle(1f, new Vector(7,0)), torch2);
        this.registerActor(door1);

        return super.begin(window, fileSystem);
    }

    @Override
    public String getTitle() {
        return "Enigme2-AB";
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
