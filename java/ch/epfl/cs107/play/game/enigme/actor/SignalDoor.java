package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class SignalDoor extends Door implements Logic {

    private Logic signal;

    public SignalDoor(Area areaOfMemebership, String destination, DiscreteCoordinates coordinatesArrival, Orientation orientation,
                      DiscreteCoordinates principalCoordinate, Circle rayonPosition, Logic signal){

        super(areaOfMemebership, destination, coordinatesArrival, orientation, principalCoordinate, rayonPosition, signal.isOn());

        this.signal = signal;

    }

    @Override
    public void update(float deltaTime) {

        super.updateDoor(signal.isOn());

        super.update(deltaTime);
    }

    /// Logic
    @Override
    public boolean isOn() {
        return signal.isOn();
    }

    @Override
    public float getIntensity() {
        if (signal.isOn()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isCellInteractable() {
        if (signal.isOn()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean takeCellSpace() {
        return !signal.isOn();
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }

}
