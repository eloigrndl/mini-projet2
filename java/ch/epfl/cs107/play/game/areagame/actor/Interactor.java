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
     * Un 'Interactor' occupe une liste de cellules
     * @return (List<DiscreteCoordinates>)
     */
    List <DiscreteCoordinates> getCurrentCells();Interactable

    /**
     * Les cellules dans le champ de vision de l'Interactor
     * @return (List<DiscreteCoordinates>)
     */
    List<DiscreteCoordinates> getFieldOfViewCells();

    /**
     * Self-explanatory.
     * @return (boolean)
     */
    boolean wantsCellInteraction();

    /**
     * Self-explanatory.
     * @return (boolean)
     */
    boolean wantsViewInteraction();

    /**
     * Method for an Interactor to interract with an Interactable
     * @param other Interactable with which we "work"
     */
    void interactWith(Interactable other);

}
