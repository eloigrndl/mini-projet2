package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.window.Image;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{

    /// The behavior is an Image of size height x width
    private final Image behaviorMap;
    private final int width, height;
    /// We will convert the image into an array of cells private final Cell[][] cells;
    private final Cell[][] cells;

    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */

    public AreaBehavior(Window window, String fileName){
        this.behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
        this.width = behaviorMap.getWidth();
        this.height = behaviorMap.getHeight();
        this.cells = new Cell[width][height];
    }

    protected int[] getBehaviorMapSize() {

        int[] size = {cells.length, cells[0].length};

        return size;
    }

    protected Image getBehaviorMap(){
        return behaviorMap;
    }

    /**
     * Each game will have its own Cell extension. */
    public abstract class Cell {
        //...
    }

}
