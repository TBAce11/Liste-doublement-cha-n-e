public class Item<T> implements Comparable<Item> {
    private T value; //value était de type T

    public Item(T element) {
        this.value = element;
    }

    @Override
    public int compareTo(Item element) {
        if ((element.getValue() instanceof String) || (this.getValue() instanceof String)) {
            return (String.valueOf((this.getValue()))).compareTo((String.valueOf(element.getValue())));
        }
        else if ((element.getValue() instanceof Double) || (this.getValue() instanceof Double)) {
            double difference;
            if (element.getValue() instanceof Integer){
                difference = ((Double)((this.getValue())))-((Integer)element.getValue()).doubleValue();
            }
            else if (this.getValue() instanceof Integer){
                difference = ((Integer) this.getValue()).doubleValue()-((Double)(element.getValue()));
            }
            else{
                difference = ((Double)((this.getValue())))-((Double)(element.getValue())); // Possibilite de faire erreur si casting un int en double
            }
            return (int)(Math.round(difference));
        }
        else{
            int difference = ((Integer) (this.getValue()))-((Integer) (element.getValue()));
            return difference;
        }
            /*
            if (Main.isInteger(this.getValue())) {
                if (element == this) {
                    return -1;
                } else {
                    return ((Integer.parseInt(element.getValue())) > (Integer.parseInt(this.getValue())));
                }
            } else if (Main.isDouble((String) this.getValue())) {

            } else {

            }

        } else if (Main.isDouble(element.getValue())) {
            if (Main.isInteger(this.getValue())) {

            } else if (Main.isDouble(this.getValue())) {

            } else {

            }

        } else {

        }*/
    }

    public T getValue() {
        return this.value;
    }

    protected void setValue(T valeur){
        this.value = valeur;
    }
}
