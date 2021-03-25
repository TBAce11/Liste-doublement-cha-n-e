public class ListeDoublementChainee implements IListeDoublementChainee {
    private String sens;
    private Noeud premier;
    private Noeud dernier;
    private int taille;

    public ListeDoublementChainee(String sens) {
        this.sens = sens;
    }

    public void ajouterListe(String listeEnString) {
        String contenu = listeEnString.substring(1, listeEnString.length() - 1);
        String[] listeContenu = contenu.split(",");
        Item item;
        for (int i = 0; i < listeContenu.length; i++) {
            if (Main.isInteger(listeContenu[i])) {
                item = new Item(Integer.parseInt(listeContenu[i]));
            } else if (Main.isDouble(listeContenu[i])) {
                item = new Item(Double.parseDouble(listeContenu[i]));
            } else {
                item = new Item(listeContenu[i]);
            }
            ajouterNoeud(item);
        }
    }

    public void ajouterNoeud(Item<?> item) {
        if (taille == 0) {
            Noeud noeud = new Noeud(item, null, null);
            this.premier = noeud;
            this.dernier = noeud;
            this.taille++;
        } else if (taille == 1) {
            int conclusion = item.compareTo(this.premier.getItem()); //changer this.premier pour getNoeud(i) où i=0
            if (conclusion > 0) {
                Noeud noeudAvant = this.premier;
                Noeud nouveauNoeud = new Noeud(item, noeudAvant, null);
                this.dernier = nouveauNoeud; //premier noeud inchangé
                noeudAvant.setProchain(nouveauNoeud);

            } else { //voulons-nous que l'égalité trie le noeud avant ou après celui qui existe déjà?
                Noeud noeudApres = this.premier;
                Noeud nouveauNoeud = new Noeud(item, null, noeudApres);
                this.premier = nouveauNoeud; //dernier noeud inchangé
                noeudApres.setPrecedent(nouveauNoeud);
            }
            this.taille++;
        } else {
            int conclusion, secondeConclusion;
            boolean confirmationAjout = false;
            for (int i = 0; i < taille; i++) { //ajouter la condition pour la fin de la liste
                conclusion = item.compareTo(getNoeud(i).getItem());
                if (conclusion > 0) { //ajouter la vérification du reste de la liste
                    for (int j = i + 1; j < taille; j++) {
                        secondeConclusion = item.compareTo(getNoeud(j).getItem());
                        if (secondeConclusion < 0) {
                            Noeud noeudAvant = getNoeud(j - 1);
                            Noeud nouveauNoeud = new Noeud(item, noeudAvant, noeudAvant.getProchain());
                            noeudAvant.getProchain().setPrecedent(nouveauNoeud);
                            noeudAvant.setProchain(nouveauNoeud);
                            this.taille++;
                            confirmationAjout = true;
                            i = taille;
                            break; //remplacer par un booléen qui contrôle l'extérieur et les conditions if si nécessaire
                        } else if (j == taille - 1) { //ajout en fin de liste si tous les items sont inférieurs
                            Noeud noeudAvant = getNoeud(j);
                            Noeud nouveauNoeud = new Noeud(item, noeudAvant, null);
                            this.dernier = nouveauNoeud; //premier noeud inchangé
                            noeudAvant.setProchain(nouveauNoeud);
                            this.taille++;
                            confirmationAjout = true;
                            i = taille;
                            break;
                        }
                    }
                }
            }
            //item plus petit que toute la liste -> placé au début de la file
            if (!confirmationAjout) {
                Noeud noeudApres = this.premier;
                Noeud nouveauNoeud = new Noeud(item, null, noeudApres);
                this.premier = nouveauNoeud; //dernier noeud inchangé
                noeudApres.setPrecedent(nouveauNoeud);
                this.taille++;
            }
        }
    }

    private Noeud getNoeud(int idx) {
        Noeud noeud = this.premier;
        for (int i = 0; i < idx; i++) {
            noeud = noeud.getProchain();
        }
        return noeud;
    }


    public void imprimerListeDuDebut() {
        System.out.print("Noeuds du début vers la fin: ");
        Noeud noeud = this.premier;

        while (noeud != null) {
            if (noeud.getProchain() != null) {
                System.out.print(noeud.getItem().getValue() + " -> ");
                noeud = noeud.getProchain();
            } else {
                System.out.print(noeud.getItem().getValue());
                noeud = noeud.getProchain();
            }
        }
    }

    public void imprimerListeDeLaFin() {
        System.out.print("\nNoeuds de la fin vers le début: ");
        Noeud noeud = this.dernier;
        while (noeud != null) {
            if (noeud.getPrecedent() != null) {
                System.out.print(noeud.getItem().getValue() + " -> ");
                noeud = noeud.getPrecedent();
            } else {

                System.out.print(noeud.getItem().getValue());
                noeud = noeud.getPrecedent();
            }
        }
    }
}


