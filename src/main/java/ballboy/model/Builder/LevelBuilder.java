package ballboy.model.Builder;

import ballboy.model.Ballboy;
import ballboy.model.Entity;
import ballboy.model.Factory.EntityFactoryRegistry;
import ballboy.model.Finish;
import ballboy.model.LevelImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class LevelBuilder {
    public LevelImpl level;
    private EntityFactoryRegistry entityFactoryRegistry;

    public LevelBuilder() {
        this.entityFactoryRegistry = new EntityFactoryRegistry();
        this.level = new LevelImpl();
        this.level.entities = new ArrayList<>();
    }

    public LevelBuilder setLevelDimensions(JSONObject dimensionConfig) {
        this.level.levelDao.floorHeight = (double) dimensionConfig.get("floorHeight");
        this.level.levelDao.height = (double) dimensionConfig.get("height");
        this.level.levelDao.width = (double) dimensionConfig.get("width");
        this.level.floorColour = (String) dimensionConfig.get("floorColour");
        this.level.levelDao.gravity = (double) dimensionConfig.get("gravity");
        return this;
    }

    public LevelBuilder setHero(JSONObject heroConfig) {
        try {
            Entity hero = createEntity(heroConfig);
            this.level.hero = (Ballboy) hero;
            this.level.entities.add(hero);
        } catch (Exception e) {
            System.out.println("Something went wrong with the Hero creation!");
        }
        return this;
    }

    public LevelBuilder setEntities(JSONArray entityConfig) {
        try {
            for (Object object : entityConfig) {
                JSONObject o = (JSONObject) object;
                Entity entity = createEntity(o);
                this.level.entities.add(entity);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong with the Hero creation!");
        }
        return this;
    }

    public LevelBuilder setFinish(JSONObject finishConfig) {
        try {
            Entity finish = createEntity(finishConfig);
            this.level.finish = (Finish) finish;
            this.level.entities.add(finish);
        } catch (Exception e) {
            System.out.println("Something went wrong with the Finish creation!");
        }
        return this;
    }

    private Entity createEntity(JSONObject entityConfig) {

        return entityFactoryRegistry.addEntity(entityConfig);
    }


    public LevelImpl build() {return this.level;}


}
