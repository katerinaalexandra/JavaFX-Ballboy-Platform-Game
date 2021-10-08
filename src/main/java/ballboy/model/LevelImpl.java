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

    public void cloudSpawner() {

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
        hero.setXPos(hero.getXPos()-5);
        System.out.println(hero.getXPos());
        return true;
    }

    @Override
    public boolean moveRight() {
        hero.setXPos(hero.getXPos()+5);
        System.out.println(hero.getXPos());
        return true;
    }

    public String getFloorColour() { return floorColour;}

}
