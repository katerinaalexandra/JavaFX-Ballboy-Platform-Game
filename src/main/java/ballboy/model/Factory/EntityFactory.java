package ballboy.model.Factory;

import ballboy.model.Entity;
import org.json.simple.JSONObject;

public interface EntityFactory {
    Entity createEntity(JSONObject entityConfig);
}
