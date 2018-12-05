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

import java.util.ArrayList;
import java.util.List;

public class Lever implements Actor, Interactable {

    private Area area;
    private DiscreteCoordinates position;
    private Sprite key;
    //false = pushed right / true = pushed left
    private boolean pushed;
    private Logic signal;

    public Lever(Area area, DiscreteCoordinates position) {
        this.area = area;
        this.position = position;
        this.key = new Sprite("lever.big.right", 1, 1.f, this);
        this.pushed = false;
        this.signal = Logic.FALSE;
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
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public void update(float deltaTime) {
        if (pushed) {
            this.key = new Sprite("lever.big.left", 1, 1.f, this);
            this.signal = Logic.TRUE;
        } else {
            this.key = new Sprite("lever.big.right", 1, 1.f, this);
            this.signal = Logic.FALSE;
        }
    }
}
