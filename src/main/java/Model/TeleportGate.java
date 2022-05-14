package Model;

import View.AlienView;
import View.TeleportGateView;

import java.awt.*;
import java.util.Random;

//A j�t�kbeli teleportkaput, mint Model.Location-t reprezent�l� oszt�ly
public class TeleportGate extends Location {

    private static int stat_id = 0;

    //Tulajdons�gok
    private boolean Active;

    //T�rolt objektumok
    private TeleportGate Pair;

    private TeleportGateView teleportGateView;

    private int id;

    //Konstruktorok

    /**
     * Konstruktor
     */
    public TeleportGate(){
        super();
        id = stat_id;
        stat_id++;
        teleportGateView = new TeleportGateView(this);
    }

    /**
     * Konstruktor
     *
     * @param s be�ll�tja Model.Space-nek
     */
    public TeleportGate(Space s){
        super(s);
        s.RemoveLocation(this);
        id = stat_id;
        stat_id++;
        teleportGateView = new TeleportGateView(this);
        SunStorm();
    }
    
    //--------------F�ggv�nyek----------------------

    /**
     * A param�terk�nt kapott teleportkaput be�ll�tja a p�rj�nak
     *
     * @param t be�ll�tja p�rnak
     */
    public void SetPair(TeleportGate t){
        this.Pair = t;

    }

    /**
     * Az inventoryb�l a teleportkaput p�ly�ra lehelyez�s�t reprezent�l� f�ggv�ny
     *
     * @param l felveszi a Model.Location-�k k�z�, be�ll�tja szomsz�dait, �s akt�vv� teszi
     */
    public void Place(Location l){
        setCoordinate(new Point(l.getCoordinate().x + 10, l.getCoordinate().y));
        Space.AddLocation(this);

        l.AddNeighbour(this);
        this.AddNeighbour(l);

        SetActive();
    }

    /**
     * Setter
     * A teleportkapu aktiv�l�sa
     */
    public void SetActive(){
        Active = true;
    }

    /**
     * Napf�ny hat�s�ra megkerg�l
     */
    public void SunStorm(){
        Space.AddStepperLocation(this);
        for(Entity e: EntitiesOnSurface){
            e.SunStorm();
        }
    }

    @Override
    public void RemoveNeighbour(Location l){
        Random rand = new Random();
        if(Neighbours.size() < 1) return;
        int randIx = rand.nextInt(Neighbours.get(0).GetNeighbours().size());
        if(randIx >= 0){
            Space.RemoveLocation(this);
            this.Place(Neighbours.get(0).GetNeighbours().get(randIx));
            Neighbours.remove(l);
        }

    }

    /**
     * A teleport�lni v�gy� e Entity telport�l�si k�s�rlete a teleportkapur�l annak p�rj�hoz
     *
     * Ha nincs p�rja, vagy nem akt�v a p�r, akkor nem t�rt�nik semmi
     * Egy�bk�nt megt�rt�nik az Entity �thelyez�se a p�rra
     *
     * @param e az entit�s, amely teleport�lni szeretne
     */
    public void Teleport(Entity e){
        if(Pair == null){
            return;
        }
        if(Pair.IsActive()) {
            this.RemoveEntity(e);
            Pair.AddEntity(e);
            e.setLocation(Pair);
        }

    }

    /**
     * Megkerg�l�shez haszn�lt Step f�ggv�ny
     */
    public void Step(){
        Random rand = new Random();
        if(Neighbours.size() < 1) return;
        int randIx = rand.nextInt(Neighbours.get(0).GetNeighbours().size());
        if(randIx >= 0){
            Space.RemoveLocation(this);
            this.Place(Neighbours.get(0).GetNeighbours().get(randIx));
            RemoveNeighbour(Neighbours.get(0));
        }

    }

    //-------------Getterek & Setterek------------\\

    /**
     * Getter
     *
     * @return Active tulajdons�g �rt�ke
     */
    public boolean IsActive(){
        return Active;
    }

    public TeleportGate GetPair(){
        return Pair;
    }

    public String toString(){
        return "TeleportGate" + id;
    }

    @Override
    public TeleportGateView getView(){
        return teleportGateView;
    }
}
