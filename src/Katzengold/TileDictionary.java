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
        tileList.add(new MapObject(3, "resources/Coin.png", true));
        tileList.add(new MapObject(4, "resources/Key.png", true));
        tileList.add(new MapObject(5, "resources/Tree.png", true));
        tileList.add(new MapObject(6, "resources/Fuchs.png", true));
        tileList.add(new MapObject(7, "resources/Unicorn.png", true));
        tileList.add(new MapObject(9, "resources/Water.png", true));

        tileList.add(new MapObject(10, "resources/water/RiverCornerLeft.png", true));
        tileList.add(new MapObject(11, "resources/water/RiverCornerUp.png", true));
        tileList.add(new MapObject(12, "resources/water/RiverCornerRight.png", true));
        tileList.add(new MapObject(13, "resources/water/RiverCornerDown.png", true));

        tileList.add(new MapObject(14, "resources/water/RiverStraightLeft.png", true));
        tileList.add(new MapObject(15, "resources/water/RiverStraightUp.png", true));
        tileList.add(new MapObject(16, "resources/water/RiverStraightRight.png", true));
        tileList.add(new MapObject(17, "resources/water/RiverStraightDown.png", true));

        tileList.add(new MapObject(18, "resources/water/RiverBridgeLeft.png", false));
        tileList.add(new MapObject(19, "resources/water/RiverBridgeUp.png", false));
        tileList.add(new MapObject(20, "resources/water/RiverBridgeRight.png", false));
        tileList.add(new MapObject(21, "resources/water/RiverBridgeDown.png", false));

        tileList.add(new MapObject(22, "resources/water/RiverInflowLeft.png", true));
        tileList.add(new MapObject(23, "resources/water/RiverInflowUp.png", true));
        tileList.add(new MapObject(24, "resources/water/RiverInflowRight.png", true));
        tileList.add(new MapObject(25, "resources/water/RiverInflowDown.png", true));

        tileList.add(new MapObject(26, "resources/water/SeaCornerLeft.png", true));
        tileList.add(new MapObject(27, "resources/water/SeaCornerUp.png", true));
        tileList.add(new MapObject(28, "resources/water/SeaCornerRight.png", true));
        tileList.add(new MapObject(29, "resources/water/SeaCornerDown.png", true));

        tileList.add(new MapObject(30, "resources/water/SeaLeft.png", true));
        tileList.add(new MapObject(31, "resources/water/SeaUp.png", true));
        tileList.add(new MapObject(32, "resources/water/SeaRight.png", true));
        tileList.add(new MapObject(33, "resources/water/SeaDown.png", true));

        tileList.add(new MapObject(34, "resources/water/SeaStraightLeft.png", true));
        tileList.add(new MapObject(35, "resources/water/SeaStraightUp.png", true));
        tileList.add(new MapObject(36, "resources/water/SeaStraightRight.png", true));
        tileList.add(new MapObject(37, "resources/water/SeaStraightDown.png", true));

        tileList.add(new MapObject(38, "resources/water/SeaInsideCornerLeft.png", true));
        tileList.add(new MapObject(39, "resources/water/SeaInsideCornerUp.png", true));
        tileList.add(new MapObject(40, "resources/water/SeaInsideCornerRight.png", true));
        tileList.add(new MapObject(41, "resources/water/SeaInsideCornerDown.png", true));
    }
}
