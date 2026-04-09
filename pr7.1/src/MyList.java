public class MyList<T> {
    MyArray<T> first;
    int size;
    int blockSize;

    public MyList(int blockSize) throws InvalidCapacityException {
        if (blockSize <= 0) {
            throw new InvalidCapacityException("Невірна місткість");
        }
        this.blockSize = blockSize;
        first = new MyArray<T>(blockSize);
        size = 0;
    }

    public void add(T value) {
        MyArray<T> cur = first;

        while (cur.next != null) {
            cur = cur.next;
        }

        if (cur.size == blockSize) {
            MyArray<T> n = new MyArray<T>(blockSize);
            cur.next = n;
            n.prev = cur;
            cur = n;
        }

        cur.data[cur.size] = value;
        cur.size++;
        size++;
    }

    public void addFirst(T value) throws InvalidIndexException {
        add(0, value);
    }

    public void add(int index, T value) throws InvalidIndexException {
        if (index < 0 || index > size) {
            throw new InvalidIndexException("Невірний індекс");
        }

        if (index == size) {
            add(value);
            return;
        }

        Object[] oldArr = toArray();
        Object[] newArr = new Object[size + 1];

        for (int i = 0; i < index; i++) {
            newArr[i] = oldArr[i];
        }

        newArr[index] = value;

        for (int i = index; i < size; i++) {
            newArr[i + 1] = oldArr[i];
        }

        rebuild(newArr);
    }

    public T get(int index) throws InvalidIndexException, EmptyListException {
        if (size == 0) {
            throw new EmptyListException("Список порожній");
        }

        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Невірний індекс");
        }

        int k = 0;
        MyArray<T> cur = first;

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

        Object[] oldArr = toArray();
        Object[] newArr = new Object[size - 1];
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
        MyArray<T> cur = first;

        while (cur != null) {
            cap += blockSize;
            cur = cur.next;
        }

        return cap;
    }

    public void clear() {
        first = new MyArray<T>(blockSize);
        size = 0;
    }

    private Object[] toArray() {
        Object[] arr = new Object[size];
        int k = 0;
        MyArray<T> cur = first;

        while (cur != null) {
            for (int i = 0; i < cur.size; i++) {
                arr[k] = cur.data[i];
                k++;
            }
            cur = cur.next;
        }

        return arr;
    }

    private void rebuild(Object[] arr) {
        clear();

        for (int i = 0; i < arr.length; i++) {
            add((T) arr[i]);
        }
    }
}