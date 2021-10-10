package ballboy.model;

import org.json.simple.JSONObject;

import java.util.List;

public class LevelImpl implements Level {
    // Level dimension attributes
    public LevelDaoImpl levelDao;
    public Ballboy hero;
    public Finish finish;
    public String floorColour;
    public int tick;
    public JSONObject cloudConfig;
    public double xAcceleration;

    // Entity attributes
    public List<Entity> entities;

    public LevelImpl() {
        this.levelDao = new LevelDaoImpl();
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public double getLevelHeight() {
        return levelDao.getLevelHeight();
    }

    @Override
    public double getLevelWidth() {
        return levelDao.getLevelWidth();
    }

    @Override
    public void tick() {
        System.out.println(getHeroX());
        for (Entity entity: this.getEntities()) {
            entity.tick(this.levelDao);

            if (checkCollision(hero, entity)) {

                hero.movementStrategy.collide(hero, entity, levelDao);
            }
        }
    }

    @Override
    public double getFloorHeight() {
        return levelDao.getFloorHeight();
    }

    @Override
    public double getHeroX() {
        return hero.getXPos();
    }

    @Override
    public double getHeroY() {
        return hero.getYPos();
    }

    @Override
    public boolean boostHeight() {
        double currentYVel = hero.getYVel();

        if (currentYVel <= 0) {
            hero.setYVel(currentYVel-(levelDao.getTimestep()*2));
        } else {
            System.out.println("Boost height when bouncing upwards!");
        }
        return true;
    }

    @Override
    public boolean dropHeight() {
        double currentYVel = hero.getYVel();
        if (currentYVel >= 0) {
            hero.setYVel(currentYVel-(levelDao.getTimestep()*2));
        }

        return true;
    }

    @Override
    public boolean moveLeft() {
        double xVel = hero.getXVel();
        xVel += levelDao.getTimestep();

        hero.setXPos(hero.getXPos() - (xVel + 0.5*xAcceleration));
        hero.setXVel(xVel);

        return true;
    }

    @Override
    public boolean moveRight() {
        double xVel = hero.getXVel();
        xVel += levelDao.getTimestep();

        hero.setXPos(hero.getXPos() + (xVel + 0.5*xAcceleration));
        hero.setXVel(xVel);

        return true;
    }

    public boolean resetXVel() {
        hero.setXVel(0);
        return true;
    }

    public String getFloorColour() { return floorColour;}

    public boolean checkCollision(Ballboy hero, Entity entity) {
        if (hero.equals(entity)) {
            return false;
        }

        if (entity.getLayer().equals(Entity.Layer.BACKGROUND)) {
            return false;
        }


        return (hero.getXPos() < (entity.getXPos() + (entity.getWidth())) &&
                ((hero.getXPos() + hero.getWidth()) > entity.getXPos()) &&
                hero.getYPos() < (entity.getYPos() + (entity.getHeight())) &&
                ((hero.getYPos() + hero.getHeight()) > entity.getYPos()));


    }

}
