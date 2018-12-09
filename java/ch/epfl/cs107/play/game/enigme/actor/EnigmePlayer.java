package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import java.util.Collections;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity implements Interactable {

    private boolean passingDoor;
    private Sprite ghost = new Sprite("ghost.1", 1, 1.f, this);
    private Door PassedDoor;

    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position){
        super(area, orientation, position);
        super.setOrientation(Orientation.DOWN);
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
        getOwnerArea().unregisterActor(this);
    }



    public void setPassingDoor(Door door){
      if(getOwnerArea().getAreaBehavior().canPassDoor(this, door.getCurrentCells())){
            passingDoor = true;
            PassedDoor = door;
      }else{
         passingDoor = false;
      }
    }
    public boolean isPassingDoor()
    {
        return passingDoor;
    }
    public Door passedDoor(){
        return PassedDoor;
    }
}


