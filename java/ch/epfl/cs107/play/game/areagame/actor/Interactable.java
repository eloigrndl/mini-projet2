package ch.epfl.cs107.play.game.areagame.actor;


import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.List;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {

    /**
     * Un 'Interactable' occupe une liste de cellules
     * @return (List<DiscreteCoordinates>)
     */
    List<DiscreteCoordinates> getCurrentCells();

    /**
     * Peut rendre une cellule non traversable si elle est déjà occupée
     * Si return true : non traversable
     * @return (boolean)
     */
    boolean takeCellSpace();

    /**
     * Accepte les interactions distantes
     * @return (boolean)
     */
    boolean isViewInteractable();

    /**
     * Accepte les interactions de contact
     * @return (boolean)
     */
    boolean isCellInteractable();

    /**
     * Gère les autorisations d'interactions
     * @param v
     */
    void acceptInteraction(AreaInteractionVisitor v);
}
