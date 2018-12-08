package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.game.actor.Actor;

import javax.sound.midi.SysexMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{

    /// The behavior is an Image of size height x width
    private final Image behaviorMap;
    private final int width, height;
    /// We will convert the image into an array of cells private final Cell[][] cells;
    private final Cell[][] cells;

    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */

    public AreaBehavior(Window window, String fileName){
        this.behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
        this.width = behaviorMap.getWidth();
        this.height = behaviorMap.getHeight();
        this.cells = new Cell[width][height];
    }

    protected int[] getBehaviorMapSize() {

        int[] size = {cells.length, cells[0].length};

        return size;
    }

    public Image getBehaviorMap(){
        return behaviorMap;
    }

    public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {

        boolean canLeave = true;
        for (int i=0; i<coordinates.size(); ++i) {
            DiscreteCoordinates coordinate = coordinates.get(i);
            if (!cells[coordinate.x][coordinate.y].canLeave(entity)) {
                return false;
            }
        }
        return true;
    }

    public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (int i=0; i<coordinates.size(); ++i) {
            DiscreteCoordinates coordinate = coordinates.get(i);
            if (!cells[coordinate.x][coordinate.y].canEnter(entity)) {
                return false;
            }
        }
        return true;
    }

    public boolean canPassDoor(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (int i=0; i<coordinates.size(); ++i) {
            DiscreteCoordinates coordinate = coordinates.get(i);
            if (!cells[coordinate.x][coordinate.y].canPassDoor(entity)) {
                return false;
            }
        }
        return true;
    }

    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (int i=0; i<coordinates.size(); ++i) {
            DiscreteCoordinates coordinate = coordinates.get(i);
            cells[coordinate.x][coordinate.y].leave(entity);
        }
    }

    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (int i=0; i<coordinates.size(); ++i) {
            DiscreteCoordinates coordinate = coordinates.get(i);
            cells[coordinate.x][coordinate.y].enter(entity);
        }
    }

    protected void setCell(int x, int y, Demo2Behavior.Demo2Cell cell) {
        cells[x][y] = cell;
    }
    protected void setCell(int x, int y, EnigmeBehavior.EnigmeCell cell) {
        cells[x][y] = cell;
    }
    /**
     * Each game will have its own Cell extension. */
    public abstract class Cell implements Interactable {

        DiscreteCoordinates coordinates;

        Set<Interactable> interactableSet = new HashSet<>();

        public Cell(int x, int y) {

            this.coordinates = new DiscreteCoordinates(x,y);
        }

        @Override
        public List<DiscreteCoordinates> getCurrentCells() {
            return null;
        }

        private void enter(Interactable interactable) {
            interactableSet.add(interactable);
        }

        private void leave(Interactable interactable) {
            interactableSet.remove(interactable);
        }

        protected boolean canEnter(Interactable entity) {
            //return true if entity a le droit de s'ajouter au contenu
            return false;
        }

        protected boolean canLeave(Interactable entity) {
            //return true if entity a le droit de se barrer du contenu
            return false;
        }

        protected boolean canPassDoor(Interactable entity) {
            return false;
        }
    }
}
