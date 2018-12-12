package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.SoundAcoustics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame implements Game, Playable{

    private Area LevelSelector, Level1, Level2, Level3;
    private EnigmePlayer character;
    //private GraphicsEntity gamePaused = new GraphicsEntity(new Vector(0.0f,0.0f),new TextGraphics("Game Paused : "+ "\n" + "press Enter again to resume game", 1f, Color.BLACK, Color.BLACK, 0.005f, true, true, new Vector(0.0f,0.0f)));
    private ImageGraphics gamePaused;

    private boolean isPaused;

    private SoundAcoustics soundEffect;

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
            LevelSelector.setLevelBegan(true);

            isPaused = false;
            gamePaused = new ImageGraphics(ResourcePath.getBackgrounds("Pause"), getCurrentArea().getWidth(), getCurrentArea().getHeight(), null, Vector.ZERO, 1.0f, 0.0f);

            soundEffect = new SoundAcoustics(ResourcePath.getSounds("Background"), 1.0f, false, false, true, false);
            soundEffect.shouldBeStarted();
            soundEffect.bip(getWindow());

            return true;
        }

        return false;
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getCurrentArea().getKeyboard();
        Button enter = keyboard.get(Keyboard.ENTER);

        if (enter.isPressed()) {
            isPaused = !isPaused;
            gamePaused.draw(getWindow());
        }

        if(!isPaused) {
            super.update(deltaTime);
            character.draw(getWindow());

            if (character.isPassingDoor()) {
                changeLevel(characterDestination(), character, characterArrival());
                character.resetIsPassingDoor();
            }
        } else {
            gamePaused.draw(getWindow());
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
        if (areaToEnter.isLevelBegan()) {
            setCurrentArea(areaToEnter.getTitle(), false);
        } else {
            setCurrentArea(areaToEnter.getTitle(), true);
            areaToEnter.setLevelBegan(true);
        }
        character.enterArea(getCurrentArea(), coordinates);
        gamePaused = new ImageGraphics(ResourcePath.getBackgrounds("Pause"), getCurrentArea().getWidth(), getCurrentArea().getHeight(), null, Vector.ZERO, 1.0f, 0.0f);

    }



}
