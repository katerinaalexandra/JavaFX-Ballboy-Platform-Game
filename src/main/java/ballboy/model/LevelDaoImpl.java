package ballboy.model;

public class LevelDaoImpl implements LevelDao {
    public double floorHeight;
    public double width;
    public double height;
    public double gravity;
    public double timestep=0.017;

    @Override
    public double getFloorHeight() {
        return floorHeight;
    }

    @Override
    public double getLevelHeight() {
        return height;
    }

    @Override
    public double getLevelWidth() {
        return width;
    }

    @Override
    public double getGravity() {
        return gravity;
    }

    @Override
    public double getTimestep() {return timestep;}

}
