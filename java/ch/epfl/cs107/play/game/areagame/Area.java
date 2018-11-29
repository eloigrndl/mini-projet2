package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.math.Vector;
import javafx.scene.Camera;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {


    /// The behavior Map
    private AreaBehavior areaBehavior;

    // Context objects
    private Window window;
    private FileSystem fileSystem;
    //List of Actors inside the area
    private List<Actor> actors;

    private List<Actor> registeredActors;
    private List<Actor> unregisteredActors;

    //Camera Parameter
    // actor on which thz camera is centered
    private Actor viewCandidate;
    // efective center of the view
    private Vector viewCenter;

	/** @return (float): camera scale factor, assume it is the same in x and y direction */
    public abstract float getCameraScaleFactor();

    public final void setViewCandidate(Actor a){

        this.viewCandidate = a;
    }

    /**
     * Add an actor to the actors list
     * @param a (Actor): the actor to add, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void addActor(Actor a, boolean forced) {
        boolean errorOccured = !actors.add(a);

        if(errorOccured && !forced) {
            System.out.println("Actor " + a + " cannot be completely added, so remove it from where it was");
            removeActor(a, true);
        }
    }

    /**
     * Remove an actor form the actor list
     * @param a (Actor): the actor to remove, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void removeActor(Actor a, boolean forced){
        boolean errorOccured = !actors.remove(a);

        if(errorOccured && !forced){
            System.out.print("Actor " + a + " cannot be completely removed");
            addActor(a,true);
        }
    }

    /**
     * Register an actor : will be added at next update
     * @param a (Actor): the actor to register, not null
     * @return (boolean): true if the actor is correctly registered
     */
    public final boolean registerActor(Actor a){
    	if(this.registeredActors == null) {
    		this.registeredActors = new ArrayList<Actor>();
    	}
    	System.out.println("a is" + a);
        this.registeredActors.add(a);
        if (Arrays.asList(this.registeredActors).contains(a)){
            return true;
        } else {
            return false;
        }


    }

    /**
     * Unregister an actor : will be removed at next update
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): true if the actor is correctly unregistered
     */
    public final boolean unregisterActor(Actor a){
    	if(this.unregisteredActors == null) {
    		this.unregisteredActors = new ArrayList<Actor>();
    	}
        this.unregisteredActors.add(a);
        if (Arrays.asList(this.unregisteredActors).contains(a)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Getter for the area width
     * @return (int) : the width in number of cols
     */
    public final int getWidth(){

        return areaBehavior.getBehaviorMapSize()[0];
    }

    /**
     * Getter for the area height
     * @return (int) : the height in number of rows
     */
    public final int getHeight(){

        return areaBehavior.getBehaviorMapSize()[1];
    }

    /** @return the Window Keyboard for inputs */
    public final Keyboard getKeyboard () {
        // TODO implements me #PROJECT #TUTO
        return window.getKeyboard();
    }

    /// Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        //définit les variables réutilisées dans les autre méthodes
        this.window = window;
        this.fileSystem = fileSystem;

        //Initialisation de la liste d'acteurs
        this.actors = new LinkedList<>();

        //Initialization of center of the view/actor of view
        viewCenter = Vector.ZERO;
        setViewCandidate(null);

        return true;
    }

    /**
     * Resume method: Can be overridden
     * @param window (Window): display context, not null
     * @param fileSystem (FileSystem): given file system, not null
     * @return (boolean) : if the resume succeed, true by default
     */
    public boolean resume(Window window, FileSystem fileSystem) {
        return true;
    }

    private final void purgeRegistration(){

        if (this.registeredActors != null){
            for(int j=0; j<(this.registeredActors.size()); ++j){
                addActor(this.registeredActors.get(j),false);
            }
        }
       if (this.unregisteredActors != null) {
           for (int k = 0; k < (this.unregisteredActors.size()); ++k) {
               removeActor(this.unregisteredActors.get(k), false);
           }
       }

       this.registeredActors = null;
       this.unregisteredActors = null;

    }

    @Override
    public void update(float deltaTime) {

        purgeRegistration();

        updateCamera();
        Keyboard keyboard = window.getKeyboard();
        Button downArrow = keyboard.get(Keyboard.DOWN);
        for (int i = 0; i < actors.size(); ++i) {
            actors.get(i).draw(window);
        }

    }

    private void updateCamera () {
        if(viewCandidate!=null){
           viewCenter = viewCandidate.getPosition();
        }
        Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
        window.setRelativeTransform(viewTransform);

    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend(){
        // Do nothing by default
        purgeRegistration();
    }


    @Override
    public void end() {
        // TODO save the AreaState somewhere
    }

    protected final void setBehavior(AreaBehavior ab){
        this.areaBehavior = ab;
    }

}
