package LinkedListTemplate;

public class TestLinkedList {

	public static void main(String[] args) {
		
	
		LinkedList<Integer> a = new LinkedList<Integer>();
		a.addFirst(5);
		a.addFirst(3);
		a.addFirst(7);
		a.addLast(10);
		a.add(13,  12);
		a.displayList();
		System.out.println();
		a.delete(12);
		a.displayList();
		System.out.println();
		a.deleteFirst();
		a.displayList();
		System.out.println();
		a.deleteLast();
		a.displayList();
	

	}

}
