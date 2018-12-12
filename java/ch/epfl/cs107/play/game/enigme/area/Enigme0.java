package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Enigme0 extends EnigmeArea{

    private boolean levelBegan = false;
    private Door door1;
    private Door door2;
    private Door door3;
    private Door door4;
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
