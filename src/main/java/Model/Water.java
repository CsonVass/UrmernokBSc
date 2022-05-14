package Model;

//A j�t�kbeli v�zjeget, mint nyersanyagot reprezent�l� oszt�ly
public class Water extends Material {


    /**
     * Ha a v�zj�g fedetlenn� v�lt napk�zelben, a v�z elszublim�l, azaz elt�nik
     *
     * @param l amely Model.Location-ben tal�lhat� a nyersanyag
     */
    @Override
    public void Exposed(Location l){
    	if(l.isNearSun()) {
        	l.RemoveMaterial();
        }
    }

    public String toString(){
        return "Water";
    }

}
