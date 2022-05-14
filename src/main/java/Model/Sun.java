package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

//A j�t�kbeli Napot reprezent�l� oszt�ly, mely a napviharok�rt felel�s
public class Sun implements Stepable {

    //Tulajdons�gok
    private int Counter = -1;

    //T�rolt objektumok
    private Space space;

//Konstruktorok

    /**
     * Konstruktor
     */
    public Sun(){
    }

    /**
     * Konstruktor
     *
     * @param s be�ll�tja Model.Space-nek
     */
    public Sun(Space s){
        space = s;
    }
    
    //--------------F�ggv�nyek----------------------

    /**
     * A Nap l�p�s�t reprezent�l� f�ggv�ny
     *
     * Random id�k�z�nk�nt Napvihar,
     * ekkor 3 k�rrel k�s�bb megind�tja a Napvihart (erre van a Counter)
     */
    @Override
    public void Step() {
        if(Counter >= 0){
            if(Counter == 0){
                SunStorm();
            }
            Count();
        }
        else{
            Random rand = new Random();
            int probability = rand.nextInt(100);
            if(probability <= 20){
                Counter = 3;
            }
        }
    }

    /**
     * A napvihar �rkez�s�t reprezent�l� f�ggv�ny
     *
     * Minden akt�v helysz�n SunStorm f�ggv�ny�t megh�vja
     */
    public void SunStorm(){
        ArrayList<Location> activeLocations = space.GetActiveLocations();
        for(int i = 0; i < activeLocations.size(); i++){
            Random rand = new Random();
            int probability = rand.nextInt(100);
            if(probability <= 70){
                activeLocations.get(i).SunStorm();
            }
        }
    }

    /**
     * Sz�mol�s, a napvihar megfelel� k�rben �rkez�s�hez
     */
    public void Count(){
        Counter--;
    }

    /**
     * Getter
     *
     * @return Counter �rt�ke
     */
    public int getCounter() {
        return Counter;
    }

    /**
     * Getter
     *
     * @return elt�rolt Model.Space
     */
    public Space getSpace() {
        return space;
    }


    public void SetCounter(int i){
        Counter = i;
    }
}
