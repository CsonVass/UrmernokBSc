package Model;

//A j�t�kbeli ur�nt, mint nyersanyagot reprezent�l� oszt�ly
public class Uran extends Material {

    //----------Tulajdons�gok----------\\

    private int numOfExp = 0;


    //-----------F�ggv�nyek--------------\\


    /**
     * Ha az ur�n fedetlenn� v�lt napk�zelben, felrobbantja a param�terk�nt kapott Model.Location-t, amiben van
     *
     * @param l amely Model.Location-ben tal�lhat� a nyersanyag
     */
    @Override
    public void Exposed(Location l){
    	if(l.isNearSun()){
            numOfExp++;
            if(numOfExp >= 3){
                l.Explode();
            }
        }
    }

    public void  SetCounter(int i){
        numOfExp = i;
    }

    public int GetCounter(){return numOfExp;}

    public String toString(){
        return "Uran";
    }

}
