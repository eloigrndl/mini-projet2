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

public class PressureSwitch implements Actor, Interactable {

    private Area area;
    private DiscreteCoordinates position;
    private Sprite pressureSwitch;
    private Logic signal;
    private boolean activated;

    public PressureSwitch(Area area, DiscreteCoordinates position) {
        this.area = area;
        this.position = position;
        this.signal = Logic.FALSE;
        this.pressureSwitch = new Sprite("GroundLightOff", 1, 1.f, this);
        this.activated = false;
    }

    @Override
    public void draw(Canvas canvas) {
        pressureSwitch.draw(canvas);
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
            this.pressureSwitch = new Sprite("GroundLightOn", 1, 1.f, this);
            signal = Logic.TRUE;
        }else{
            this.pressureSwitch = new Sprite("GroundLightOff", 1, 1.f, this);
            signal = Logic.FALSE;
        }

    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }
}
