package ch.epfl.cs107.play.game.demo1;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.actor.*;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;

import java.awt.Color;

public class Demo1 implements Game {

    private Actor actor1;
    private Actor actor2;
    private Actor actor3;
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

        //Définition des variables
        this.window = window;
        this.fileSystem = fileSystem;

        //Ajout des acteurs
        float radius = 0.2f;
        actor1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius), null, Color.RED, 0.005f));
        actor2 = new MovingRock(new Vector(0.3f,0.1f), "this is a rock");
        actor3 = new GraphicsEntity(new Vector(0.0f,0.0f),new TextGraphics("BOUM!!!", 0.05f, Color.RED, Color.RED, 0.005f, true, false, new Vector(0.0f,0.0f)));

        //Echelle de la vue
        Transform viewTransform = Transform.I.scaled(2).translated(Vector.ZERO);
        window.setRelativeTransform(viewTransform);

        return true;
    }

    @Override
    public void end() {

    }

    @Override
    public void update(float deltaTime) {

        //On dessine les acteurs
        actor1.draw(window);
        actor2.draw(window);

        //On update MovingRock si on appuie sur downArrow
        Keyboard keyboard = window.getKeyboard();
        Button downArrow = keyboard.get(Keyboard.DOWN);
        if (downArrow.isDown()) {
            actor2.update(1);
        }

        //On dessine le texte si MovingRock est dans son rayon
        if (Math.abs(actor2.getPosition().x) <= 0.2 && Math.abs(actor2.getPosition().y) <= 0.2) {
            actor3.draw(window);
        }
    }
}
