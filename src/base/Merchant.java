package base;

import java.util.Random;

public class Merchant {

    String name;
    int x;
    int y;
    double money;
    int xp;

    public Merchant(String n){

        // Konstruktor Methode wird aufgerufen wenn man mit new ein objekt instanziert
        // --> Merchant blub = new Merchant([irgend ein string])  <-- neuer Merchant bekommt den namen

        this.name = n;   // <-- hier name setzen
        this.x = 15;    // nicht teil der Aufgaben stellung aber ich habe den Merchant in der Mitte des Spielfelds plaziert
        this.y = 15;
    }

    public void addMoney (int m){
        money += m;
    } // geld wird zum alten wert dazu addiert "a+=b" ist das selbe wie "a=a+b"

    public void move (){

        Random blub = new Random();//in Java ist Random eine classe von der wir erst ein objekt instanzieren müssen ich nenne es blub xD

        setX(getX() + (blub.nextInt(3)-1)); //
        setY(getY() + (blub.nextInt(3)-1));
    }

    public String toString(){ // returned eine Beschreibung bestehend aus den objekt attributen
        return "dies ist ein Merchant.   name : " + this.name + "   XP : " + this.getExp() + "   Money : " + this.getMoney();
    }

    public int getColor(){
        return 123456;
    } // eine randon color return methode weil das programm sonst nicht läuft xD

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double geldbetrag) {
        this.money = geldbetrag;
    }
    public int getExp() {
        return xp;
    }
    public void setExp(int exp) {
        this.xp = exp;
    }

}
