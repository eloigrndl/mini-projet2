package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.Area;

import java.util.HashMap;
import java.util.Map;


/**
 * AreaGame is a type of Game displayed in a (MxN) Grid called Area
 * An AreaGame has multiple Areas
 */
abstract public class AreaGame implements Game {

    // Context objects
    private Window window;
    private FileSystem fileSystem;
    /// A map containing all the Area of the Game
    private Map<String, Area> areas;
    /// The current area the game is in
    private Area currentArea;

    /**
     * Add an Area to the AreaGame list
     * @param a (Area): The area to add, not null
     */
    protected final void addArea(Area a){
        areas.put(a.getTitle(),a);
    }

    /**
     * Setter for the current area: Select an Area in the list from its key
     * - the area is then begin or resume depending if the area is already started or not and if it is forced
     * @param key (String): Key of the Area to select, not null
     * @param forceBegin (boolean): force the key area to call begin even if it was already started
     * @return (Area): after setting it, return the new current area
     */
    protected final Area setCurrentArea(String key, boolean forceBegin){

        if(currentArea != null){
            currentArea.suspend();
        }

        if(areas.get(key) != null){
            currentArea = areas.get(key);
            if(forceBegin){
                currentArea.begin(window, fileSystem);
            }else{
                currentArea.resume(window, fileSystem);
            }
        }else {
            if (currentArea == null) {
                throw new NullPointerException("currentArea is null");
            }
        }
        return null;
    }

    protected Area getCurrentArea(){
        return this.currentArea;
    }
    /**@return (Window) : the Graphic and Audio context*/
    protected final Window getWindow(){
        // TODO implements me #PROJECT #TUTO
        return window;
    }

    /**@return (FIleSystem): the linked file system*/
    protected final FileSystem getFileSystem(){
        // TODO implements me #PROJECT #TUTO
        return fileSystem;
    }


    /// AreaGame implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        //définit les variables réutilisées dans les autre méthodes
        this.window = window;
        this.fileSystem = fileSystem;
        areas = new HashMap<>();

        return true;
    }


    @Override
    public void update(float deltaTime) {
        currentArea.update(deltaTime);
    }

    @Override
    public void end() {
        // TODO save the game states somewhere
    }

}
