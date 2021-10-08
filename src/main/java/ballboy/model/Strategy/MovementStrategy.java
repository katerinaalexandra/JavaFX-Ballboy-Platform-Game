package ballboy.model.Strategy;

import ballboy.model.Entity;
import ballboy.model.GameEngine;
import ballboy.model.LevelDao;

public interface MovementStrategy {
    void move(Entity entity, LevelDao levelDao);
}
