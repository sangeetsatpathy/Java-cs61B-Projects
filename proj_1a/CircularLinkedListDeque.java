/*Invariants:
size is always the size of the linked list
sentinel.next is always the first node of the deque
*/
package proj_1a;
public class CircularLinkedListDeque<T>{
	public Node sentinel;
	public int size;
	//public static final T randomItem;  // in order to try to add a random generic value for the sentinel nodes.
	public T randomItem = (T) (Object) 13;//cast the value into an Object, and cast that into a T generic.

	private class Node{
		public T head;
		public Node next;
		public Node prev;

		public Node(T h){
			head = h;
		}
		public Node(T h, Node n, Node p){
			head = h;
			next = n;
			prev = p;
			//this.next.prev=this;//NullPointerException
			//note: until a reference variable is instantiated with a pointer, it will amount to null.
		}
	}
//TWO ANALYSES: One in Second Node Constructor(1 arg)/implementation in Deque constructor    AND   addFirst before and after.
/*creates an empty linkedlist double ended queue*/
	public CircularLinkedListDeque(){	
		//sentinel = new Node(randomItem, sentinel, sentinel); this will not work: the right side of = evaluates first. since sentinel
		// is a class variable, it will start of as null. Sentinel is only instantiated after the right side is evaluated, Exception.
		sentinel = new Node(randomItem);
		sentinel.next = sentinel;//this uses a new constructor, so we know what the value of sentinel is, allowing us to define stnl.next
		sentinel.prev = sentinel;
	}



/*Adds an item of type T to the front of the deque.*/
	public void addFirst(T item){
		//Node newNode = new Node(item, sentinel.next, sentinel);//makes random node with a next of sentinel.next and previous of sentinel
		//sentinel.next.prev = newNode;//the CURRENT first item has its previous set to the new node.
		//sentinel.next = newNode;//this is where it becomes official that this is the first node.
		size++;
		
		sentinel.next = new Node(item, sentinel.next, sentinel);//firstSentinel.next is always the first item.
		sentinel.next.next.prev = sentinel.next;

	}
/*Adds an item of type T to the back of the deque.*/
	public void addLast(T item){
		Node newNode = new Node(item, sentinel, sentinel.prev);
		sentinel.prev.next = newNode;
		sentinel.prev = newNode;
		size++;
	}
/*Returns true if deque is empty, false otherwise.*/
	public boolean isEmpty(){
		if (size==0) {
			return true;
		}
		return false;
	}
/*Returns the number of items in the deque.*/
	public int size(){
		return size;
	}
/*Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.*/
	public void printDeque(){
		for (int i=0;i<size();i++) {
			System.out.print(get(i));
			System.out.print(" ");
		}
		System.out.println();
	}
/* Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
	public T removeFirst(){
		if(isEmpty()){
			return null;
		}
		T rValue = sentinel.next.head;
		sentinel.next.next.prev = sentinel;
		sentinel.next = sentinel.next.next;
		size-=1;
		return rValue;
	}
/* Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
	public T removeLast(){
		if (isEmpty()) {
			return null;
		}
		T rValue = sentinel.prev.head;
		sentinel.prev.prev.next = sentinel;
		sentinel.prev = sentinel.prev.prev;
		size-=1;
		return rValue;
	}
/* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. 
If no such item exists, returns null. Must not alter the deque!*/
	public T get(int index){
		if (index >= size) {
			return null;
		}
		Node n = sentinel.next;
		for (int i=0;i<index;i++) {
			n=n.next;
		}
		return n.head;
	}

/*Helper method for get, returns the first node in the deque.*/
	private Node getFirst(){
		return sentinel.next;
	}

	private Node getRecursiveNode(int index){
		if (index==0) {
			return getFirst();
		}
		return getRecursiveNode(index-1).next;
	}

/*same as get, but uses recursive*/
	public T getRecursive(int index){
		return getRecursiveNode(index).head;
	}

	public static void main(String[] args) {
		CircularLinkedListDeque<Integer> L = new CircularLinkedListDeque<Integer>();
		L.addFirst(10);
		L.addFirst(20);
		//L.addLast(15);
		//L.addLast(25);
		System.out.println(L.size());
		System.out.println(L.sentinel.next.next.prev.head);
		L.printDeque();
		/*L.removeLast();
		L.printDeque();
		L.removeFirst();
		L.printDeque();*/
	}

}


