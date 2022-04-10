package deque;

import edu.neu.ccs.XObject;

public class LinkedListDeque<T> implements Deque<T> {
    public class Node{
        public T item;
        public Node next;
        public Node prev;
        public Node(T i, Node n, Node p){
            item = i;
            next = n;
            prev = p;
        }
    }
    private int size;
    private final Node sentinelHead;
    public  LinkedListDeque(){
        sentinelHead = new Node(null,null,null);
        sentinelHead.next = sentinelHead;
        sentinelHead.prev = sentinelHead;
        size = 0;

    }

    public LinkedListDeque(T item){
        this();
        size = 1;
        Node node = new Node(item,sentinelHead,sentinelHead);
        sentinelHead.next = node;
        sentinelHead.prev = node;
    }

    public void addFirst(T item){
        Node newnode = new Node(item, sentinelHead.next, sentinelHead);
        sentinelHead.next.prev = newnode;
        sentinelHead.next = newnode;
        size +=1;
    }
    public void addLast(T item){
        Node oldLast = sentinelHead.prev;
        Node newnode = new Node(item,sentinelHead,oldLast);
        sentinelHead.prev = newnode;
        oldLast.next = newnode;
        size +=1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node first = sentinelHead.next;
        while(first!=sentinelHead){
            System.out.print(first.item);
            System.out.print(' ');
            first = first.next;
        }
        System.out.println();
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T item = sentinelHead.next.item;
        Node deleteNode = sentinelHead.next;
        sentinelHead.next = deleteNode.next;
        deleteNode.next.prev = deleteNode.prev;
        if (sentinelHead.prev == deleteNode){
            sentinelHead.prev = sentinelHead;
        }
        size -= 1;
        return item;
    }
    public T removeLast(){
        if(isEmpty()){
            return  null;
        }
        T item = sentinelHead.prev.item;
        Node deleteNode = sentinelHead.prev;
        Node newTail = deleteNode.prev;
        newTail.next = sentinelHead;
        sentinelHead.prev = newTail;
        size -= 1;
        return item;


    }
    public T get(int index){
        if(isEmpty()){
            return null;
        }
        if(index > size-1 ){
            return  null;
        }
        Node temp = sentinelHead;
        for(int i=0;i<index;i++){
            temp = temp.next;
        }
        return temp.next.item;
    }

    public T getRecursive(int index){
        if(isEmpty()){
            return null;
        }
        if(index > size-1 ){
            return  null;
        }
        return getRecursiveHelper(index, sentinelHead.next);
    }
    private T getRecursiveHelper(int index, Node currentNode) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(index - 1, currentNode.next);
    }

}
