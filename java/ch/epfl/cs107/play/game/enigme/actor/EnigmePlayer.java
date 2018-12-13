package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Animation;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity implements Interactor, Animation {

    //Player & PlayerAnimation
    private Sprite ghost = new Sprite("max.new.1", 1f, 1f, this, new RegionOfInterest(0,  0, 16, 21), new Vector(0f, 0f));
    private static int ANIMATION_DURATION = 8;

    //Door
    private boolean passingDoor;
    private Door lastDoor;

    //Interactions
    private final EnigmePlayerHandler handler;

    //Dialogs
    private Dialog dialog;
    private boolean showDialog;

    //Interactor attributes
    boolean canUpdatePressureSwitch = true;

    /**
     * EnigmePlayer Constructor
     * @param area current area
     * @param orientation current orientation
     * @param position current position
     */
    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position){
        super(area, orientation, position);
        handler = new EnigmePlayerHandler();
        dialog = new Dialog("", "dialog.1", getOwnerArea());
        showDialog = false;
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

        if (showDialog) {
            dialog.draw(canvas);
        }
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
        Button KKey = keyboard.get(Keyboard.K);
        Button spaceKey = keyboard.get(Keyboard.SPACE);


        if(leftArrow.isDown()) {
            if (getOrientation().equals(Orientation.LEFT)) {
                super.move(ANIMATION_DURATION);
                canUpdatePressureSwitch = true;
            } else {
                super.setOrientation(Orientation.LEFT);
            }
        }

        if(rightArrow.isDown()) {
            if (getOrientation().equals(Orientation.RIGHT)) {
                super.move(ANIMATION_DURATION);
                canUpdatePressureSwitch = true;
            } else {
                super.setOrientation(Orientation.RIGHT);
            }
        }

        if(upArrow.isDown()) {
            if (getOrientation().equals(Orientation.UP)) {
                super.move(ANIMATION_DURATION);
                canUpdatePressureSwitch = true;
            } else {
                super.setOrientation(Orientation.UP);
            }
        }

        if(downArrow.isDown()) {
            if (getOrientation().equals(Orientation.DOWN)) {
                super.move(ANIMATION_DURATION);
                canUpdatePressureSwitch = true;
            } else {
                super.setOrientation(Orientation.DOWN);
            }
        }

        if (KKey.isPressed()) {
            dialog.resetDialog("");
            showDialog = false;
        }

        if(spaceKey.isDown()){
            ANIMATION_DURATION = 4;
        }else{
            ANIMATION_DURATION = 8;
        }

        super.update(deltaTime);
        ghost = animPerso(getOrientation(), inMoveFrame, ghost);

    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }

    /**
     * Method handling all the calls for the Actor when he enters an Area
     * @param area Area to enter
     * @param position new position
     */
    public void enterArea(Area area, DiscreteCoordinates position){
        area.registerActor(this);
        setOwnerArea(area);
        area.setViewCandidate(this);
        super.setCurrentPosition(position.toVector());
        resetMotion();
    }

    /**
     * Method handling the calls for the Actor when he leaves an Area
     */
    public void leaveArea(){
        getOwnerArea().unregisterActor(this);
    }

    /**
     * Setter to know if the Actor is passing a door
     * @param door door he is passing
     */
    public void setIsPassingDoor(Door door){
      if (getOwnerArea().passDoor(this,door.getCurrentCells())){
            passingDoor = true;
            lastDoor = door;
      } else {
          passingDoor = false;
      }
    }

    /**
     * Reset isPassingDoor
     */
    public void resetIsPassingDoor(){
        passingDoor = false;
    }

    /**
     * Getter to know if the Actor is passing a door
     * @return (boolean) actor currently passing a door
     */
    public boolean isPassingDoor()
    {
        return passingDoor;
    }

    /**
     * Getter to know which door was passed last
     * @return (Door) the last Door
     */
    public Door getLastDoor(){
        return lastDoor;
    }

    /**
     * Interactor method to launch interactions
     * @param other Interactable with which we "work"
     */
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    /**
     * Handling the dialogs, so that they can appear and disappear using
     * our constraints
     * @param text Text to appear in dialog
     */
    private void showDialog(String text) {
        dialog.resetDialog(text);
        showDialog = true;
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
            showDialog("Yummy !");
        }

        @Override
        public void interactWith(Door door) {
            // gère ce qui se passe lorsque le personnage passe les porte
            setIsPassingDoor(door);
        }

        @Override
        public void interactWith(EnigmeBehavior.EnigmeCell cell) {
        }

        @Override
        public void interactWith(Key key) {
            System.out.println("interact with key");
            key.setCollected();
            showDialog("It seems this key can open a door.");
        }

        @Override
        public void interactWith(Torch torch) {
            torch.updateFired();
        }

        @Override
        public void interactWith(PressurePlate pressurePlate) {
            pressurePlate.setActivated();
        }

        @Override
        public void interactWith(PressureSwitch pressureSwitch) {

            boolean isOnSwitch = false;

            if (!getIsMoving() && canUpdatePressureSwitch) {
                for (DiscreteCoordinates pressureSwitchCoordinate : pressureSwitch.getCurrentCells()) {
                    DiscreteCoordinates coordinate = getCurrentMainCellCoordinates();
                    if (pressureSwitchCoordinate.equals(coordinate)) {
                        isOnSwitch = true;
                    }
                }

                if (isOnSwitch) {
                    pressureSwitch.setActivated();
                    canUpdatePressureSwitch = false;
                }
            }
        }

        @Override
        public void interactWith(Lever lever) {
            lever.setPushed();
        }

        @Override
        public void interactWith(SignalDoor signalDoor) {
            setIsPassingDoor(signalDoor);
        }

        @Override
        public void interactWith(SignalRock signalRock) {}

        @Override
        public void interactWith(HelpingPerson helpingPerson) {
            showDialog(helpingPerson.whatToSay);
        }

        @Override
        public void interactWith(Gelly gelly) {
            gelly.setCollected(true);
            getOwnerArea().unregisterActor(gelly);
            showDialog("I love Gelly");

        }
    }
}
