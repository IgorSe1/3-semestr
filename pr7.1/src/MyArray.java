public class MyArray<T> {
    T[] data;
    int size;
    MyArray<T> next;
    MyArray<T> prev;

    public MyArray(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
        next = null;
        prev = null;
    }
}