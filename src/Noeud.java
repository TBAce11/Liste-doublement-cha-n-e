/* Noms : Anita Abboud et Tarik Benakezouh
 * Description du fichier: Class Noeud qui contient un Item et les pointeurs pour les noeuds avant et après
 * lui dans la liste chainée triée. */

public class Noeud {
    protected Item item;
    protected Noeud prochain;
    protected Noeud precedent;

    public Noeud(Item item, Noeud precedent, Noeud prochain) {
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