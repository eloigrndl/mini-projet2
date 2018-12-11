package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PressurePlate extends AreaEntity implements Logic {

    private Sprite pressurePlate;
    private Logic signal;
    private boolean activated;
    private float activationTime = 5f;

    public PressurePlate(Area area, DiscreteCoordinates position) {
        super(area, Orientation.UP, position);
        this.pressurePlate = new Sprite("GroundPlateOff", 1, 1.f, this);
        this.activated = false;
    }

    @Override
    public void draw(Canvas canvas) {
        pressurePlate.draw(canvas);
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
            this.pressurePlate = new Sprite("GroundLightOn", 1, 1.f, this);
            if ((activationTime - deltaTime) > 0) {
                activated = true;
                activationTime -= deltaTime;
            } else {
                activated = false;
                activationTime = 5f;
            }

        } else {
            this.pressurePlate = new Sprite("GroundPlateOff", 1, 1.f, this);
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }

    protected void setActivated() {
        this.activated = true;
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
