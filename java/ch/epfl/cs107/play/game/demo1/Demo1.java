package ch.epfl.cs107.play.game.demo1;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.actor.*;

import java.awt.*;

public class Demo1 implements Game {

    private Actor actor1;
    private Window window;
    private FileSystem fileSystem;

    @Override
    public String getTitle() {
        return "Demo1";
    };

    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        //définit les variables réutilisées dans les autre méthodes
        this.window = window;
        this.fileSystem = fileSystem;

        //changement d'échelle
        //valeur de "scaled" représente la réduction (10 = monde 10x plus petit)
        Transform viewTransform = Transform.I.scaled(10).translated(Vector.ZERO);
        window.setRelativeTransform(viewTransform);

        float radius = 0.2f;
        actor1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius), null, Color.RED, 0.005f));

        return true;
    }

    @Override
    public void end() {

    }

    @Override
    public void update(float deltaTime) {

        actor1.draw(window);

    }
}
