package Model;

import java.util.ArrayList;

//A j�t�kbeli �p�t�shez sz�ks�ges recepteket reprezent�l� oszt�ly
public class Recipe {

    //T�rolt objektumok
    private ArrayList<Material> Ingredients;

    //Konstruktorok

    /**
     * Konstruktor
     */
    public Recipe(){
        Ingredients = new ArrayList<Material>();
    }
    
    //--------------F�ggv�nyek----------------------

    /**
     * Megn�zi, hogy a param�terk�nt kapott Model.Material sz�ks�ges-e a recepthez
     *
     * @param m err�l d�nti el, hogy sz�ks�ges-e a recepthez
     */
    public void isNeeded(Material m){
        for (int i = 0; i < Ingredients.size(); i++){
            if(Ingredients.get(i).MaterialType(m)){
                Ingredients.remove(i);
                return;
            }
        }
    }

    /**
     * Ha megvan minden sz�ks�ges nyersanyag, elveszi azokat,
     * �s friss�ti a telepes eszk�zt�r�t
     *
     * @param inventory a telepes inventory-ja
     * @return a friss�tett inventory
     */
    public ArrayList<Material> UpdateInventory(ArrayList<Material> inventory){
        ArrayList<Material> tmp = new ArrayList<>();
        tmp.addAll(Ingredients);
        //Possible place of shit happening with indexes
        for(int i = 0; i < inventory.size(); i++){
            int iOffset = 0;
            for(int j = 0; j < tmp.size(); j++) {
                if(inventory.get(i).MaterialType(tmp.get(j))){
                    inventory.remove(i);
                    tmp.remove(j);
                    iOffset = 1;
                    j--;
                }
            }
            i -= iOffset;
        }
        return inventory;
    }

    /**
     * Ellen�rzi, hogy megvan-e minden sz�ks�ges nyersanyag a recepthez
     *
     * @param inventory a telepes inventory-ja
     * @return az ellen�rz�s eredm�nye
     */
    public boolean HasEverything(ArrayList<Material> inventory){
        ArrayList<Material> initial = new ArrayList<>();
        initial.addAll(Ingredients);

        for(int i = 0; i < inventory.size(); i++){
            isNeeded(inventory.get(i));
        }

        boolean success = Ingredients.size() == 0;
        Ingredients = initial;
        return success;
    }

    /**
     * Be�ll�tja a robothoz sz�ks�ges nyersanyagokat sz�ks�gesnek
     */
    public void SetToRobot(){
        Ingredients.add(new Iron());
        Ingredients.add(new Coal());
        Ingredients.add(new Uran());
    }

    /**
     * Be�ll�tja a teleportkapuhoz sz�ks�ges nyersanyagokat sz�ks�gesnek
     */
    public void SetToTeleportGate(){
        Ingredients.add(new Iron());
        Ingredients.add(new Iron());
        Ingredients.add(new Water());
        Ingredients.add(new Uran());
    }

    /**
     * Be�ll�tja a nyer�shez sz�ks�ges nyersanyagokat sz�ks�gesnek
     */
    public void SetToWin(){
        Ingredients.add(new Coal());
        Ingredients.add(new Iron());
        Ingredients.add(new Uran());
        Ingredients.add(new Water());
    }

}
