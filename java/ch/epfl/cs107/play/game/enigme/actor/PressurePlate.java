package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;

public class PressurePlate implements Actor, Interactable {

    private Area area;
    private DiscreteCoordinates position;
    private Sprite pressurePlate;
    private Logic signal;
    private boolean activated;
    private final float activationTime = 0.3f;

    public PressurePlate(Area area, DiscreteCoordinates position) {
        this.area = area;
        this.position = position;
        this.signal = Logic.FALSE;
        this.pressurePlate = new Sprite("GroundPlateOff", 1, 1.f, this);
        this.activated = false;
    }

    @Override
    public void draw(Canvas canvas) {
        pressurePlate.draw(canvas);
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
            this.pressurePlate = new Sprite("GroundPlateOn", 1, 1.f, this);
            this.signal = Logic.TRUE;
            this.activated = false;
            update(activationTime);
        }else{
            this.pressurePlate = new Sprite("GroundPlateOff", 1, 1.f, this);
            this.signal = Logic.FALSE;
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }
}
