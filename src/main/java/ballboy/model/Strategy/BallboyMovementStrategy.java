package ballboy.model.Strategy;

import ballboy.model.*;

import java.awt.geom.Point2D;

public class BallboyMovementStrategy implements MovementStrategy {
    @Override
    public void move(Entity entity, LevelDao levelDao) {

        MovingEntity movableEntity = (MovingEntity) entity;

        double timestep = levelDao.getTimestep();
        double floorHeight = levelDao.getFloorHeight();
        double gravity = levelDao.getGravity() / 1000; // gravity is in m/s and we are working with ticks

        // Casting relies on correct 'movement' values in config
        double dy = movableEntity.getYVel();
        double yPos = movableEntity.getYPos();


        double difference=0.0;
        if (yPos + dy > floorHeight) {
            difference = yPos + dy - floorHeight;
        }


        movableEntity.setYPos(yPos + dy - difference);


        if (movableEntity.getYPos() == floorHeight) {
            double temp_dy = (-dy);
            dy = temp_dy;
        }
        movableEntity.setYVel(dy+=timestep);


    }

    @Override
    public void collide(Entity thisEntity, Entity collisionEntity, LevelDao levelDao) {
        MovingEntity movableEntity = ((MovingEntity) thisEntity);

        if (collisionEntity.getLayer().equals(Entity.Layer.FINISH)) {
            System.out.println("Game won!");
            System.exit(0);
        }

        if (collisionEntity.getLayer().equals(Entity.Layer.MIDGROUND)) {
            //System.out.println("Collision with stationary entity. Expect a bounce");

            double upperBoundX = collisionEntity.getXPos() + collisionEntity.getWidth();
            double lowerBoundX = collisionEntity.getXPos() - thisEntity.getWidth();

            double upperBoundY = collisionEntity.getYPos() - thisEntity.getHeight();
            double lowerBoundY = collisionEntity.getYPos() + collisionEntity.getHeight();


            collideWithStationary(upperBoundX, upperBoundY, lowerBoundX, lowerBoundY, movableEntity);

        }

        if (collisionEntity.getLayer().equals(Entity.Layer.FOREGROUND)) {
            //System.out.println("Collision with movable entity. Expect ballboy to return to start xPos");
            double startingX = movableEntity.getStartingX();
            double startingY = movableEntity.getStartingY();

            movableEntity.setXPos(startingX);
            movableEntity.setYPos(startingY);

            movableEntity.setXVel(0);
            movableEntity.setYVel(0);
        }
    }

    private void collideWithStationary(double upperBoundX, double upperBoundY, double lowerBoundX, double lowerBoundY, MovingEntity hero) {

        Point2D heroCoordinates = new Point2D.Double(hero.getXPos(), hero.getYPos());

        if (hero.getPrevYPos() < upperBoundY) {
            //System.out.println("top side hit!");
            hero.setYVel(-1*hero.getYVel());
        } else if (hero.getYPos() < lowerBoundY && hero.getYPos() > upperBoundY) {
            System.out.println("side hit!");
            //hero.setYVel(-1*hero.getYVel());
        } else if (hero.getPrevYPos() > lowerBoundY) {
            //System.out.println("bottom hit!");
            hero.setYVel(-1*hero.getYVel());
        }







    }
}
