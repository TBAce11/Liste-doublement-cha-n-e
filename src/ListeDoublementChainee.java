/* Noms : Anita Abboud et Tarik Benakezouh
 * Description du fichier: Class ListeDoublementChainee qui implémente l'interface IListeDoublementChainee qui spécifie
 * le sens de la chaine, les noeuds de tête et de queue ainsi que la taille de la chaine. */

public class ListeDoublementChainee implements IListeDoublementChainee {
    private String sens;
    private Noeud premier;
    private Noeud dernier;
    private int taille;

    public ListeDoublementChainee(String sens) {
        this.sens = sens;
    }

    // Methode qui retourne le noeud prochain de la liste tant que nous n'avons pas atteint la queue
    private Noeud getNoeud(int idx) {
        Noeud noeud = this.premier;
        for (int i = 0; i < idx; i++) {
            noeud = noeud.getProchain();
        }
        return noeud;
    }

    // Methode qui crée un Item avec le bon type pour chaque élément entré dans la liste de la ligne de commande
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

    // Methode qui ajoute un noeud dans la liste chainée selon le sens spécifiée en ligne de commande
    public void ajouterNoeud(Item<?> item) {
        // Ajout du premier noeud de la liste
        if (taille == 0) {
            Noeud noeud = new Noeud(item, null, null);
            this.premier = noeud;
            this.dernier = noeud;
            this.taille++;

        // Ajout du seconde noeud de la liste
        } else if (taille == 1) {
            int comparaison = item.compareTo(this.premier.getItem()); // Entier dictant la relation du 1er item par
                                                                      // rapport au deuxième
            if(this.sens.contains("desc")){ // Inversion du résultat de la comparaison lors d'un tri descendant
                comparaison = -comparaison;
            }
            if (comparaison > 0) { // Valeur de l'Item actuel supérieur à celui de la liste
                Noeud noeudAvant = this.premier;
                Noeud nouveauNoeud = new Noeud(item, noeudAvant, null);
                this.dernier = nouveauNoeud; // Premier noeud inchangé
                noeudAvant.setProchain(nouveauNoeud);

            } else { // Valeur de l'Item actuel inférieur ou égal à celui de la liste
                Noeud noeudApres = this.premier;
                Noeud nouveauNoeud = new Noeud(item, null, noeudApres);
                this.premier = nouveauNoeud; // Dernier noeud inchangé
                noeudApres.setPrecedent(nouveauNoeud);
            }
            this.taille++;

        // Ajout d'un noeud dans une liste à plus de 2 noeuds
        } else {
            int comparaison, secondeComparaison; // Valeur de la comparaison entre deux noeuds
            boolean confirmationAjout = false; // Confirmation que le noeud a été ajouté
            // Comparaison entre le noeud etudié et le reste des noeuds déjà triés
            for (int i = 0; i < taille; i++) {
                comparaison = item.compareTo(getNoeud(i).getItem());
                if(this.sens.contains("desc")){
                    comparaison = -comparaison;
                }
                if (comparaison > 0) {
                    //Deuxième boucle se chargeant de trouver le premier item supérieur à l'actuel à partir de l'item de
                    //inférieur, triant le noeud à droite du noeud inférieur et à gauche du premier noued supérieur à
                    //celui-ci
                    for (int j = i + 1; j < taille; j++) { // Index j reprenant le travail à partir de l'index i actuel
                        secondeComparaison = item.compareTo(getNoeud(j).getItem());
                        if(this.sens.contains("desc")){
                            secondeComparaison = -secondeComparaison;
                        }
                        if (secondeComparaison < 0) {
                            Noeud noeudAvant = getNoeud(j - 1);
                            Noeud nouveauNoeud = new Noeud(item, noeudAvant, noeudAvant.getProchain());
                            noeudAvant.getProchain().setPrecedent(nouveauNoeud);
                            noeudAvant.setProchain(nouveauNoeud);
                            this.taille++;
                            confirmationAjout = true;
                            i = taille; // Condition mettant fin à la boucle fin dès la prochaine itération
                            break;
                        } else if (j == taille - 1) { // Ajout en fin de liste si tous les items sont inférieurs
                            Noeud noeudAvant = getNoeud(j);
                            Noeud nouveauNoeud = new Noeud(item, noeudAvant, null);
                            this.dernier = nouveauNoeud;
                            noeudAvant.setProchain(nouveauNoeud);
                            this.taille++;
                            confirmationAjout = true;
                            i = taille;
                            break;
                        }
                    }
                }
            }
            if (!confirmationAjout) { // Item plus petit que ceux de toute la liste placé au début de la file
                Noeud noeudApres = this.premier;
                Noeud nouveauNoeud = new Noeud(item, null, noeudApres);
                this.premier = nouveauNoeud; // Dernier noeud inchangé
                noeudApres.setPrecedent(nouveauNoeud);
                this.taille++;
            }
        }
    }

    public void imprimerListeDuDebut() {
        System.out.print("Noeuds du début vers la fin: ");
        Noeud noeud = this.premier;
        while (noeud != null) {
            if (noeud.getProchain() != null) {
                System.out.print(noeud.getItem().getValue() + "->");
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
                System.out.print(noeud.getItem().getValue() + "->");
                noeud = noeud.getPrecedent();
            } else {
                System.out.print(noeud.getItem().getValue());
                noeud = noeud.getPrecedent();
            }
        }
    }
}


