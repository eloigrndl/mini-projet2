package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable {

    /// an AreaEntity knows its ow Area
    private Area ownerArea;
    /// Orientatinn of the AreaEntity in the Area
    private Orientation orientation;
    /// Coordinate of the main Cell linked to the entity
    private DiscreteCoordinates currentMainCellCoordinates;

    /**
     * Default AreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {

        // TODO fix me assert impossible
//        assert area != null;
//        assert orientation != null;
//        assert position != null;

        super(position.toVector());

        this.ownerArea = area;
        this.orientation = orientation;
        this.currentMainCellCoordinates = position;
    }


    /**
     * Getter for the coordinates of the main cell occupied by the AreaEntity
     * @return (DiscreteCoordinates)
     */
    protected DiscreteCoordinates getCurrentMainCellCoordinates(){

        DiscreteCoordinates coordinates = new DiscreteCoordinates(currentMainCellCoordinates.x,currentMainCellCoordinates.y);
        return coordinates;
    }

    @Override
    protected void setCurrentPosition(Vector v) {
        //super.setCurrentPosition(v);

        System.out.println("Vecteur X : " + v.x + " Y : " + v.y);

       if (DiscreteCoordinates.isCoordinates(v)) {
           //si les coordonnées sont suffisamment proches d'une coordonnée discrète
           //On arrondit, affecte à la position et met à jour les coordonnées principales

           int vectorX = Math.round(v.x);
           int vectorY = Math.round(v.y);
           currentMainCellCoordinates = new DiscreteCoordinates(vectorX, vectorY);
           v.round();
       }
            super.setCurrentPosition(v);

    }

    /**
     * Setter for the orientation
     */
    protected void setOrientation(Orientation orientation) {
        //pas en déplacement ensuite changer orientation
        this.orientation = orientation;

    }

    /**
     * Getter for the orientation
     * @return (Orientation)
     */
    protected Orientation getOrientation() {
        return orientation;
    }

    public Area getaOwnerArea(){
        return this.ownerArea;
    }

    public void setOwnerArea(Area ownerArea) {
        this.ownerArea = ownerArea;
    }
}
