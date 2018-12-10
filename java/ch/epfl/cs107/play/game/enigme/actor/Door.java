package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Door extends AreaEntity {

    private String destination;
    private DiscreteCoordinates principalCoordinate;
    private DiscreteCoordinates coordinatesArrival;
    private Circle currentCoordinates;
    private Sprite door;
    private boolean opened;

    /**x
     *
     * @param areaOfMemebership aire
     * @param destination aire vers laquelle doit mener la porte
     * @param coordinatesArrival les coordonnées d'arrivée dans l'aire de destination
     * @param orientation
     * @param principalCoordinate coordonnées principales de la porte
     * @param rayonPosition coordonnées qu'occupent la porte (en plus de sa coordonnée principale)
     */
    public Door(Area areaOfMemebership, String destination, DiscreteCoordinates coordinatesArrival, Orientation orientation,
                DiscreteCoordinates principalCoordinate, Circle rayonPosition, boolean opened){
        super(areaOfMemebership, orientation, principalCoordinate );
        this.destination = destination;
        this.coordinatesArrival = coordinatesArrival;
        this.currentCoordinates = rayonPosition;
        this.principalCoordinate = principalCoordinate;
        this.opened = opened;
        if(this.opened){
            door = new  Sprite("door.open.1", 1, 1.f, this);
        }else{
            door = new Sprite("door.close.1", 1, 1.0f, this);
        }

    }


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        ArrayList<DiscreteCoordinates> currentCells = new ArrayList<>();
        currentCells.add(principalCoordinate);
        return currentCells;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        door.draw(canvas);
    }


    @Override
    public void update(float deltaTime) {
        if(opened){
            door = new Sprite("door.open.1", 1, 1.f, this);
        }else{
            door = new Sprite("door.close.1", 1, 1.0f, this);
        }
    }

    public String getDestination() {
        return destination;
    }

    public boolean getOpened(){
        return opened;
    }

    public DiscreteCoordinates getCoordinatesArrival() {
        return coordinatesArrival;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }
}

