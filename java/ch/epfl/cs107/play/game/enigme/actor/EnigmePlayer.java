package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import java.util.Collections;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity implements Interactable {

    private boolean passingDoor;
    private Sprite ghost = new Sprite("ghost.1", 1, 1.f, this);
    private Door lastPassedDoor;
    private final static int ANIMATION_DURATION = 8;

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
    public void draw(Canvas canvas) {
        ghost.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    public void enterArea(Area area, DiscreteCoordinates position){
        area.registerActor(this);
        setOwnerArea(area);
        area.setViewCandidate(this);
        super.setCurrentPosition(position.toVector());
        resetMotion();
    }

    public void leaveArea(){
        getaOwnerArea().unregisterActor(this);
    }

    public void setIsPassingDoor(Door door){
      if(door.getOpened()){
            passingDoor = true;
            lastPassedDoor = door;
      }else{
         passingDoor = false;
      }
    }
    public boolean isPassingDoor() {
        return passingDoor;
    }

    public Door passedDoor(){
        return lastPassedDoor;
    }

    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }
}

/**
 * La classe EnigmePlayerHandler permet de déléguer la gestion des interactions.
 * Elle envisage et définit des méthodes pour *tous* les cas possibles.
 */
class EnigmePlayerHandler implements EnigmeInteractionVisitor {

    @Override
    public void interactWith(Apple apple) {
        //gère ce qui se passe lorsque le personnage interagit avec une pomme
    }

    @Override
    public void interactWith(Door door) {
        // gère ce qui se passe lorsque le personnage passe les porte
    }
}


