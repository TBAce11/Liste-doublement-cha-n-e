public class Noeud {

    protected int element;
    protected Noeud prochain;
    protected Noeud precedent;

    public Noeud( int element, Noeud precedent, Noeud prochain ) {
        this.element = element;
        this.precedent = precedent;
        this.prochain = prochain;
    }
}