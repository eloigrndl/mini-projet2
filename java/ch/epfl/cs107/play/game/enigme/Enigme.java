package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;

import java.util.ArrayList;

/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame implements Game, Playable{

    private Area LevelSelector, Level1, Level2, Level3;
    private EnigmePlayer character;

    //private LevelSelector levelSelector;

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
            this.LevelSelector = new LevelSelector();
            this.Level1 = new Level1();
            this.Level2 = new Level2();
            this.Level3 = new Level3();

            addArea(LevelSelector);
            addArea(Level1);
            addArea(Level2);
            addArea(Level3);
            setCurrentArea(LevelSelector.getTitle(), true);

            this.character = new EnigmePlayer(getCurrentArea(), Orientation.UP, (new DiscreteCoordinates(5, 5)));
            LevelSelector.registerActor(character);
            LevelSelector.setViewCandidate(character);
            return true;
        }

        return false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        character.draw(getWindow());

        if(character.isPassingDoor()){
            changeLevel(characterDestination(), character, characterArrival()) ;
            character.resetIsPassingDoor();
        }
    }

    /**
     * Get the location of the character's arrival
     * @return l'aire de destination de la denière porte franchi par le personnage
     */
    private Area characterDestination(){
        return getAreas().get(character.getLastDoor().getDestination());
    }

    /**
     * Get the location of the character's arrival
     * @return les coordonnées d'arrivée dans la nouvelle aire du personnage (une fois une porte passée)
     */
    private DiscreteCoordinates characterArrival(){
        return character.getLastDoor().getCoordinatesArrival();
    }

    /**
     * Method permitting the level change
     * @param areaToEnter
     * @param character
     * @param coordinates
     */
    private void changeLevel(Area areaToEnter, EnigmePlayer character, DiscreteCoordinates coordinates) {
        character.leaveArea();
        setCurrentArea(areaToEnter.getTitle(), true);
        character.enterArea(getCurrentArea(), coordinates);
    }

}
