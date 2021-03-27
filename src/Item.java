/* Noms : Anita Abboud et Tarik Benakezouh
* Description du fichier: Classe Item qui implémente l'interface Comparable et qui contient une valeur
* de type quelconque. */

public class Item<T> implements Comparable<Item> {
    private T value;

    public Item(T element) {
        this.value = element;
    }

    @Override
    public int compareTo(Item element) {

        // Comparaison lorsqu'un des items est un String
        if ((element.getValue() instanceof String) || (this.getValue() instanceof String)) {
            return (String.valueOf((this.getValue()))).compareTo((String.valueOf(element.getValue())));

        // Comparaison lorsqu'un des items est un Double
        } else if ((element.getValue() instanceof Double) || (this.getValue() instanceof Double)) {
            double difference;

            // Casting manuel d'un Integer vers un Double
            if (element.getValue() instanceof Integer) {
                difference = ((Double) ((this.getValue()))) - ((Integer) element.getValue()).doubleValue();
            } else if (this.getValue() instanceof Integer) {
                difference = ((Integer) this.getValue()).doubleValue() - ((Double) (element.getValue()));
            } else {
                difference = ((Double) ((this.getValue()))) - ((Double) (element.getValue()));
            }

            // Empêche les erreurs d'arrondissement dans les doubles lorsque la difference entre deux nombres est
            // tres basse.
            if (difference < 1 && difference > 0) { // Arrondissement vers les positifs
                return (int) (Math.ceil(difference));
            } else if (difference > -1 && difference < 0) { // Arrondissement vers les negatifs
                return (int) (Math.floor(difference));
            } else {
                return (int) (Math.round(difference)); // Arrondissement normal
            }

        // Comparaison entre deux entiers
        } else {
            int difference = ((Integer) (this.getValue())) - ((Integer) (element.getValue()));
            return difference;
        }
    }

    public T getValue() {
        return this.value;
    }
}
