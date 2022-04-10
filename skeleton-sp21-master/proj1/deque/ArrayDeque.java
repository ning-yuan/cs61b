package deque;

import net.sf.saxon.om.Item;


public class ArrayDeque<T> implements Deque<T>{
    @SuppressWarnings("unchecked")

    private  T[] items = (T[]) new Object[8];
    private int size;
    private int front;
    private int rear;
    private static int FACTOR = 2;

    public ArrayDeque(){
        size = 0;
        front = 3;
        rear = 4;
    }
    public ArrayDeque(T item) {
        items[3] = item;
        size = 1;
        front = 2;
        rear = 4;

    }

    @SuppressWarnings("unchecked")
    private void resize(int s) {
        T[] newItems = (T[]) new Object[s];
        int firstPos = Math.abs(s - size) / 2;
        System.arraycopy(items, front + 1, newItems, firstPos, size);
        items = newItems;
        front = firstPos - 1;
        rear = firstPos + size;
    }
    private void shrinkSize() {
        if (isEmpty()) {
            resize(8);
        } else if (items.length / 4 > size && size >= 4) {
            resize(size * 2);
        }
    }



    public void addFirst(T item){
        items[front] = item;
        front -=1;
        size +=1;
        if(front == -1){
            resize(size*FACTOR);
        }

    }
    public void addLast(T item){
        items[rear] = item;
        rear += 1;
        size +=1;
        if(rear == items.length){
            resize(size*FACTOR);

        }

    }
    public boolean isEmpty(){
        return(size == 0);
    }
    public int size(){
        return size;
    }

    public void printDeque() {
        int cursor = (front + 1) % items.length;
        while(cursor != rear){
            System.out.print(items[cursor]);
            System.out.print(' ');
            cursor = (cursor + 1) % items.length;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        front += 1;
        T item = items[front];
        items[front] = null;
        size -= 1;
        shrinkSize();
        return item;
    }
    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        rear -= 1;
        T item = items[rear];
        items[rear] = null;
        size -= 1;
        shrinkSize();
        return item;
    }
    public T get(int index){
        if (index < 0 || index > size - 1) {
            return null;
        }
        int itemIndex = front + 1 + index;
        return items[itemIndex];
    }
    /*
    public Iterable<T> iterator(){

    }*/
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<?> ad = (ArrayDeque<?>) o;
        if (ad.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (ad.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

}
