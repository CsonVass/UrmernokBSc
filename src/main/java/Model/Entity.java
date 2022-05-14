package Model;

import java.util.ArrayList;

public abstract class Entity implements Stepable{

    //Tulajdons�gok
    protected ArrayList<Material> Materials;
    protected Location location;
    protected Game Game;

    //Konstruktorok

    /**
     * Konstruktor
     */
    public Entity(){
        Materials = new ArrayList<Material>();
    }

    /**
     * Konstruktor
     *
     * A param�terk�nt kapott Game-t elt�rolja
     *
     * @param g be�ll�tja az elt�rolt Game-nek
     */
    public Entity(Game g){
        Game = g;
        Materials = new ArrayList<Material>();
    }

    /**
     * Konstruktor
     *
     * A param�terk�nt kapott Model.Location-t elt�rolja
     *
     * @param l be�ll�tja az Entit�s Model.Location-j�nek
     */
    public Entity(Location l){
        setLocation(l);
        Materials = new ArrayList<Material>();
        l.AddEntity(this);
    }

    /**
     * Konstruktor
     *
     * A param�terk�nt kapott Model.Location-t �s Game-t elt�rolja
     *
     * @param l be�ll�tja az Entit�s Model.Location-j�nek
     * @param g be�ll�tja az elt�rolt Game-nek
     */
    public Entity(Location l, Game g){
        String attrib = l.toString()+", " + g.toString();
        setLocation(l);
        Game = g;
        Materials = new ArrayList<Material>();
        l.AddEntity(this);
    }

    //-------------F�ggv�nyek---------------

    /**
     * Az entit�s l Model.Location-re t�rt�n� �tmozg�s�t reprezent�l� f�ggv�ny
     *
     * Ha a szomsz�dok k�z�tt van, sikeres, egy�bk�nt sikertelen
     *
     * @param l erre a location-re k�v�n �tmozogni az entit�s
     */
    public void Move(Location l){
        if(location.GetNeighbours().contains(l)){
            location.RemoveEntity(this);
            l.AddEntity(this);
            setLocation(l);
        }
    }

    /**
     * Az entit�s kib�j�s�t-elb�j�s�t reprezent�l� f�ggv�ny
     */
    public void Hide(){
        location.Hide(this);
    }

    /**
     * Az entit�s fur�s�t reprezent�l� f�ggv�ny
     */
    public void Drill(){
        location.ReduceLayers();
    }

    /**
     * Az entit�s b�ny�sz�sz�s�t reprezent�l� f�ggv�ny
     */
    public void Mine(){}

    /**
     * Az entit�s hal�l�t reprezent�l� absztrakt f�ggv�ny
     */
    public abstract void Die();

    /**
     * Az entit�s l�p�s�t reprezent�l� f�ggv�ny
     */
    @Override
    public abstract void Step();

    /**
     * A napvihar hat�s�t az entit�son reprezent�l� f�ggv�ny
     */
    public void SunStorm(){
        Die();
    }

    /**
     * A robban�s hat�s�t az entit�son reprezent�l� f�ggv�ny
     */
    public void Explode(){}

    /**
     * Az entit�s teleport�l�s�t reprezent�l� f�ggv�ny
     */
    public void Teleport(){}

    //--------------------Setterek, getterek----------------------

    /**
     * A param�terk�nt kapott Model.Location-t elt�rolja (Setter)
     *
     * @param location be�ll�tja az Entit�s location-j�nek
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    public void setGame(Game game){
        Game = game;
    }

    /**
     * Getter
     *
     * @return az elt�rolt Model.Location-t adja vissza
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter
     *
     * @return az entit�s Inventory-j�t adja vissza
     */
    public ArrayList<Material> getInventory(){
        return Materials;
    }

    public void AddMaterial(Material m){}

}
