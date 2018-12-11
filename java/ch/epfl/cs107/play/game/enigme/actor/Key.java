package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.Area;
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

public class Key extends AreaEntity implements Logic {

    private Sprite key;
    private boolean pickedUp;
    private boolean visible;
    private boolean isViewInteractable;

    public Key(Area area, DiscreteCoordinates position) {
        super(area, Orientation.UP, position);
        this.pickedUp = false;
        this.key = new Sprite("key.1", 1.f, 1.f, this);
        this.visible = true;
        this.isViewInteractable = true;
    }

    @Override
    public void draw(Canvas canvas) {
        if (!pickedUp) {
            key.draw(canvas);
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return visible;
    }

    @Override
    public boolean isViewInteractable() {
        return isViewInteractable;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public void update(float deltaTime) {
        if(!pickedUp) {
            visible = true;
            isViewInteractable = true;
        }else{
            visible = false;
            isViewInteractable = false;
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }

    protected void setCollected() {
        this.pickedUp = true;
    }

    @Override
    public boolean isOn() {
        return !isViewInteractable;
    }

    @Override
    public float getIntensity() {
        return (isViewInteractable? 0.0f : 1.0f);
    }
}
