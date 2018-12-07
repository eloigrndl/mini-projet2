package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;


/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame implements Game {

    private Area levelSelector, level1, level2, level3;

    /// Enigme implements Playable

    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        if (super.begin(window, fileSystem)) {
            //Init levelSelector, level1-2-3
            //addAreas
            //setCurrentArea(levelSelector)
            //setup/register/setViewCandidate character

            return true;
        }

        return false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        //character draw
        //character passingDoor
        //switch level
    }

    private void changeLevel(Area areaToLeave, Area areaToEnter, Demo2Player character, DiscreteCoordinates coordinates) {
        areaToLeave.unregisterActor(character);
        character.leaveArea();
        addArea(areaToEnter);
        setCurrentArea(areaToEnter.getTitle(), true);
        areaToEnter.registerActor(character);
        character.setOwnerArea(areaToEnter);
        areaToEnter.setViewCandidate(character);
        character.enterArea(getCurrentArea(), coordinates);
    }
}
