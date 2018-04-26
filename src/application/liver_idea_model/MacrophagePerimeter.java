package application.liver_idea_model;

import engine.Actor;
import engine.Circle2D;
import engine.PulseEntity;
import javafx.scene.paint.Color;

import java.util.HashSet;

public class MacrophagePerimeter extends Circle2D implements PulseEntity {
    //private static final Color _color = new Color(255 / 255.0, 173 / 255.0, 31 / 255.0, 0);
    private static Color _color = new Color(0 / 255.0, 167 / 255.0, 61 / 255.0, 0.2);
    private int _enemyCounter = 0;
    private int _enemyThreshold = 10;

    public MacrophagePerimeter(double x, double y, double radiusX, double radiusY, double depth) {
        super(x, y, radiusX, radiusY, depth);
        setColor(_color);
    }

    @Override
    public void setWidthHeight(double radiusX, double radiusY) {
        super.setWidthHeight(radiusX, radiusY);
    }

    @Override
    public void onActorOverlapped(Actor self, HashSet<Actor> collidedWith) {
        // Check for viruses and infected cells
        _enemyCounter = 0;
        for(Actor actor: collidedWith) {
            if (actor instanceof Virus) {
                _enemyCounter ++;
            }
            else if (actor instanceof LiverCell) {
                if (((LiverCell) actor).infected()) {
                    _enemyCounter ++;
                }
            }
        }
    }

    public boolean isOverwhelmed(){
        if (_enemyCounter > _enemyThreshold) {
            return true;
        }
        return false;
    }

    public void resetPerimeter() {
        _enemyCounter = 0;
    }

    /**
     * Called each time the simulation.engine updates.
     *
     * @param deltaSeconds Change in seconds since the last update.
     *                     If the simulation.engine is running at 60 frames per second,
     *                     this value will be roughly equal to (1/60).
     */
    @Override
    public void pulse(double deltaSeconds) {
        _enemyCounter = 0;
    }
}
