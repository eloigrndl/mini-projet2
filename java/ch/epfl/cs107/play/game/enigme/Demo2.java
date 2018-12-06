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

        if (super.begin(window, fileSystem)) {
            this.room0 = new Room0();
            room0.setBehavior(room0.getAreaBehavior());
            this.room1 = new Room1();
            room1.setBehavior(room1.getAreaBehavior());
            addArea(room0);
            addArea(room1);
            setCurrentArea(room0.getTitle(), true);
            this.character = new Demo2Player(getCurrentArea(), Orientation.UP, (new DiscreteCoordinates(5,5)));
            room0.setViewCandidate(character);
            return true;
        }

        return false;

    }

    @Override
    public void update(float deltaTime) {

        super.update(1);
        character.update(1);
        character.draw(getWindow());
        if(character.isPassingDoor()){
            if(getCurrentArea().getTitle().equals(room0.getTitle())){
                character.leaveArea(getCurrentArea(), new DiscreteCoordinates((int) character.getPosition().x, (int) character.getPosition().y));
                addArea(room1);
                setCurrentArea(room1.getTitle(), true);
                character.setOwnerArea(room1);
                room1.setViewCandidate(character);
                character.enterArea(getCurrentArea(),new DiscreteCoordinates(5, 2) );
            }else if(getCurrentArea().getTitle().equals(room1.getTitle())){
                character.leaveArea(getCurrentArea(), new DiscreteCoordinates((int) character.getPosition().x, (int) character.getPosition().y));
                room0.setBehavior(room0.getAreaBehavior());
                room0.setViewCandidate(character);
                addArea(room0);
                setCurrentArea(room0.getTitle(), true);
                character.setOwnerArea(room0);
                room0.setViewCandidate(character);
                character.enterArea(getCurrentArea(),new DiscreteCoordinates(5,5 ));
            }
        }
    }
}


