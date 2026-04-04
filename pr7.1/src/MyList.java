// MyList.java
public class MyList {
    MyArray first;
    int size;
    int blockSize;

    MyList(int blockSize) throws InvalidCapacityException {
        if (blockSize <= 0) {
            throw new InvalidCapacityException("Невірна місткість");
        }
        this.blockSize = blockSize;
        first = new MyArray(blockSize);
        size = 0;
    }

    public void add(int value) {
        MyArray cur = first;

        while (cur.next != null) {
            cur = cur.next;
        }

        if (cur.size == blockSize) {
            MyArray n = new MyArray(blockSize);
            cur.next = n;
            n.prev = cur;
            cur = n;
        }

        cur.data[cur.size] = value;
        cur.size++;
        size++;
    }

    public void addFirst(int value) throws InvalidIndexException {
        add(0, value);
    }

    public void add(int index, int value) throws InvalidIndexException {
        if (index < 0 || index > size) {
            throw new InvalidIndexException("Невірний індекс");
        }

        if (index == size) {
            add(value);
            return;
        }

        int[] oldArr = toArray();
        int[] newArr = new int[size + 1];

        for (int i = 0; i < index; i++) {
            newArr[i] = oldArr[i];
        }

        newArr[index] = value;

        for (int i = index; i < size; i++) {
            newArr[i + 1] = oldArr[i];
        }

        rebuild(newArr);
    }

    public int get(int index) throws InvalidIndexException, EmptyListException {
        if (size == 0) {
            throw new EmptyListException("Список порожній");
        }

        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Невірний індекс");
        }

        int k = 0;
        MyArray cur = first;

        while (cur != null) {
            for (int i = 0; i < cur.size; i++) {
                if (k == index) {
                    return cur.data[i];
                }
                k++;
            }
            cur = cur.next;
        }

        throw new InvalidIndexException("Невірний індекс");
    }

    public void remove(int index) throws InvalidIndexException, EmptyListException {
        if (size == 0) {
            throw new EmptyListException("Список порожній");
        }

        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Невірний індекс");
        }

        int[] oldArr = toArray();
        int[] newArr = new int[size - 1];
        int j = 0;

        for (int i = 0; i < size; i++) {
            if (i != index) {
                newArr[j] = oldArr[i];
                j++;
            }
        }

        rebuild(newArr);
    }

    public int size() {
        return size;
    }

    public int capacity() {
        int cap = 0;
        MyArray cur = first;

        while (cur != null) {
            cap += blockSize;
            cur = cur.next;
        }

        return cap;
    }

    public void clear() {
        first = new MyArray(blockSize);
        size = 0;
    }

    private int[] toArray() {
        int[] arr = new int[size];
        int k = 0;
        MyArray cur = first;

        while (cur != null) {
            for (int i = 0; i < cur.size; i++) {
                arr[k] = cur.data[i];
                k++;
            }
            cur = cur.next;
        }

        return arr;
    }

    private void rebuild(int[] arr) {
        clear();

        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
    }
}