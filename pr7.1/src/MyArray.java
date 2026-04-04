public class MyArray {
    int[] data;
    int size;
    MyArray next;
    MyArray prev;

    MyArray(int capacity) {
        data = new int[capacity];
        size = 0;
        next = null;
        prev = null;
    }
}