package ch.epfl.cs107.play.game.enigme.actor.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

public class Demo2Player extends MovableAreaEntity implements Interactable {

    private boolean passingDoor;
    private final Sprite ghost = new Sprite("ghost.1", 1, 1.f, this);
    private final static int ANIMATION_DURATION = 8;

    /**
     * Demo2Player Constructor
     * @param area current area
     * @param orientation current orientation
     * @param position current position
     */
    public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position){
        super(area, orientation, position);
        super.setOrientation(Orientation.DOWN);
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    protected boolean move(int framesForMove) {

        boolean move = super.move(ANIMATION_DURATION);

        if (move) {
            if (getOwnerArea().passDoor(this, getEnteringCells())) {
                setPassingDoor(true);
            } else {
                setPassingDoor(false);
            }
        }

        return move;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        ghost.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        Button leftArrow = keyboard.get(Keyboard.LEFT);
        Button rightArrow = keyboard.get(Keyboard.RIGHT);
        Button upArrow = keyboard.get(Keyboard.UP);
        Button downArrow = keyboard.get(Keyboard.DOWN);

        if(leftArrow.isDown()) {
            if (getOrientation().equals(Orientation.LEFT)) {
                move(ANIMATION_DURATION);
            } else {
                super.setOrientation(Orientation.LEFT);
            }
        }

        if(rightArrow.isDown()) {
            if (getOrientation().equals(Orientation.RIGHT)) {
                move(ANIMATION_DURATION);
            } else {
                super.setOrientation(Orientation.RIGHT);
            }
        }

        if(upArrow.isDown()) {
            if (getOrientation().equals(Orientation.UP)) {
                move(ANIMATION_DURATION);
            } else {
                super.setOrientation(Orientation.UP);
            }
        }

        if(downArrow.isDown()) {
            if (getOrientation().equals(Orientation.DOWN)) {
                move(ANIMATION_DURATION);
            } else {
                super.setOrientation(Orientation.DOWN);
            }
        }

        super.update(1);
    }

    /**
     * Method in charge of handling all necessary calls for the Actor to enter the Area
     * @param area the current area
     * @param position the current position
     */
    public void enterArea(Area area, DiscreteCoordinates position){
        area.registerActor(this);
        setOwnerArea(area);
        area.setViewCandidate(this);
        super.setCurrentPosition(position.toVector());
        resetMotion();
    }

    /**
     * Method in charge of handling all necessary calls for the Actor to leave the Area
     */
    public void leaveArea(){
        getOwnerArea().unregisterActor(this);
    }

    /**
     * Getter to know if the Actor is currently passing a door
     * @return isPassingDoor
     */
    public boolean isPassingDoor() {
        return passingDoor;
    }

    /**
     * Setter to know if the actor is passing a door
     * @param isPassing is the actor currently passing a door
     */
    public void setPassingDoor(boolean isPassing) {
        this.passingDoor = isPassing;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }
}
