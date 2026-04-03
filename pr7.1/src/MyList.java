class MyList {
    MyArray first;
    int size;
    int blockSize = 5;

    MyList(){
        first = new MyArray(blockSize);
        size = 0;
    }

    public void add(int value){
        MyArray cur = first;
        while(cur.next != null){
            cur = cur.next;
        }
        if(cur.size == blockSize){
            MyArray n = new MyArray(blockSize);
            cur.next = n;
            n.prev = cur;
            cur = n;
        }
        cur.data[cur.size] = value;
        cur.size++;
        size++;
    }

    public void addFirst(int value){
        add(0, value);
    }

    public void add(int index, int value){
        if(index < 0 || index > size) return;

        if(index == size){
            add(value);
            return;
        }

        int i = 0;
        MyArray cur = first;

        while(cur != null){
            for(int j = 0; j < cur.size; j++){
                if(i == index){
                    shiftRight(cur, j);
                    cur.data[j] = value;
                    size++;
                    return;
                }
                i++;
            }
            cur = cur.next;
        }
    }

    private void shiftRight(MyArray block, int pos){
        MyArray cur = block;

        while(cur != null){
            if(cur.size < blockSize){
                for(int i = cur.size; i > 0; i--){
                    cur.data[i] = cur.data[i-1];
                }
                cur.size++;
                return;
            }
            if(cur.next == null){
                MyArray n = new MyArray(blockSize);
                cur.next = n;
                n.prev = cur;
            }
            cur = cur.next;
        }
    }

    public int get(int index){
        if(index < 0 || index >= size) return -1;

        int i = 0;
        MyArray cur = first;

        while(cur != null){
            for(int j = 0; j < cur.size; j++){
                if(i == index){
                    return cur.data[j];
                }
                i++;
            }
            cur = cur.next;
        }
        return -1;
    }

    public void remove(int index){
        if(index < 0 || index >= size) return;

        int i = 0;
        MyArray cur = first;

        while(cur != null){
            for(int j = 0; j < cur.size; j++){
                if(i == index){
                    shiftLeft(cur, j);
                    size--;
                    return;
                }
                i++;
            }
            cur = cur.next;
        }
    }

    private void shiftLeft(MyArray block, int pos){
        MyArray cur = block;

        for(int i = pos; i < cur.size - 1; i++){
            cur.data[i] = cur.data[i+1];
        }
        cur.size--;
    }

    public int size(){
        return size;
    }

    public int capacity(){
        int cap = 0;
        MyArray cur = first;
        while(cur != null){
            cap += blockSize;
            cur = cur.next;
        }
        return cap;
    }

    public void clear(){
        first = new MyArray(blockSize);
        size = 0;
    }
}
