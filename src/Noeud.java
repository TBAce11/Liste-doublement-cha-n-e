public class Noeud {
    protected Item item;
    protected Noeud prochain;
    protected Noeud precedent;

    public Noeud(Item item, Noeud precedent, Noeud prochain) {
        /*if (Main.isInteger(value)) {
            this.item.setValue(Integer.parseInt(value));
        }
        else if(Main.isDouble(value)){
            this.item.setValue(Double.parseDouble(value));
        }
        else{
            this.item.setValue(value);
        }*/
        this.item = item;
        this.precedent = precedent;
        this.prochain = prochain;
    }

    public Item getItem(){
        return this.item;
    }

    public Noeud getPrecedent(){
        return this.precedent;
    }

    public Noeud getProchain(){
        return this.prochain;
    }

    public void setPrecedent(Noeud noeudPrecedent){
        this.precedent = noeudPrecedent;
    }

    public void setProchain(Noeud prochainNoeud){
        this.prochain = prochainNoeud;
    }

}