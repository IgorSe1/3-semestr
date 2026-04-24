import java.util.Comparator;

public class MyList<T extends Comparable<T>> implements Comparable<MyList<T>> {

    MyArray<T> first;
    int size;
    int blockSize;

    public MyList(int blockSize) throws MyException {
        if (blockSize <= 0) {
            throw new MyException("Невірна місткість блоку");
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
            cur.next = new MyArray<T>(blockSize);
            cur = cur.next;
        }

        cur.data[cur.size] = value;
        cur.size++;
        size++;
    }

    public void addFirst(T value) throws MyException {
        add(0, value);
    }

    public void add(int index, T value) throws MyException {
        if (index < 0 || index > size) {
            throw new MyException("Невірний індекс: " + index);
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

    public T get(int index) throws MyException {
        if (size == 0) {
            throw new MyException("Список порожній");
        }
        if (index < 0 || index >= size) {
            throw new MyException("Невірний індекс: " + index);
        }

        int k = 0;
        MyArray<T> cur = first;

        while (cur != null) {
            for (int i = 0; i < cur.size; i++) {
                if (k == index) {
                    return (T) cur.data[i];
                }
                k++;
            }
            cur = cur.next;
        }

        throw new MyException("Помилка при отриманні елемента");
    }

    public void remove(int index) throws MyException {
        if (size == 0) {
            throw new MyException("Список порожній");
        }
        if (index < 0 || index >= size) {
            throw new MyException("Невірний індекс: " + index);
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

    @Override
    public int compareTo(MyList<T> other) {
        return this.size - other.size;
    }

    public void sort() throws MyException {
        if (size == 0) {
            throw new MyException("Список порожній");
        }

        Object[] arr = toArray();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                T a = (T) arr[j];
                T b = (T) arr[j + 1];

                if (a.compareTo(b) > 0) {
                    Object tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        rebuild(arr);
    }

    public void sort(Comparator<T> comp) throws MyException {
        if (size == 0) {
            throw new MyException("Список порожній");
        }

        Object[] arr = toArray();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                T a = (T) arr[j];
                T b = (T) arr[j + 1];

                if (comp.compare(a, b) > 0) {
                    Object tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        rebuild(arr);
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

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        MyArray<T> cur = first;

        while (cur != null) {
            for (int i = 0; i < cur.size; i++) {
                sb.append(cur.data[i]);
                sb.append(", ");
            }
            cur = cur.next;
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
