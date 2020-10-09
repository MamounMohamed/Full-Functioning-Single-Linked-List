class node {
     int type;
     node next = null;
    public node(int a) {
        type = a;
    }
    public String toString(){
        return Integer.toString(type);
    }
}
class List {
    private node head = null;
    private int size = 0 ;
    private int index;
    public int getSize(){
        return size;
    }
    public void add (int add ){
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
    public void sort () {
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
    public node get(int index){
        node get = head;
        for(int i = 0 ; i<index ; i++){
            get = get.next;
        }
        return get;
    }
    public void addFirst (int add){
        node put = new node (add);
        if (head == null)
            head = put;
        else {
            put.next = head ;
            head = put;
        }
        size+=1;
    }
    public void deleteFirst(){
        head = head.next;
        size-=1;
    }
    public void deleteLast(){
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
    public node GetFirst (){
        return head;
    }
    public node GetLast(){
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
    public void merge (List a ){
       node m = a.head;
       for (;m!=null ; m=m.next){
           this.add(m.type);
       }
    }
    public void addInSorted (int type) {
        if (head == null) {
            add(type);
        }
        if (type < head.type) {
            addFirst(type);
        } else if (type > GetLast().type) {
            add(type);
        } else {
            node put = new node(type);
            node previous = head;
            node checker = head;
            while (type > checker.type) {
                previous = previous.next;
                checker = checker.next;
                if (put.type <= checker.next.type & put.type > previous.type) {
                    put.next = checker.next;
                    previous.next = put;
                    size += 1;
                }
            }
        }
    }
    public node getMinmum () {
        node min = new node (Integer.MAX_VALUE);
        for(node i = head ; i!=null ; i=i.next){
            if(i.type<min.type)
                min=i;
        }
        return min ;
    }
    public node getMaximum () {
        node max = new node (Integer.MIN_VALUE);
        for(node i = head ; i!=null ; i=i.next){
            if(i.type>max.type)
                max=i;
        }
        return max ;
    }
    public node search(int key){
        index = 0 ;
        for (node i = head ; i!=null ; i=i.next){
            if (key == i.type) {
                return i;
            }
            index+=1;
        }
        return null ;
    }
    public void isThere(int key){
       node there = search(key);
       if(there!=null)
           System.out.println("number is found at index " + index);
       else
           System.out.println("Not there");
    }
    public void insertAtIndex(int index , int key) {
        if (index < 0 || index > size)
            System.out.println("index is not there");
        else if (index == 0)
            addFirst(key);
        else if(index==size)
            add(key);
        else {
            node put = new node(key);
            node check = head;
            node previous = head;
            for (int i = 0; i < index-1; i++) {
                check = check.next;
                previous = previous.next;
            }
            put.next =check.next;
            previous.next = put;
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