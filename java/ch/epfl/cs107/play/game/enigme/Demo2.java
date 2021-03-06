package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.Room0;
import ch.epfl.cs107.play.game.enigme.area.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
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
            this.room1 = new Room1();
            addArea(room0);
            addArea(room1);
            setCurrentArea(room0.getTitle(), true);
            this.character = new Demo2Player(getCurrentArea(), Orientation.UP, (new DiscreteCoordinates(5,5)));
            room0.registerActor(character);
            room0.setViewCandidate(character);
            return true;
        }
        return false;
    }

    @Override

    public void update(float deltaTime) {

        super.update(deltaTime);
        if(character.isPassingDoor()){
            if(getCurrentArea().getTitle().equals(room0.getTitle())){
                changeLevel(room1, character, new DiscreteCoordinates(5,2));
            }else if(getCurrentArea().getTitle().equals(room1.getTitle())){
                changeLevel(room0, character, new DiscreteCoordinates(5,5));
            }
        }

    }

    /**
     * Method handling the calls to change level
     * @param areaToEnter the area entered
     * @param character the current character
     * @param coordinates the coordinates where the character will be in the new area
     */
    private void changeLevel(Area areaToEnter, Demo2Player character, DiscreteCoordinates coordinates) {
        character.leaveArea();
        setCurrentArea(areaToEnter.getTitle(), true);
        character.enterArea(getCurrentArea(), coordinates);
        character.setPassingDoor(false);

    }
}
