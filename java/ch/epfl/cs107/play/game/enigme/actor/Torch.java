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

public class Torch extends AreaEntity implements Logic {

    private Sprite torch;
    private boolean fired;

    public Torch(Area area, DiscreteCoordinates position, Logic signal) {
        super(area, Orientation.UP, position);

        if(!isOn()){
            this.torch = new Sprite("torch.ground.off", 1, 1.f, this);
            this.fired = false;
        }else{
            this.torch = new Sprite("torch.ground.on.1", 1, 1.f, this);
            this.fired = true;
        }

    }

    @Override
    public void draw(Canvas canvas) {
        torch.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public void update(float deltaTime) {
        if(fired) {
            this.torch = new Sprite("torch.ground.on.1", 1, 1.f, this);
        }else{
            this.torch = new Sprite("torch.ground.off", 1, 1.f, this);
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }

    protected void updateFired() {
        this.fired = !fired;
    }

    @Override
    public boolean isOn() {
        return fired;
    }

    @Override
    public float getIntensity() {
        return (fired ? 1.0f : 0.0f);
    }
}
