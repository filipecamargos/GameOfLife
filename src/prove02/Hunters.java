package prove02;

import java.awt.*;
import java.util.Random;

public class Hunters extends Creature implements Movable, Aggressor, Aware {

    int powerTranning;
    boolean huntingSkills;
    Random _rand;

    public Hunters(){
        _health = 10;
        _rand = new Random();
        huntingSkills = false;
        powerTranning = 3;
    }

    /**
     * Determine the Visual of the Hunter
     */
    public Shape getShape() {
        return Shape.Circle;
    }

    public Color getColor() {
        return new Color(165, 33, 33, 255);
    }

    public Boolean isAlive() {
        return _health > 0;
    }

    /**
     * Zombie will attack only Zombies and get stronger Hunting Experiences and Eats Animals
     */
    public void attack(Creature target) {
        // Zombie attacks all except plants.
        if (target instanceof Zombie) {
            target.takeDamage(powerTranning);
            huntingSkills = true;
            fightEvil();
        }
        if (target instanceof Animal){
            target.takeDamage(powerTranning);
            _health =+ 3;
        }


    }

    public void fightEvil() {
        if (huntingSkills == true){
            powerTranning =+3;
        }
        huntingSkills = false;
    }

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
     * Hunter will sense its Neighbors and move towards them if they are Zombies
     */
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {

        if (above instanceof Zombie) {
            _location.y ++;
        } else if (below instanceof Zombie) {
            _location.y --;
        } else if (left instanceof Zombie) {
            _location.x --;
        } else if (right instanceof Zombie) {
            _location.x ++;
        }
        else {
            move();
        }

    }
}
