package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.*;

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

    default void interactWith(EnigmePlayer player) {
        //by default, interaction is empty
    }

    default void interactWith(EnigmeBehavior.EnigmeCell cell) {
        //by default, interaction is empty
    }

    default void interactWith(Key key) {
        //by default, interaction is empty
    }

    default void interactWith(Torch torch) {
        //by default, interaction is empty
    }

    default void interactWith(PressurePlate pressurePlate) {
        //by default, interaction is empty
    }

    default void interactWith(PressureSwitch pressureSwitch) {
        //by default, interaction is empty
    }

    default void interactWith(Lever lever) {
        //by default, interaction is empty
    }

    default void interactWith(SignalDoor signalDoor) {
        //by default, interaction is empty
    }

    default void interactWith(SignalRock signalRock) {
        //by default, interaction is empty
    }
}
