class node { // that's a single pointer node that only points to the next node
     int type;
     node next = null;
    public node(int a) {
        type = a;
    }
    public String toString(){
        return Integer.toString(type);
    }
}
class List { // this list will work great if you want to create a stack that inherits it's functions but not queues due to it's O(N) addAtThelast a queue or an enqueue would be better if we created double linked lists
    private node head = null;
    private int size = 0 ;
    private int index;
    public int getSize(){
        return size;
    }
    public void add (int add ){ // adds at the end of the list
        node put = new node (add);
        if (head == null)
            head = put;
        else {
            node m = head;
            for (; m.next!=null ; m= m.next){}
            m.next = put ;
        }
        size+=1;
    }
    public void sort () { // we using selection sort here 
        for (node i = head; i.next != null; i = i.next) {
            for (node j = i.next; j != null; j = j.next) {
                if (j.type < i.type){
                    int temp = i.type;
                    i.type = j.type;
                    j.type=temp;
                }
            }
        }
    }
    public node get(int index){ // gets the node at a given index
        node get = head;
        for(int i = 0 ; i<index ; i++){
            get = get.next;
        }
        return get;
    }
    public void addFirst (int add){// pushes an element to the front of the list and can serve as a push in a stack with O(1)complexity
        node put = new node (add);
        if (head == null)
            head = put;
        else {
            put.next = head ;
            head = put;
        }
        size+=1;
    }
     //we increment the size with every addition and decrement it with every deletion so we can always return the size in O(1)
    public void deleteFirst(){ // deletes the first object
        head = head.next;
        size-=1;
    }
    public void deleteLast(){ // deletes the last node
        if (head==null)
            System.out.print("No Items \n");
        else if (head.next==null) {
            head = null;
            size -= 1;
        }
        else {
            node m = head;
        while (m.next.next!=null){
            m=m.next;
        }
            m.next=null;
            size-=1;
        }
    }
    public node GetFirst (){ // get the head of the list
        return head;
    }
    public node GetLast(){ // get the tail of the list
        if (head.next==null)
            return head ;
        else{
            node m = head ;
           while(m.next!=null){
               m=m.next;
           }
            return m ;
        }
    }
    public void merge (List a ){//merges two lists
       node m = a.head;
       for (;m!=null ; m=m.next){
           this.add(m.type);
       }
    }
    public void addInSorted (int type) { // add in the right place in respect to sorted list
        if (head == null) { // three logically easy conditions that makes us avoid null pointers  
            add(type);
        }
        if (type < head.type) { 
            addFirst(type);
        } else if (type > GetLast().type) {
            add(type);
        } else {
            node put = new node(type); // the node we want to put
            node previous = head;//the previous node
            node checker = head; // the node we are standing on
            while (type > checker.type) { // while the node key is les than the checker we stand on keep incrementing the checker and the previus
                previous = previous.next;
                checker = checker.next;
                if (put.type <= checker.next.type & put.type > previous.type) { // if the node key is less than the next & bigger than the previous
                    put.next = checker.next; // let the node point to the node next to checker
                    previous.next = put; // let the previous node point to the new added node
                    size += 1;
                }
            }
        }
    }
    public node getMinmum () { // returns the minmum element on the list
        node min = new node (Integer.MAX_VALUE);
        for(node i = head ; i!=null ; i=i.next){
            if(i.type<min.type)
                min=i;
        }
        return min ;
    }
    public node getMaximum () {  // returns the maximum element on the list
        node max = new node (Integer.MIN_VALUE);
        for(node i = head ; i!=null ; i=i.next){
            if(i.type>max.type)
                max=i;
        }
        return max ;
    }
    public node search(int key){ // searchs for a node in the list and returns it
        index = 0 ; // we need it for the there method don't mind it
        for (node i = head ; i!=null ; i=i.next){
            if (key == i.type) {
                return i;
            }
            index+=1;// we increment it after every iteration
        }
        return null ;
    }
    public void isThere(int key){
       node there = search(key);
       if(there!=null)
           System.out.println("number is found at index " + index // the index we found from the search method
       else
           System.out.println("Not there");
    }
    public void insertAtIndex(int index , int key) {
        if (index < 0 || index > size) // three easily logical conditions
            System.out.println("index is not there");
        else if (index == 0)
            addFirst(key);
        else if(index==size)
            add(key);
        else {
            node put = new node(key);
            node check = head;
            node previous = head;
            for (int i = 0; i < index-1; i++) {//iterate till the  node before the index , increase the previous and the checker
                check = check.next;
                previous = previous.next;
            }
            put.next =check.next;//make the new node point to the node we're standing on
            previous.next = put;//make the previous points to the new node
            size+=1;
        }
    }
    public void insertAfterNode (int key , int num) {
        if (search(key) != null) {
            node put = new node(num);
            node previous = search(key);
            if(search(key).next!=null) {
                node after = search(key).next;
                put.next = after;
                previous.next = put;
                size += 1;
            }
            else {
                add(num);
            }
        }
        else
            System.out.println("Node not found");
    }
    public void reverse (){
        List temp = new List();
        while(!this.isEmpty()){
            temp.add(this.GetFirst().type);
            this.deleteFirst();
        }
        while (!temp.isEmpty()){
            this.addFirst(temp.GetFirst().type);
            temp.deleteFirst();
        }
    }
    public void insertInMiddle(int add){
        insertAtIndex(size/2 ,add);
    }
    public void deleteNode(int key) {
        if (search(key) == null)
            System.out.println("Node already not in the list");
        else if (search(key) == GetFirst())
            deleteFirst();
        else if (search(key)==GetLast())
            deleteLast();
           else {
            node previous = head;
            node current = search(key);
            while (previous.next != current) {
                previous = previous.next;
            }
                previous.next = current.next;
                size -= 1;
        }
    }
    public void deleteAtIndex(int index){
        size-=1;
        node previous = head;
        node after = head;
        for (int i = 0 ; i<index-1 ; i++){
            previous = previous.next;
            after = after.next;
        }
       previous.next= after.next.next;
    }
    public boolean isEmpty(){
        return head==null;
    }
    public String toString (){
      String str = new String();
        for(node m = head ; m!=null ; m=m.next)
        str +=m +" ";
    return str;
    }
}
