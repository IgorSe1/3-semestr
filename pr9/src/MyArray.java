public class MyArray<T> {
    Object[] data;
    int size;
    MyArray<T> next;

    public MyArray(int capacity) {
        data = new Object[capacity];
        size = 0;
        next = null;
    }
}
