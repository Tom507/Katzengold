package Katzengold;

public class MapObject {
    public int id;
    public String directory;
    public boolean collidable;

    MapObject(String directory, boolean collidable){
        this.directory = directory;
        this.collidable = collidable;
    }

    MapObject(int objectId, String directory, boolean collidable){
        this.id = objectId;
        this.directory = directory;
        this.collidable = collidable;
    }
}
