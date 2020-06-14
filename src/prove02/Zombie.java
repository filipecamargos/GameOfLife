package prove02;

import sun.java2d.d3d.D3DDrawImage;

import java.awt.*;

public class Zombie extends Creature implements Movable, Aggressor{

    public Zombie(){
        _health = 3;
    }
    /**
     * Determine the Visual Caractheristics of the Zombie
     */
    public Shape getShape() {
        return Shape.Square;
    }
    public Color getColor() {
        return new Color(42, 33, 165);
    }
    public Boolean isAlive() {
        return _health > 0;
    }

    /**
     * Zombie will attack any creature except plants
     */
    public void attack(Creature target) {
        // Zombie attacks all except plants.
        if (target instanceof Animal || target instanceof Wolf) {
            target.takeDamage(10);
        }
    }

    /**
     * Move the Zombie from left to right.
     */
    public void move() {
        _location.x ++;
    }
}
