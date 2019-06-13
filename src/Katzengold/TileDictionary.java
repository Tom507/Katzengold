package Katzengold;

import java.util.ArrayList;

public class TileDictionary {

    public TileDictionary(){
        tileSetup();
    }

    public ArrayList<MapObject> tileList = new ArrayList();

    public MapObject getById(int id){
        return tileList.stream().filter(obj -> obj.id == id).findFirst().orElse(null);
    }

    public MapObject getByDirectory(String dir){
        return tileList.stream().filter(obj -> obj.directory.equals(dir)).findFirst().orElse(null);
    }

    private void tileSetup(){
        //####################> Tiles ##############################################
        tileList.add(new MapObject(0, "resources/Empty.png", false));
        tileList.add(new MapObject(1, "resources/cat/Down.png", false));
        tileList.add(new MapObject(5, "resources/Tree.png", true));
        tileList.add(new MapObject(9, "resources/Water.png", false));

        tileList.add(new MapObject(10, "resources/river/RiverCornerLeft.png", true));
        tileList.add(new MapObject(11, "resources/river/RiverCornerUp.png", true));
        tileList.add(new MapObject(12, "resources/river/RiverCornerRight.png", true));
        tileList.add(new MapObject(13, "resources/river/RiverCornerDown.png", true));

        tileList.add(new MapObject(14, "resources/river/RiverStraightLeft.png", true));
        tileList.add(new MapObject(15, "resources/river/RiverStraightUp.png", true));
        tileList.add(new MapObject(16, "resources/river/RiverStraightRight.png", true));
        tileList.add(new MapObject(17, "resources/river/RiverStraightDown.png", true));
    }
}
