package prove02;

import java.awt.*;
import java.util.Random;

public class Wolf extends Creature implements Movable, Aware, Aggressor, Spawner{

    Random _rand;
    boolean _spawnerCounting;

    /**
     * Creates an Wolf with 3 health point.
     */
    public Wolf() {
        _rand = new Random();
        _health = 3;
        _spawnerCounting = false;
    }

    public Wolf(Point location) {
        _rand = new Random();
        _health = 3;
        _spawnerCounting = false;
        _location = new Point();
        _location.y = location.y;
        _location.x = location.x - 1;
    }

    /**
     * Move the Wolf in a random direction.
     */
    public void move() {

        // Choose a random direction each time move() is called.
        switch(_rand.nextInt(4)) {
            case 0:
                _location.x++;
                break;
            case 1:
                _location.x--;
                break;
            case 2:
                _location.y++;
                break;
            case 3:
                _location.y--;
                break;
            default:
                break;
        }
    }

    /**
     * Wolf will sense its Neighbors and move towards them if they are animal
     */
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {

        /**
         * Wolf will check the coordinates if there is any creature on its x and
         * y coordinates with one pixes move
         */
        if (above instanceof Animal) {
            _location.y ++;
        } else if (below instanceof Animal) {
            _location.y --;
        } else if (left instanceof Animal) {
            _location.x --;
        } else if (right instanceof Animal) {
            _location.x ++;
        }
        else {
            move();
        }

    }

    /**
     * Wolf class should attack any Animal instances they land
     */
    public void attack(Creature target) {

        if(target instanceof Animal) {
            target.takeDamage(5);
            _spawnerCounting = true;
        }
    }

    /**
     * Represent the style of the wolf
     */
    public Boolean isAlive() {
        return _health > 0;
    }

    public Shape getShape() {
        return Shape.Square;
    }

    public Color getColor() {
        return new Color(43, 45, 47);
    }

    /**
     * Every time a wolf eats another animal,
     * it should gain the ability to spawn a new wolf on its next turn
     */
    public Creature spawnNewCreature() {
        if (_spawnerCounting) {
            Wolf newBornWolf = new Wolf(this._location);
            _spawnerCounting = false;
            return newBornWolf;
        } else {
            return null;
        }
    }

}
