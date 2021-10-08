package ballboy.model;

public interface LevelDao {
    // Data Access Object pattern - separate business from persistence layer
    // Movement strategies need to read global info about level
    // Instead of passing through large level object, we pass through an interface that can call our level object.
    double getFloorHeight();

    double getLevelHeight();

    double getLevelWidth();

    double getGravity();

    double getTimestep();

}
