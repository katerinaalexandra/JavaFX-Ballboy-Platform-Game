package ballboy.model;

import java.util.List;

public class LevelImpl implements Level {
    // Level dimension attributes
    public LevelDaoImpl levelDao;
    public Ballboy hero;
    public Finish finish;
    public String floorColour;

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
        for (Entity entity: this.getEntities()) {
            entity.tick(this.levelDao);
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
        return false;
    }

    @Override
    public boolean dropHeight() {
        return false;
    }

    @Override
    public boolean moveLeft() {
        return false;
    }

    @Override
    public boolean moveRight() {
        return false;
    }

    public String getFloorColour() { return floorColour;}

}
