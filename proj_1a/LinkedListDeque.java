/*Invariants:
size is always the size of the linked list
*/
package proj_1a;
public class LinkedListDeque<T>{
	public Node firstSentinel;
	public Node endSentinel;
	public int size;
	//public static final T randomItem;  // in order to try to add a random generic value for the sentinel nodes.
	public T randomItem = (T) (Object) 13;//cast the value into an Object, and cast that into a T generic.
	private class Node{
		public T head;
		public Node next;
		public Node prev;

		public Node(T h, Node n, Node p){
			head = h;
			next = n;
			prev = p;
			this.prev.next=this;//fix this, this is for fixing problem of un-accounted double pointers.
			this.next.prev = this;
		}
	}

/*creates an empty linkedlist double ended queue*/
	public LinkedListDeque(){	
		firstSentinel = new Node(randomItem, endSentinel, endSentinel);//how to input a random value for sentinel with generics
		endSentinel = new Node(randomItem, firstSentinel, firstSentinel);
	}



/*Adds an item of type T to the front of the deque.*/
	public void addFirst(T item){
		firstSentinel.next = new Node(item, firstSentinel.next, firstSentinel);//firstSentinel.next is always the first item.
		firstSentinel.next.next.prev = firstSentinel.next;
		size++;
	}
/*Adds an item of type T to the back of the deque.*/
	public void addLast(T item){
		endSentinel.prev = new Node(item, endSentinel, endSentinel.prev);//endSentinel.prev is always the last item.
		endSentinel.prev.prev.next = endSentinel.prev;
		size++;
	}
/*Returns true if deque is empty, false otherwise.*/
	public boolean isEmpty(){
		if(firstSentinel.next == endSentinel){
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
		for(int i=0; i<size();i++){
			System.out.print(get(i));
			System.out.print(" ");
		}
		System.out.println();
	}
/* Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
	public T removeFirst(){
		if(size==0){
			return null;
		}
		Node removedValue = firstSentinel.next;
		firstSentinel.next=firstSentinel.next.next;//delinks, no longer in deque
		firstSentinel.next.prev = null;//deletes from memory
		// Add a way so that when you delink the removed node, its prev value will not link to the deleted node.
		firstSentinel.next.prev = firstSentinel;
		size-=1;
		return removedValue.head;
	}
/* Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
	public T removeLast(){
		if(size==0){
			return null;
		}
		Node removedValue = endSentinel.prev;
		endSentinel.prev = endSentinel.prev.prev;
		endSentinel.prev.next = null;//deletes from memory
		endSentinel.prev.next = endSentinel;
		size-=1;
		return removedValue.head;
	}
/* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. 
If no such item exists, returns null. Must not alter the deque!*/
	public T get(int index){
		Node n = firstSentinel.next;
		if(index>=size){
			return null;
		}
		for(int i=0; i<index; i++){
			n = n.next;
		}
		return n.head;
	}

/*Helper method for get, returns the first node in the deque.*/
	private Node getFirst(){
		return firstSentinel.next;
	}

	private Node getRecursiveNode(int index){
		if (index==0) {
			return getFirst();
		}
		return getRecursiveNode(index-1).next;
	}

/*same as get, but uses recursive*/
	public T getRecursive(int index){
		return getRecursiveNode(index).head;//uses a helper method, which in turn uses recursion
	}

	public static void main(String[] args) {
		LinkedListDeque<Integer> L = new LinkedListDeque<Integer>();
		L.addFirst(10);
		L.addFirst(20);
		//L.addLast(15);
		//L.addLast(25);
		System.out.println(L.size());
		System.out.println(L.firstSentinel.next.next.prev.head);
		System.out.println(L.endSentinel.prev.head);
		L.printDeque();

	}

}


