package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.math.Vector;

import java.util.*;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {

    // The behavior Map
    private AreaBehavior areaBehavior;

    // Context objects
    private Window window;
    private FileSystem fileSystem;

    // List of Actors inside the area
    private List<Actor> actors;

    private List<Actor> registeredActors = new LinkedList<>();
    private List<Actor> unregisteredActors = new LinkedList<>();

    // Camera Parameters
    // Actor on which the camera is centered
    private Actor viewCandidate;
    // Effective center of the view
    private Vector viewCenter;

    //List of Interactables
    private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter = new HashMap<>();
    private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave = new HashMap<>();

    //List of Interactors
    private List<Interactor> interactors;

    //Has Level already began
    private boolean levelBegan = false;

	/** @return (float): camera scale factor, assume it is the same in x and y direction */
    public abstract float getCameraScaleFactor();

    /**
     * Setter for the viewCandidate so that we can then center the view on him.
     * @param a (Actor)
     */
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

        if(a instanceof Interactable){
            errorOccured = errorOccured || !enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
        }

        if (a instanceof Interactor) {
            errorOccured = errorOccured || !interactors.add((Interactor) a);
        }

        if(errorOccured && !forced) {
            System.out.println("Actor " + a + " cannot be completely added");
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

        if(a instanceof Interactable){
            errorOccured = errorOccured || !leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
        }

        if (a instanceof Interactor) {
            errorOccured = errorOccured || !interactors.remove((Interactor) a);
        }

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

        this.registeredActors.add(a);

    	return true;
    }

    /**
     * Unregister an actor : will be removed at next update
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): true if the actor is correctly unregistered
     */
    public final boolean unregisterActor(Actor a){

        this.unregisteredActors.add(a);

        return true;
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
        return window.getKeyboard();
    }

    /// Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        //d??finit les variables r??utilis??es dans les autre m??thodes
        this.window = window;
        this.fileSystem = fileSystem;

        //Initialisation de la liste d'acteurs
        this.actors = new LinkedList<>();
        this.interactors = new LinkedList<>();

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

       if (interactablesToEnter != null) {
           for (Map.Entry<Interactable, List<DiscreteCoordinates>> entry : interactablesToEnter.entrySet() ) {
               Interactable key = entry.getKey();
               List<DiscreteCoordinates> value = entry.getValue();
               areaBehavior.enter(key, value);
           }

       }

       if (interactablesToLeave != null) {
           for (Map.Entry<Interactable, List<DiscreteCoordinates>> entry : interactablesToLeave.entrySet() ) {
               Interactable key = entry.getKey();
               List<DiscreteCoordinates> value = entry.getValue();
               areaBehavior.leave(key,value);
           }
       }

        interactablesToEnter.clear();
        interactablesToLeave.clear();

        this.registeredActors.clear();
        this.unregisteredActors.clear();
    }

    @Override
    public void update(float deltaTime) {
        updateCamera();
        purgeRegistration();

        for (Actor actor : actors) {
            //On met ?? jour et on dessine les acteurs.
            actor.update(deltaTime);
            actor.draw(window);
        }
        for (Interactor interactor : interactors) {
            if (interactor.wantsCellInteraction()) {
                //demander au gestionnaire de la grille (AreaBehavior)
                //de mettre en place les interactions de contact
                areaBehavior.cellInteractionOf(interactor);
            }

            if (interactor.wantsViewInteraction()) {
                //demander au gestionnaire de la grille (AreaBehavior)
                //de mettre en place les interactions distantes
                areaBehavior.viewInteractionOf(interactor);
            }
        }
    }
    /**
     * Met ?? jour le centre de la vue.
     * S'il existe un viewCandidate, on centre la vue sur lui.
     */
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

    /**
     * Setter for the (AreaBehavior)
     * @param ab (AreaBehavior)
     */
    protected final void setBehavior(AreaBehavior ab){
        this.areaBehavior = ab;
    }

    /**
     * Teste si la grille associe??e a?? l???Aire permet a?? entity de quitter
     * les cellules de coordonne??es de coordinates
     * @param entity (Interactable) dont on veut tester les coordonn??es
     * @param coordinates (List<DiscreteCoordinates>) ?? tester
     * @return (boolean) entity autoris?? ?? quitter la cellule
     */
    public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {

        boolean canLeave = areaBehavior.canLeave(entity, coordinates);

        if (canLeave) {
            interactablesToLeave.put(entity, coordinates);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Teste si la grille associe??e a?? l???Aire permet a?? entity d'entrer
     * les cellules de coordonne??es de coordinates
     * @param entity (Interactable) dont on veut tester les coordonn??es
     * @param coordinates (List<DiscreteCoordinates>) ?? tester
     * @return (boolean) entity autoris?? ?? entrer dans la cellule
     */
    public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {

        boolean canEnter = areaBehavior.canEnter(entity, coordinates);

        if (canEnter) {
            interactablesToEnter.put(entity, coordinates);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Teste si la grille associe??e a?? l???Aire permet a?? entity de passer
     * la porte
     * @param entity (Interactable) dont on veut tester les coordonn??es
     * @param coordinates (List<DiscreteCoordinates>) ?? tester
     * @return (boolean) entity autoris?? ?? entrer dans la cellule
     */
    public final boolean passDoor(Interactable entity, List<DiscreteCoordinates> coordinates) {
        return areaBehavior.canPassDoor(entity, coordinates);
    }

    /**
     * Getter isLevelBegan
     * @return (boolean)
     */
    public boolean isLevelBegan() {
        return levelBegan;
    }

    /**
     * Setter isLevelBegan
     * @param begin levelBegan
     */
    public void setLevelBegan(boolean begin) {
        levelBegan = begin;
    }
}
