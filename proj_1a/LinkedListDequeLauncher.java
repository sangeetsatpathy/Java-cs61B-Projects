import proj_1a.LinkedListDeque;
public class LinkedListDequeLauncher{
	public static void main(String[] args) {
		LinkedListDeque<Integer> L = new LinkedListDeque<Integer>();
		L.addFirst(10);
		L.addFirst(20);
		System.out.println(L.size());

		L.printDeque();

	}
}