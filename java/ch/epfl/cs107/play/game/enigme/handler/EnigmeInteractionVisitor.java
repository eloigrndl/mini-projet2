package ch.epfl.cs107.play.game.enigme.handler;

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

    /**
     * Simulates an interaction between Interactors and EnigmePlayer in enigme
     * @param player (EnigmePlayer), not null
     */
    default void interactWith(EnigmePlayer player) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and EnigmeCell in enigme
     * @param cell (EnigmeCell), not null
     */
    default void interactWith(EnigmeBehavior.EnigmeCell cell) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and Key in enigme
     * @param key (Key), not null
     */
    default void interactWith(Key key) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and Torch in enigme
     * @param torch (Torch), not null
     */
    default void interactWith(Torch torch) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and PressurePlate in enigme
     * @param pressurePlate (PressurePlate), not null
     */
    default void interactWith(PressurePlate pressurePlate) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and PressureSwitch in enigme
     * @param pressureSwitch (PressureSwitch), not null
     */
    default void interactWith(PressureSwitch pressureSwitch) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and Lever in enigme
     * @param lever (Lever), not null
     */
    default void interactWith(Lever lever) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and SignalDoor in enigme
     * @param signalDoor (SignalDoor), not null
     */
    default void interactWith(SignalDoor signalDoor) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and SignalRock in enigme
     * @param signalRock (SignalRock), not null
     */
    default void interactWith(SignalRock signalRock) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and SignalRing in enigme
     * @param signalRing (SignalRing), not null
     */
    default void interactWith(SignalRing signalRing) {
        //by default, interaction is empty
    }

    /**
     * Simulates an interaction between Interactors and InvisibleSignalDoor in enigme
     * @param invisibleSignalDoor (InvisibleSignalDoor), not null
     */
    default void interactWith(InvisibleSignalDoor invisibleSignalDoor) {
        //by default, interaction is empty
    }
}
