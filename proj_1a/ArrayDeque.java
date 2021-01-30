//Note: with a circular array approach, a first in, first out approach is what it means. Removing the last element means removing
//nextLast+1, or the one that was most recently added as first. Same with first.

public class ArrayDeque<T>{
	public int size;
	public int nextFirst;
	public int nextLast;
	public T[] items;
	//creates an empty deque
	public ArrayDeque(){
		items = (T[]) new Object[8];
		size=0;
		nextFirst=0;
		nextLast=1;
	}


	private void resize(int capacity){
		T[] a = (T[]) new Object[capacity];
		System.arraycopy(items, 0, a, 0, size);
		items = a;
	}

/*Adds an item of type T to the front of the deque.*/
	public void addFirst(T item){
		if (size == items.length) {
			resize(size*2);
		}
		items[nextFirst] = item;//UNCLEAR: do we circulate all the way until we resize??? Then we will end up with 'chunks' of circles
		size++;
		nextFirst-=1;
		if (nextFirst==-1) {
			nextFirst = items.length-1;
		}
	}
/*Adds an item of type T to the back of the deque.*/
	public void addLast(T item){
		if (size==items.length) {
			resize(size*2);			
		}		
		items[nextLast] = item;
		size++;
		nextLast+= 1;
		if(nextLast==size){
			nextLast=0; 
		}
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
/*IDEAS: 1. for the print deque, if the value = null, then skip it.      2. Print the values starting from nextFirst in order to nextLast
3. Make a copy of the array as a separate straightforward array, in which all the operations are performed.  */



/*Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.*/
	public void printDeque(){
		for (int i=0;i<items.length;i++) {
			if(items[i]==null){
				continue;
			}
			System.out.print(items[i]);//should be nextFirst or whatever.
			System.out.print(" ");
		}
		System.out.println();
	}
/* Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
//Note: add a statement in order to remove array elements if the array is less than 25% used. Prob will use resizing.
	public T removeFirst(){
		int a = nextFirst+1;
		if (a==items.length) {
			a=0;
		}

		T rValue = items[a];//INDICATOR FOR FIRST - done
		if (size/items.length <= 0.25 && items.length>=16) {//if the usage factor is less than 25% and the length is greater than 16
			//T [] a = (T[]) new Object[size-1];
			//System.arraycopy(items, 1, a, 0, size-1);
			T[] arr = (T[]) new Object[size/2];
			int k = 0;
			//adds all active elements of the array into the new array a.
			for (int i=0; i<items.length; i++) {
				if (items[i] == null || i==a) {//INDICATOR FOR FIRST- done
					continue;
				}
				arr[k++] = items[i];
			}
			items = arr;
			size-=1;
			return rValue;
		}
		items[a] = null;//INDICATOR FOR FIRST - done
		//if not small usage factor, just remove the first.
		//NOTE: What is the first? Is it nextFirst?
		size-=1;
		return rValue;
	}
/* Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
	public T removeLast(){
		int b = nextLast-1;
		if (b==-1) {
			b=items.length-1;
		}
		T rValue = items[b];//INDICATOR FOR LAST-done
		if (size/items.length <= 0.25 && items.length>=16) {//if the usage factor is less than 25% and the length is greater than 16
			//T[] arr = (T[]) new Object[size-1];
			//System.arraycopy(items, 0, arr, 0, size-1);
			T[] arr = (T[]) new Object[size/2];
			int i=0;
			for (int k =0; k<items.length; k++) {
				if (items[k] == null || k==b) {//INDICATOR FOR LAST-done
					continue;
				}
				arr[i++] = items[k];
			}
			items = arr;
			size-=1;
			return rValue;
		}
		items[b] = null;//INDICATOR FOR LAST-done
		size-=1;
		return rValue;
	}
/* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. 
If no such item exists, returns null. Must not alter the deque!*/
	public T get(int index){
		if (index > size-1) {
			return null;
		}
		return items[index];
	}

	public static void main(String[] args) {
		ArrayDeque<Integer> aList = new ArrayDeque<Integer>();
		aList.addFirst(10);
		aList.printDeque();
		System.out.println(aList.nextFirst);
		aList.addFirst(20);
		aList.printDeque();
		aList.addLast(15);
		aList.addLast(25);
		aList.printDeque();
		aList.removeFirst();
		aList.printDeque();
		aList.removeLast();
		aList.printDeque();
	}
}