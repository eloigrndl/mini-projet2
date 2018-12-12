package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;

import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Enigme1 extends EnigmeArea{
    private Door door1;
    private Door door2;
    private Door door3;
    private Door door4;

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
