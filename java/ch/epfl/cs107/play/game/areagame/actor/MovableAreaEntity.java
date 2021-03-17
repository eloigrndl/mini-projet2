package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Animation;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import java.util.List;
import java.util.ArrayList;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity implements Animation {

    /// Indicate if the actor is currently moving
    private boolean isMoving;
    /// Indicate how many frames the current move is supposed to take
    private int framesForCurrentMove;
    /// The target cell (i.e. where the mainCell will be after the motion)
    private DiscreteCoordinates targetMainCellCoordinates;

    //Number used to update the Sprite when actor is moving
    protected int inMoveFrame = 0;

    /**
     * Getter for the leaving cells which are the current cells.
     * @return (List<DiscreteCoordinates>)
     */
    protected final List<DiscreteCoordinates> getLeavingCells() {
        return getCurrentCells();
    }

    /**
     * Getter for the entering cells
     * @return (List<DiscreteCoordinates>)
     */
    protected final List<DiscreteCoordinates> getEnteringCells() {
        List<DiscreteCoordinates> currentCells = getCurrentCells();
        List<DiscreteCoordinates> enteringCells = new ArrayList<>();

        for (DiscreteCoordinates currentCell : currentCells) {

            //Actor directions coordinates
            DiscreteCoordinates projectedCoordinates = currentCell.jump(getOrientation().toVector());

            //Position on the grid
            int positionX = projectedCoordinates.x;
            int positionY = projectedCoordinates.y;

            //If the coordinates are inside the grid, we add them
            if (positionX < getOwnerArea().getWidth() && positionY < getOwnerArea().getHeight()) {
                enteringCells.add(projectedCoordinates);
            }
        }
        return enteringCells;
    }

    /**
     * Default MovableAreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
    public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        resetMotion();
    }

    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion(){
        isMoving = false;
        framesForCurrentMove = 0;
        targetMainCellCoordinates = getCurrentMainCellCoordinates();
    }

    /**
     * Decide if a cell can move and if yes initialize it
     * @param framesForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
    protected boolean move(int framesForMove){

        if (!isMoving || getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {

            if (getOwnerArea().leaveAreaCells(this, getLeavingCells()) && getOwnerArea().enterAreaCells(this, getEnteringCells())) {
                framesForCurrentMove = Math.max(framesForMove, 1);
                Vector orientation = getOrientation().toVector();
                targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
                isMoving = true;
                return true;
            } else {
                //Impossible movement : return false
                return false;
            }
        }
        return false;
    }

    /// MovableAreaEntity implements Actor

    @Override
    public void update(float deltaTime) {

        if (isMoving && !(getCurrentMainCellCoordinates().equals(targetMainCellCoordinates))) {
            Vector distance = getOrientation().toVector();
            distance = distance.mul(1.0f / framesForCurrentMove);
            setCurrentPosition(getPosition().add(distance));
            inMoveFrame +=1;
            if(inMoveFrame >= 4){
                inMoveFrame -=4;
            }

        } else {
            resetMotion();
            inMoveFrame = 0;
        }

    }

    @Override
    protected void setOrientation(Orientation orientation) {
        if (!isMoving) {
            super.setOrientation(orientation);
        }
    }

    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        // TODO implements me #PROJECT #TUTO
        // the velocity must be computed as the orientation vector (getOrientation().toVector() mutiplied by 
    	// framesForCurrentMove
        return null;
    }

    /**
     * Getter for the isMoving property
     * @return (boolean) isMoving
     */
    protected boolean getIsMoving() {
        return (isMoving && !(getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)));
    }
}
