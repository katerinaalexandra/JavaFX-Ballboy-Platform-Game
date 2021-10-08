package ballboy.model.Factory;

import ballboy.model.*;
import ballboy.model.Strategy.*;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EntityFactoryRegistry {
    private Map<String, EntityFactory> factories;

    public EntityFactoryRegistry() {
        factories=new HashMap<>();

        factories.put("cloud", new EntityFactory() {public Entity createEntity(JSONObject entityConfig) { return new Cloud(entityConfig, setStrategy((String) entityConfig.get("movement")));}});
        factories.put("ballboy", new EntityFactory() {public Entity createEntity(JSONObject entityConfig) { return new Ballboy(entityConfig, setStrategy((String) entityConfig.get("movement")));}});
        factories.put("enemy", new EntityFactory() {public Entity createEntity(JSONObject entityConfig) { return new Enemy(entityConfig, setStrategy((String) entityConfig.get("movement")));}});
        factories.put("wall", new EntityFactory() {public Entity createEntity(JSONObject entityConfig) { return new Wall(entityConfig, setStrategy((String) entityConfig.get("movement")));}});
        factories.put("finish", new EntityFactory() {public Entity createEntity(JSONObject entityConfig) { return new Finish(entityConfig, setStrategy((String) entityConfig.get("movement")));}});
    }

    private MovementStrategy setStrategy(String entityMovement) {
        MovementStrategy entityStrategy;
        switch (entityMovement) {
            case "ballboy":
                entityStrategy = new BallboyMovementStrategy();
                break;
            case "cloud":
                entityStrategy = new CloudMovementStrategy();
                break;
            case "oscillating":
                entityStrategy = new OscillatingMovementStrategy();
                break;
            case "horizontal":
                entityStrategy = new HorizontalMovementStrategy();
                break;
            case "default":
                entityStrategy = new NoMovementStrategy();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + entityMovement);
        }
        return entityStrategy;
    }

    private void registerFactory(String identifier, EntityFactory factory) {
        factories.put(identifier, factory);
    }

    public Entity addEntity(JSONObject config) {
        String identifier = (String) config.get("type");
        MovementStrategy entity = setStrategy((String) config.get("movement"));
        EntityFactory factory = factories.get(identifier);
        return factory.createEntity(config);
    }

}
