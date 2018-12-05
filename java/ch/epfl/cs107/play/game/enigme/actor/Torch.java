package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;
import com.sun.org.apache.bcel.internal.generic.FALOAD;

import java.util.ArrayList;
import java.util.List;

public class Torch implements Actor, Interactable {
    private Area area;
    private DiscreteCoordinates position;
    private Sprite torch;
    private Logic signal;
    private boolean fired;

    public Torch(Area area, DiscreteCoordinates position, Logic signal) {
        this.area = area;
        this.position = position;
        this.signal = signal;
        if(signal == Logic.FALSE){
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
            signal = Logic.TRUE;
        }else{
            this.torch = new Sprite("torch.ground.off", 1, 1.f, this);
            signal = Logic.FALSE;
        }
    }
}
