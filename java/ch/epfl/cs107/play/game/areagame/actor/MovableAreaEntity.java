package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

    /// Indicate if the actor is currently moving
    private boolean isMoving;
    /// Indicate how many frames the current move is supposed to take
    private int framesForCurrentMove;
    /// The target cell (i.e. where the mainCell will be after the motion)
    private DiscreteCoordinates targetMainCellCoordinates;

    /**
     * Getter for the leaving cells which are the current cells.
     * @return (List<DiscreteCoordinates>)
     */
    protected final List<DiscreteCoordinates> getLeavingCells() {
        return getCurrentCells();
    }

    /**
     * Getter for the entering cells
     * "Les cellules investies seront toutes celles qui parmi les projections des cellules courantes dans la direction de l'acteur font partie de la grille.
     * @return (List<DiscreteCoordinates>)
     */
    protected final List<DiscreteCoordinates> getEnteringCells() {
        List<DiscreteCoordinates> currentCells = getCurrentCells();
        List<DiscreteCoordinates> enteringCells = new ArrayList<DiscreteCoordinates>();

        for (int i=0; i<currentCells.size(); ++i) {
            //Iterate through
            DiscreteCoordinates currentCell = currentCells.get(i);

            //Coordonnées dans la direction de l'acteur
            DiscreteCoordinates projectedCoordinates = currentCell.jump(getOrientation().toVector());

            //Position dans la grille
            int positionX = projectedCoordinates.x;
            int positionY = projectedCoordinates.y;

            //regarde si ces coordonnées font partie de la grille et si oui les ajoute à la liste de nouvelles coordonnées
            if(positionX < getaOwnerArea().getHeight() && positionY < getaOwnerArea().getWidth()){
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
     * Decide if a cell can move and if yes initalize it
     * @param framesForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
    protected boolean move(int framesForMove){


        if (!isMoving || getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) { //Si l'acteur ne bouge pas OU s'il a atteint sa cellule cible
            //Demander à son aire s'il est possible de quitter les cellules données par getLeavingCells() et d'entrer dans les cellules getEnteringCells()
            if (getaOwnerArea().leaveAreaCells(this, getLeavingCells()) && getaOwnerArea().enterAreaCells(this, getEnteringCells())) {
                if (framesForMove < 1) {
                    framesForCurrentMove = 1;
                } else {
                    framesForCurrentMove = framesForMove;
                }
                Vector orientation = getOrientation().toVector();
                targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
                isMoving = true;
                return true;
            } else { //Déplcement pas possible : move retourne false
                return false;
            }
        }
        return false;
    }

    /// MovableAreaEntity implements Actor

    @Override
    public void update(float deltaTime) {
        if (isMoving && !(getCurrentMainCellCoordinates().equals(targetMainCellCoordinates))) {
            //si l'acteur bouge et que la cible n'est pas atteinte, le déplacer
            Vector distance = getOrientation().toVector();
            distance = distance.mul(1.0f / framesForCurrentMove);
            setCurrentPosition(getPosition().add(distance));
            //isMoving = false;
        } else {
            //Sinon reset motion
            resetMotion();
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
}
