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

public class SignalDoor extends Door implements Logic {

    private Sprite door;
    private Logic signal;

    public SignalDoor(Area areaOfMemebership, String destination, DiscreteCoordinates coordinatesArrival, Orientation orientation,
                      DiscreteCoordinates principalCoordinate, Circle rayonPosition, Logic signal){

        super(areaOfMemebership, destination, coordinatesArrival, orientation, principalCoordinate, rayonPosition);

        this.signal = signal;

        if(!signal.isOn()){
            this.door = new Sprite("door.close.1", 1, 1.f, this);
        }else{
            this.door = new Sprite("door.open.1", 1, 1.f, this);
        }

    }

    @Override
    public void update(float deltaTime) {
        if(signal.isOn()) {
            this.door = new Sprite("door.open.1", 1, 1.f, this);
        }else{
            this.door = new Sprite("door.close.1", 1, 1.f, this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        door.draw(canvas);
    }

    /// Logic
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
