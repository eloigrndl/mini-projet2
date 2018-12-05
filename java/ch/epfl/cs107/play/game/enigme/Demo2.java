package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame implements Game {

    private Area room0, room1;
    private Demo2Player character;

    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public String getTitle() {
        return "Demo2";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        super.begin(window, fileSystem);
        this.room0 = new Room0();
        this.room1 = new Room1();
        addArea(room0);
        addArea(room1);
        setCurrentArea("LevelSelector", true);
        this.character = new Demo2Player(getCurrentArea(), Orientation.UP, (new DiscreteCoordinates(5,5)));
        return super.begin(window, fileSystem);
    }

    @Override
    public void update(float deltaTime) {
        super.update(1);
        character.draw(getWindow());
        character.update(1);
        System.out.println("isPassingDoor = " + character.isPassingDoor());
        if(character.isPassingDoor()){
            Area newArea;
            if(getCurrentArea().getTitle().equals("LevelSelector")){
                character.leaveArea(getCurrentArea(), new DiscreteCoordinates((int) character.getPosition().x, (int) character.getPosition().y));
                newArea = setCurrentArea("Level1", true);
                setBehavior(new Demo2Behavior(getWindow(),"Level1"));
                character.enterArea(getCurrentArea(),new DiscreteCoordinates(5, 2) );
            }else if(getCurrentArea().getTitle().equals("Level1")){
                character.leaveArea(getCurrentArea(), new DiscreteCoordinates((int) character.getPosition().x, (int) character.getPosition().y));
                setCurrentArea("LevelSelector", true);
                character.enterArea(getCurrentArea(),new DiscreteCoordinates(5,5 ));
            }
        }
    }
}


