package ch.epfl.cs107.play.game.areagame.actor;


import ch.epfl.cs107.play.math.DiscreteCoordinates;
import java.util.List;

/**
 * Models objects asking for interaction (i.e. can interact with some Interactable)
 * @see Interactable
 * This interface makes sense only in the "Area Context" with Actor contained into Area Cell
 */
public interface Interactor {

    /**
     * Getter for coordinates of cells
     * @return List of DiscreteCoordinates of cells
     */
    List<DiscreteCoordinates> getCoodinates();

    /**
     * A un certain nombre de cellules dans son champ de vision
     * @return List of DiscreteCoordinates de ces cellules
     */
    List<DiscreteCoordinates> getFieldOfViewCells();

    /**
     * Indique s'il demande une interaction de contact
     * @return boolean
     */
    boolean wantsCellInteraction();

    /**
     * Indique s'il demande une interaction distante
     * @return boolean
     */
    boolean wantsViewInteraction();

}
