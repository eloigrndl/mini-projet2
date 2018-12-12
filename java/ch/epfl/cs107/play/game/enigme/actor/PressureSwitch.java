package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class PressureSwitch extends AreaEntity implements Logic {

    //(PressureSwitch) properties
    private Sprite pressureSwitch;
    private boolean activated;

    /**
     * PressureSwitch Constructor
     * @param area the current area
     * @param position the current position
     */
    public PressureSwitch(Area area, DiscreteCoordinates position) {
        super(area, Orientation.UP, position);
        this.pressureSwitch = new Sprite("GroundLightOff", 1, 1.f, this);
        this.activated = false;
    }

    @Override
    public void draw(Canvas canvas) {
        pressureSwitch.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
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
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public void update(float deltaTime) {
        if(activated){
            this.pressureSwitch = new Sprite("GroundLightOn", 1, 1.f, this);
        }else{
            this.pressureSwitch = new Sprite("GroundLightOff", 1, 1.f, this);
        }

    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }

    /**
     * Setter to update the state of PressureSwitch activation
     */
    protected void setActivated() {
        this.activated = !activated;
    }

    @Override
    public boolean isOn() {
        return activated;
    }

    @Override
    public float getIntensity() {
        return (activated ? 1.0f : 0.0f);
    }
}
