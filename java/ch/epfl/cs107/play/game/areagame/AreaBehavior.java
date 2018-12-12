package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.window.Image;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
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

    /**
     * Getter of the behaviorMap size
     * @return (int[]) width, height
     */
    protected int[] getBehaviorMapSize() {

        int[] size = {cells.length, cells[0].length};
        return size;
    }

    /**
     * Getter for the (Image) of the BehaviorMap
     * @return (Image)
     */
    public Image getBehaviorMap(){
        return behaviorMap;
    }

    /**
     * Définit si entity peut quitter la cellule
     * @param entity (Interactable)
     * @param coordinates (List<DiscreteCoordinates>) current coordinates of entity
     * @return (boolean) entity can leave
     */
    public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {

        for (int i=0; i<coordinates.size(); ++i) {
            DiscreteCoordinates coordinate = coordinates.get(i);
            if (!cells[coordinate.x][coordinate.y].canLeave(entity)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Définit si entity peut entrer dans la cellule
     * @param entity (Interactable)
     * @param coordinates (List<DiscreteCoordinates>) current coordinates of entity
     * @return (boolean) entity can enter
     */
    public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {

        for (int i=0; i<coordinates.size(); ++i) {
            DiscreteCoordinates coordinate = coordinates.get(i);
            if (!cells[coordinate.x][coordinate.y].canEnter(entity)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Définit si entity peut passer la porte
     * @param entity (Interactable)
     * @param coordinates (List<DiscreteCoordinates>) current coordinates of entity
     * @return (boolean) entity can enter
     */
    public boolean canPassDoor(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (int i=0; i<coordinates.size(); ++i) {
            DiscreteCoordinates coordinate = coordinates.get(i);
            if (!cells[coordinate.x][coordinate.y].canPassDoor(entity)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Définit si entity peut sortir
     * @param entity (Interactable)
     * @param coordinates (List<DiscreteCoordinates>) current coordinates of entity
     * @return (boolean) entity can leave
     */
    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (int i=0; i<coordinates.size(); ++i) {
            DiscreteCoordinates coordinate = coordinates.get(i);
            cells[coordinate.x][coordinate.y].leave(entity);
        }
    }

    /**
     * Définit si entity peut entrer
     * @param entity (Interactable)
     * @param coordinates (List<DiscreteCoordinates>) current coordinates of entity
     * @return (boolean) entity can enter
     */
    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (int i=0; i<coordinates.size(); ++i) {
            DiscreteCoordinates coordinate = coordinates.get(i);
            cells[coordinate.x][coordinate.y].enter(entity);
        }
    }

    /**
     * Gere les interactions de contact entre interactor et les Interactables aux mêmes positions.
     * @param interactor
     */
    public void cellInteractionOf(Interactor interactor) {
        for (DiscreteCoordinates coordinates : interactor.getCurrentCells()) {
            cells[coordinates.x][coordinates.y].cellInteractionOf(interactor);
        }
    }

    /**
     * Gere les interactions à distance entre interactor et Interactable aux mêmes positions.
     * @param interactor
     */
    public void viewInteractionOf(Interactor interactor) {
        for (DiscreteCoordinates coordinates : interactor.getFieldOfViewCells()) {
            cells[coordinates.x][coordinates.y].viewInteractionOf(interactor);
        }
    }

    /**
     * Setter for the cells
     * @param x coordinate
     * @param y coordinate
     * @param cell (Cell) containing its type
     */
    protected void setCell(int x, int y, Cell cell) {
        cells[x][y] = cell;
    }

    /**
     * Each game will have its own Cell extension. */
    public abstract class Cell implements Interactable {

        //Cell coordinates
        DiscreteCoordinates coordinates;

        //The set of Interactables
        Set<Interactable> interactableSet = new HashSet<>();

        /**
         * Cell Constructor
         * @param x coordinate
         * @param y coordinate
         */
        public Cell(int x, int y) {

            this.coordinates = new DiscreteCoordinates(x,y);
        }

        /**
         * Getter of the interactableSet
         * @return (<Set>Interactable) the set of interactables
         */
        public Set<Interactable> getInteractableSet() {
            return interactableSet;
        }

        @Override
        public List<DiscreteCoordinates> getCurrentCells() {
            List <DiscreteCoordinates> currentCells = new ArrayList<>();
            currentCells.add(this.coordinates);
            return currentCells;
        }

        /**
         * Make an (Interactable) enter
         * @param interactable
         */
        private void enter(Interactable interactable) {
            interactableSet.add(interactable);
        }

        /**
         * Make an (Interactable) leave
         * @param interactable
         */
        private void leave(Interactable interactable) {
            interactableSet.remove(interactable);
        }

        /**
         * Returns if an Interactable canEnter
         * @param entity (Interactable)
         * @return canEnter
         */
        protected boolean canEnter(Interactable entity) {
            //return true if entity a le droit de s'ajouter au contenu
            return false;
        }

        /**
         * Returns if an Interactable canLeave
         * @param entity (Interactable)
         * @return canLeave
         */
        protected boolean canLeave(Interactable entity) {
            //return true if entity a le droit de se barrer du contenu
            return false;
        }

        /**
         * Returns if an Interactable canPassDoor
         * @param entity (Interactable)
         * @return canPassDoor
         */
        protected boolean canPassDoor(Interactable entity) {
            return false;
        }

        /**
         * Receives the Interactor and checks if her list of Interactables
         * wants a cell interaction
         * @param interactor the (Interactor)
         */
        private void cellInteractionOf(Interactor interactor) {
            for (Interactable interactable : interactableSet) {
                if (interactable.isCellInteractable()) {
                    interactor.interactWith(interactable);
                }
            }
        }

        /**
         * Receives the Interactor and checks if her list of Interactables
         * wants a view interaction
         * @param interactor the (Interactor)
         */
        private void viewInteractionOf(Interactor interactor) {
            for (Interactable interactable : interactableSet) {
                if (interactable.isViewInteractable()) {
                    interactor.interactWith(interactable);
                }
            }
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {
        }
    }
}
