package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {

    private boolean passingDoor;
    private Sprite ghost = new Sprite("ghost.1", 1, 1.f, this);
    private Door lastDoor;

    private final static int ANIMATION_DURATION = 5;

    private final EnigmePlayerHandler handler;

    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position){
        super(area, orientation, position);
        super.setOrientation(Orientation.DOWN);

        handler = new EnigmePlayerHandler();
    }

    @Override
    public boolean isCellInteractable() {
        return true;
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
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        List<DiscreteCoordinates> fieldOfViewCells = new ArrayList<>();
        for (DiscreteCoordinates coordinates : getCurrentCells()) {
            fieldOfViewCells.add(coordinates.jump(getOrientation().toVector()));
        }

        return fieldOfViewCells;
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        Button LButton = keyboard.get(Keyboard.L);

        if (LButton.isPressed()) {
            return true;
        }

        return false;
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
                super.move(ANIMATION_DURATION);
            } else {
                super.setOrientation(Orientation.LEFT);
            }
        }

        if(rightArrow.isDown()) {
            if (getOrientation().equals(Orientation.RIGHT)) {
                super.move(ANIMATION_DURATION);
            } else {
                super.setOrientation(Orientation.RIGHT);
            }
        }

        if(upArrow.isDown()) {
            if (getOrientation().equals(Orientation.UP)) {
                super.move(ANIMATION_DURATION);
            } else {
                super.setOrientation(Orientation.UP);
            }
        }

        if(downArrow.isDown()) {
            if (getOrientation().equals(Orientation.DOWN)) {
                super.move(ANIMATION_DURATION);
            } else {
                super.setOrientation(Orientation.DOWN);
            }
        }
        super.update(deltaTime);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }

    public void enterArea(Area area, DiscreteCoordinates position){
        area.registerActor(this);
        setOwnerArea(area);
        area.setViewCandidate(this);
        super.setCurrentPosition(position.toVector());
        resetMotion();
    }

    public void leaveArea(){
        getOwnerArea().unregisterActor(this);
    }

    public void setIsPassingDoor(Door door){
      if (getOwnerArea().passDoor(this,door.getCurrentCells())){
            passingDoor = true;
            lastDoor = door;
      } else {
          passingDoor = false;
      }
    }

    public void resetIsPassingDoor(){
        passingDoor = false;
    }

    public boolean isPassingDoor()
    {
        return passingDoor;
    }

    public Door getLastDoor(){
        return lastDoor;
    }

    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    /**
     * La classe EnigmePlayerHandler permet de déléguer la gestion des interactions.
     * Elle envisage et définit des méthodes pour *tous* les cas possibles.
     */
    class EnigmePlayerHandler implements EnigmeInteractionVisitor {

        @Override
        public void interactWith(Apple apple) {
            //gère ce qui se passe lorsque le personnage interagit avec une pomme
            apple.setCollected(true);
            getOwnerArea().unregisterActor(apple);
        }

        @Override
        public void interactWith(Door door) {
            // gère ce qui se passe lorsque le personnage passe les porte
            setIsPassingDoor(door);
        }

        @Override
        public void interactWith(EnigmeBehavior.EnigmeCell cell) {
        }
    }
}


