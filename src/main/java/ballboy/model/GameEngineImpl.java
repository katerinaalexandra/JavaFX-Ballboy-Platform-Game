package ballboy.model;

import ballboy.ConfigParser;
import ballboy.model.Builder.LevelBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GameEngineImpl implements GameEngine {
    private JSONObject levelConfig;

    public LevelImpl currentLevel;
    public LevelBuilder currentLevelBuilder;
    public int tick;

    // GameEngineImpl Constructor
    public GameEngineImpl(String config) {
        this.levelConfig= new ConfigParser(config).getLevelConfig();
        // Build level using Builder pattern
        startLevel();
        System.out.println(currentLevel.getEntities());
    }

    @Override
    public Level getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void startLevel() {
        this.currentLevelBuilder = new LevelBuilder()
                                    .setLevelDimensions((JSONObject) levelConfig.get("LevelDimensions"))
                                    .setHero((JSONObject) levelConfig.get("Hero"))
                                    .setEntities((JSONArray) levelConfig.get("Entities"))
                                    .setFinish((JSONObject) levelConfig.get("Finish"));

        this.currentLevel = currentLevelBuilder.build();
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

    @Override
    public void tick() {
        // LevelImpl will handle entity movement strategy as it has DAO
        getCurrentLevel().tick();

        // GameEngine will handle cloud spawning
        tick++;

        // Refactor this - it doesn't really follow Builder pattern
        if (tick % 2000 == 0) {
            currentLevelBuilder.spawnCloud((JSONObject) levelConfig.get("Clouds"));
            System.out.println(currentLevel.getEntities());
        }
    }
}
