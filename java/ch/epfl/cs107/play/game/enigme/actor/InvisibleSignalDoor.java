package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;

public class InvisibleSignalDoor extends SignalDoor {

    //(InvisibleSignalDoor) attributes
    private Logic signal;

    /**
     * Door constructor
     * @param areaOfMemebership aire
     * @param destination aire vers laquelle doit mener la porte
     * @param coordinatesArrival les coordonnées d'arrivée dans l'aire de destination
     * @param orientation l'orientation
     * @param principalCoordinate coordonnées principales de la porte
     * @param rayonPosition coordonnées qu'occupent la porte (en plus de sa coordonnée principale)
     */
    public InvisibleSignalDoor(Area areaOfMemebership, String destination, DiscreteCoordinates coordinatesArrival, Orientation orientation,
                               DiscreteCoordinates principalCoordinate, Circle rayonPosition, Logic signal){
        super(areaOfMemebership, destination, coordinatesArrival, orientation, principalCoordinate, rayonPosition, signal);
        this.signal = signal;

    }

    @Override
    public void draw(Canvas canvas) {}

    @Override
    public boolean isOn() {
        return signal.isOn();
    }

    @Override
    public float getIntensity() {
        return (signal.isOn() ? 1.0f : 0.0f);
    }

    @Override
    public boolean isCellInteractable() {
        return signal.isOn();
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }

}
