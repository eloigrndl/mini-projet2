package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Key implements Actor, Interactable {

    private Area area;
    private DiscreteCoordinates position;
    private Sprite key;
    private boolean pickedUp;
    private boolean visible;
    private boolean isViewInteractable;
    private Logic signal;

public Key(Area area, DiscreteCoordinates position) {
        this.area = area;
        this.position = position;
        this.pickedUp = false;
        this.key = new Sprite("key.1", 1, 1.f, this);
        this.signal = Logic.FALSE;
        this.visible = true;
        this.isViewInteractable = true;

    }

    @Override
    public void draw(Canvas canvas) {
        key.draw(canvas);
    }

    @Override
    public Transform getTransform() {
        return null;
    }

    @Override
    public Vector getVelocity() {
        return null;
    }


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        List<DiscreteCoordinates> currentCells = new ArrayList<DiscreteCoordinates>();
        currentCells.add(this.position);
        return currentCells;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
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
            signal = Logic.FALSE;
        }else{
            visible = false;
            isViewInteractable = false;
            signal = Logic.TRUE;
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }
}
