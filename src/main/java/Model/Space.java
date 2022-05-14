package Model;

import View.ObjectView;

import java.util.ArrayList;
import java.util.Random;

//A j�t�kteret, azaz az �rt reprezent�l� oszt�ly
public class Space {

    //T�rolt objektumok
    private Game Game;
    private ArrayList<Location> Locations;
    private ArrayList<Location> StepperLocations;
    private Sun Sun;

    //Konstruktorok

    /**
     * Konstruktor,
     * be�ll�tja a t�rolt objektumokat
     */
    public Space(){
        Game = new Game();
        Locations = new ArrayList<Location>();
        Sun = new Sun(this);
        StepperLocations = new ArrayList<Location>();
    }

    /**
     * Konstruktor,
     * be�ll�tja a t�rolt objektumokat
     *
     * @param g be�ll�tja Game-nek
     */
    public Space(Game g){
        Game = g;
        Locations = new ArrayList<Location>();
        Sun = new Sun(this);
        StepperLocations = new ArrayList<Location>();
    }
    
    //--------------F�ggv�nyek----------------------

    /**
     * Seg�df�ggv�ny a SunStorm-hoz
     * Visszat�r azon helysz�nek list�j�val, melyeken entit�s tart�zkodik
     *
     * @return Model.Location-�k list�ja, melyeken entit�s tart�zkodik
     */
    public ArrayList<Location> GetActiveLocations(){
        ArrayList<Location> ActiveLocations = new ArrayList<Location>();

        for(int i = 0; i < Locations.size(); i++){
            if(Locations.get(i).getEntitiesOnSurface().size() > 0){
                ActiveLocations.add(Locations.get(i));
            }
        }

        return ActiveLocations;
    }

    /**
     * A j�t�kt�rben lev� Model.Location-�k szomsz�ds�gait �ll�tja be
     */
    public void SetNeighbours(){
        for(Location l1 : Locations){
            for(Location l2 : Locations){
                if(l2 == l1) continue;
                if(l1.getCoordinate().distance(l2.getCoordinate()) <= 140){
                    if(!l1.GetNeighbours().contains(l2) && !l2.GetNeighbours().contains(l1)){
                        l1.AddNeighbour(l2);
                        l2.AddNeighbour(l1);
                    }
                }
            }
        }
    }

    /**
     * A param�terk�nt kapott l Model.Location-t, felveszi a Model.Location-�k list�j�ra
     *
     * @param l hozz�adja a Model.Location-�k list�j�ra
     */
    public void AddLocation(Location l){
        Locations.add(l);
        Game.viewController.addObject(l.getView());
    }

    /**
     * A param�terk�nt kapott l Model.Location-t, felveszi a StepperLocation-�k list�j�ra
     *
     * @param l hozz�adja a StepperLocation-�k list�j�ra
     */
    public void AddStepperLocation(Location l){
        StepperLocations.add(l);
    }

    /**
     * A param�terk�nt kapott l Model.Location-t, elt�vol�tja a Model.Location-�k list�j�r�l
     *
     * @param l elt�vol�tja a Model.Location-�k list�j�r�l
     */
    public void RemoveLocation(Location l){
        Locations.remove(l);
        Game.viewController.removeObject(l.getView());
    }


    //-----------Setterek & Getterek------------\\

    /**
     * Getter
     *
     * @return elt�rolt Model.Sun
     */
    public Sun GetSun(){
        return Sun;
    }

    /**
     * Getter
     *
     * @return elt�rolt Game
     */
    public Game getGame() {
        return Game;
    }

    /**
     * Getter
     *
     * @return elt�rolt Model.Location-�k list�ja
     */
    public ArrayList<Location> getLocations() {
        return Locations;
    }

    /**
     * Getter
     *
     * @return elt�rolt StepperLocation-�k list�ja
     */
    public ArrayList<Location> getStepperLocations() {
        return StepperLocations;
    }

    /**
     * Getter
     *
     * @param game az elt�rolt Game
     */
    public void setGame(Game game) {
        Game = game;
    }

    /**
     * Setter
     *
     * @param locations erre �ll�tja be a Locations list�t
     */
    public void setLocations(ArrayList<Location> locations) {
        Locations = locations;
    }

    /**
     * Setter
     *
     * @param stepperLocations erre �ll�tja be a StepperLocations list�t
     */
    public void setStepperLocations(ArrayList<Location> stepperLocations) {
        StepperLocations = stepperLocations;
    }

    /**
     * Setter
     *
     * @param sun erre �ll�tja be a Model.Sun-t
     */
    public void setSun(Sun sun) {
        Sun = sun;
    }

}
