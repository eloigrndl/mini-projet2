package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;
import java.util.ArrayList;

public class LevelSelector extends EnigmeArea {

    private ArrayList<Door> allDoors = new ArrayList<>();

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);

        Door door1 = new Door(this, "Level1", new DiscreteCoordinates(5,1), Orientation.UP,
                new DiscreteCoordinates(1,7), new Circle(0.25f, new Vector(1,7)), true);
        this.registerActor(door1);

        Door door2 = new Door(this, "Level2", new DiscreteCoordinates(5,1), Orientation.UP,
                new DiscreteCoordinates(2,7), new Circle(0.25f, new Vector(2,7)),true);
        this.registerActor(door2);

        Door door3 = new Door(this, "LevelSelector",new DiscreteCoordinates(3,6), Orientation.UP,
                new DiscreteCoordinates(3,7), new Circle(0.25f, new Vector(3,7)), false);
        this.registerActor(door3);

        Door door4 = new Door(this,"LevelSelector",new DiscreteCoordinates(4,6), Orientation.UP,
                new DiscreteCoordinates(4,7), new Circle(0.25f, new Vector(4,7)), false);
        this.registerActor(door4);

        Door door5 = new Door(this, "LevelSelector", new DiscreteCoordinates(5,6), Orientation.UP,
                new DiscreteCoordinates(5,7), new Circle(0.25f, new Vector(5,7)), false);
        this.registerActor(door5);

        Door door6 = new Door(this, "LevelSelector", new DiscreteCoordinates(6,6), Orientation.UP,
                new DiscreteCoordinates(6,7), new Circle(0.25f, new Vector(6,7)), false);
        this.registerActor(door6);

        Door door7 = new Door(this, "LevelSelector", new DiscreteCoordinates(7,6), Orientation.UP,
                new DiscreteCoordinates(7,7), new Circle(0.25f, new Vector(7,7)), false);
        this.registerActor(door7);

        Door door8 = new Door(this, "LevelSelector", new DiscreteCoordinates(8,6), Orientation.UP,
                new DiscreteCoordinates(8,7), new Circle(0.25f, new Vector(8,7)), false);
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

}
