package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;
public class Level1 extends EnigmeArea {

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);

        Door door9 = new Door(this, "LevelSelector", new DiscreteCoordinates(1,6), Orientation.UP,
                new DiscreteCoordinates(5,0), new Circle(0.25f, new Vector(5,0)), true);
        this.registerActor(door9);

        return super.begin(window, fileSystem);
    }

    @Override
    public String getTitle() {
        return "Level1";
    }


}

