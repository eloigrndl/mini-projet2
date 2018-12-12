package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;
import java.util.ArrayList;

public class LevelSelector extends EnigmeArea {

    private ArrayList<Door> allDoors = new ArrayList<>();

    boolean levelBegan = false;

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);

        SignalDoor door1 = new SignalDoor(this, "Level1", new DiscreteCoordinates(5,1), Orientation.UP,
                new DiscreteCoordinates(1,7), new Circle(0.25f, new Vector(1,7)), Logic.TRUE);
        this.registerActor(door1);

        SignalDoor door2 = new SignalDoor(this, "Level2", new DiscreteCoordinates(5,1), Orientation.UP,
                new DiscreteCoordinates(2,7), new Circle(0.25f, new Vector(2,7)),Logic.TRUE);
        this.registerActor(door2);

        SignalDoor door3 = new SignalDoor(this, "Level3",new DiscreteCoordinates(5,1), Orientation.UP,
                new DiscreteCoordinates(3,7), new Circle(0.25f, new Vector(3,7)), Logic.TRUE);
        this.registerActor(door3);

        SignalDoor door4 = new SignalDoor(this,"LevelSelector",new DiscreteCoordinates(4,6), Orientation.UP,
                new DiscreteCoordinates(4,7), new Circle(0.25f, new Vector(4,7)), Logic.FALSE);
        this.registerActor(door4);

        SignalDoor door5 = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(5,6), Orientation.UP,
                new DiscreteCoordinates(5,7), new Circle(0.25f, new Vector(5,7)), Logic.FALSE);
        this.registerActor(door5);

        SignalDoor door6 = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(6,6), Orientation.UP,
                new DiscreteCoordinates(6,7), new Circle(0.25f, new Vector(6,7)), Logic.FALSE);
        this.registerActor(door6);

        SignalDoor door7 = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(7,6), Orientation.UP,
                new DiscreteCoordinates(7,7), new Circle(0.25f, new Vector(7,7)), Logic.FALSE);
        this.registerActor(door7);

        SignalDoor door8 = new SignalDoor(this, "Enigme0", new DiscreteCoordinates(23,18), Orientation.DOWN,
                new DiscreteCoordinates(8,7), new Circle(0.25f, new Vector(8,7)), Logic.TRUE);
        this.registerActor(door8);

        allDoors.add(door1);
        allDoors.add(door2);
        allDoors.add(door3);
        allDoors.add(door4);
        allDoors.add(door5);
        allDoors.add(door6);
        allDoors.add(door7);
        allDoors.add(door8);

        return super.begin(window,fileSystem);
    }

    public ArrayList<Door> getAllDoors() {
        return allDoors;
    }

    @Override
    public String getTitle()
    {
        return "LevelSelector";
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
