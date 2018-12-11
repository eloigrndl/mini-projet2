package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea {

    boolean levelBegan = false;

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window,fileSystem);

        Door door10 = new Door(this, "LevelSelector", new DiscreteCoordinates(2,6), Orientation.UP,
                new DiscreteCoordinates(5,0),new Circle(0.25f, new Vector(5,0)), true);
        this.registerActor(door10);

        Apple apple1 = new Apple(this, Orientation.UP, new DiscreteCoordinates(5,6));
        this.registerActor(apple1);

        return super.begin(window, fileSystem);
    }

    @Override
    public String getTitle() {
        return "Level2";
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

