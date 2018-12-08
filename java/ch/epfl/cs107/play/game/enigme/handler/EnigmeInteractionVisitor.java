package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {

    /**
     * Simulates an interaction between Interactors and Apple in enigme
     * @param apple (Apple), not null
     */
    default void interactWith(Apple apple) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and Door in enigme
     * @param door (Door), not null
     */
    default void interactWith(Door door) {
        //by default, interaction is empty
    }
}
