public class Item<T> implements Comparable<Item> {
    private T value;

    public Item(T element) {
        this.value = element;
    }

    @Override
    public int compareTo(Item element) {
        if (Main.isInteger((String) element.getValue())) {
            return 0;
        } else if (Main.isDouble((String) element.getValue())) {
            return 1;
        }
        return -1;
    }

    public T getValue() {
        return this.value;
    }
}
