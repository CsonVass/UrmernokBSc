package Model;

import View.ObjectView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

//Egy absztrakt oszt�ly, mely egy j�t�kbeli helysz�nt reprezent�l
public abstract class Location implements Stepable {

    //Jellemz� tulajdons�gok
    private Point Coordinate;
    protected int NumberOfLayers = 106;
    protected boolean NearSun = false;

    //T�rolt objektumok
    protected ArrayList<Location> Neighbours;
    protected ArrayList<Entity> EntitiesOnSurface;
    protected Space Space;

    //Konstruktorok

    /**
     * Param�ter n�lk�li konstruktor, be�ll�tja a tulajdons�gokat
     */
    public Location(){
        Neighbours = new ArrayList<Location>();
        EntitiesOnSurface = new ArrayList<Entity>();
        Space = new Space();
        Random r = new Random();
        Coordinate = new Point(r.nextInt(200), r.nextInt(200));
    }

    /**
     * Konstruktor
     *
     * @param s be�ll�tja a Model.Space-nek
     */
    public Location(Space s){
        Neighbours = new ArrayList<Location>();
        EntitiesOnSurface = new ArrayList<Entity>();
        Space = s;
        Random r = new Random();
        Coordinate = new Point(r.nextInt(500), r.nextInt(500));
    }
    
    //--------------F�ggv�nyek----------------------

    /**
     * A param�terk�nt kapott entit�st hozz�adja az EntitiesOnSurface list�hoz
     *
     * @param e hozz�adja az EntitiesOnSurface list�hoz
     */
    public void AddEntity(Entity e){
        EntitiesOnSurface.add(e);

        ArrayList<Material> Items = new ArrayList<Material>();
        for(int i = 0; i < EntitiesOnSurface.size(); i++){
            for(int j = 0; j < EntitiesOnSurface.get(i).getInventory().size(); j++){
                Items.add(EntitiesOnSurface.get(i).getInventory().get(j));
            }
        }
        Space.getGame().CheckWin(Items);
    }

    /**
     * A param�terk�nt kapott Locationt hozz�adja a Neighbours list�hoz
     *
     * @param l hozz�adja a Neighbours list�hoz
     */
    public void AddNeighbour(Location l){
        Neighbours.add(l);
    }

    /**
     * A Model.Location-t el�r� napvihar hat�s�t modellez� f�ggv�ny
     *
     * Minden rajta lev� entit�s Sunstorm f�ggv�ny�t megh�vja
     */
    public void SunStorm(){
        int n = EntitiesOnSurface.size();
        for(int i = 0; i < n; i++){
            EntitiesOnSurface.get(0).SunStorm();
        }
    }

    /**
     * Elt�vol�tja a param�terk�nt kapott entit�st az EntitiesOnSurface list�b�l
     *
     * @param e elt�vol�tja az EntitiesOnSurface list�b�l
     */
    public void RemoveEntity(Entity e){
        EntitiesOnSurface.remove(e);
    }

    /**
     * Elt�vol�tja a param�terk�nt kapott Model.Location-t a Neighbours list�b�l
     *
     * @param l elt�vol�tja a Neighbours list�b�l
     */
    public void RemoveNeighbour(Location l){
        Neighbours.remove(l);
    }

    /**
     * Egy Model.Location-�n t�rt�n� elb�j�si k�s�rletet reprezent�l� f�ggv�ny
     * Alapvet�en sikertelen, csak aszteroid�n m�s, �gy ott fel�ldefini�l�dik
     *
     * @param e az elb�jni k�v�nt entit�s
     */
    public void Hide(Entity e){
    }


    /**
     * Egy Model.Location-�n t�rt�n� f�r�si k�s�rletet reprezent�l� f�ggv�ny
     * Alapvet�en sikertelen, csak aszteroid�n m�s, �gy ott fel�ldefini�l�dik
     */
    public void ReduceLayers(){
    }

    /**
     * Egy Model.Location felrobban�s�t reprezent�l� f�ggv�ny
     */
    public void Explode(){
    }

    /**
     * Egy Model.Location-�n t�rt�n� b�ny�sz�si k�s�rletet reprezent�l� f�ggv�ny
     * Alapvet�en sikertelen, csak aszteroid�n m�s, �gy ott fel�ldefini�l�dik
     *
     * @return default null
     */
    public Material MineMaterialInside(){
        return null;
    }

    /**
     * Egy Model.Location-�n t�rt�n� nyersanyag-lehelyez�si k�s�rletet reprezent�l� f�ggv�ny
     * Alapvet�en sikertelen, csak aszteroid�n m�s, �gy ott fel�ldefini�l�dik
     *
     * @param m a lehelyezni k�v�nt nyersanyag
     * @return default false
     */
    public boolean Place(Material m){
        return false;
    }

    /**
     * Egy Model.Location-�n t�rt�n� teleport�l�si k�s�rletet reprezent�l� f�ggv�ny
     * Alapvet�en sikertelen, csak teleportkapun m�s, �gy ott fel�ldefini�l�dik
     *
     * @param e az entit�s, amely teleport�lni szeretne
     */
    public void Teleport(Entity e){}

    /**
     * L�p�s (TeleportGate-n�l van szerepe)
     */
    public void Step(){}

    public void Die(){
        for(Entity e : EntitiesOnSurface){
            e.Die();
        }
        for(Location l : Neighbours){
            l.RemoveNeighbour(this);
        }
        Space.RemoveLocation(this);
    }

    //-----------Setterek & Getterek ------------\\

    /**
     * Getter, a NearSun tulajdons�g �rt�k�t adja vissza
     *
     * @return a NearSun tulajdons�g �rt�ke
     */
    public boolean isNearSun() {
        return NearSun;
    }

    /**
     * Setter, be�ll�tja a NearSun tulajdons�got a param�terk�nt kapott �rt�kre
     *
     * @param nearSun erre �ll�tja a NearSun-t
     */
    public void setNearSun(boolean nearSun) {
        NearSun = nearSun;
    }

    /**
     * Getter, egy Model.Location szomsz�dait visszaad� f�ggv�ny
     *
     * @return adott Model.Location szomsz�dait
     */
    public ArrayList<Location> GetNeighbours(){
        return Neighbours;
    }

    /**
     * Setter
     * Egy Model.Location sziklar�tegeinek sz�m�t a param�terk�nt kapott �rt�kre �ll�t� f�ggv�ny
     *
     * @param numberOfLayers k�regr�teg sz�m�t erre �ll�tja
     */
    public void setNumberOfLayers(int numberOfLayers) {
        NumberOfLayers = numberOfLayers;
    }

    public void setCoordinate(Point c){
        Coordinate = c;
    }

    /**
     * Egy Model.Location belsej�ben lev� nyersanyagot elt�vol�t� f�ggv�ny
     * Alapvet�en �res, de a lesz�rmazottak fel�ldefini�lhatj�k
     */
    public void RemoveMaterial(){
    }

    /**
     * Setter
     * Egy Model.Location �ltal t�rolt Model.Space tulajdons�got a param�terk�nt kapott �rt�kre �ll�t� f�ggv�ny
     *
     * @param s erre �ll�tja a Model.Space-t
     */
    public void SetSpace(Space s){
        Space = s;
    }

    /**
     * Getter
     *
     * @return a Model.Location koordin�t�ja
     */
    public Point getCoordinate() {
        return Coordinate;
    }

    /**
     * Getter
     *
     * @return a k�regr�teg sz�ma
     */
    public int getNumberOfLayers() {
        return NumberOfLayers;
    }


    /**
     * Getter
     *
     * @return adott Model.Location-�n tartozkod� entit�sok
     */
    public ArrayList<Entity> getEntitiesOnSurface() {
        return EntitiesOnSurface;
    }

    /**
     * Getter
     *
     * @return visszaadja a Model.Space-t
     */
    public Space getSpace() {
        return Space;
    }

    public Material GetMaterial(){return null;}

    public abstract ObjectView getView();

}
