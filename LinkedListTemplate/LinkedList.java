package LinkedListTemplate;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedList<T>{
	int size;
	Node<T> head = null;
	Node<T> tail = null;
	
	public int getSize() {
		return size;
	}	

	/** runs the given function on each of the nodes in the ll
	 * returns the last node that the function was run for
	 * 
	 * at any time the caller function may return true to stop the execution from running
	 */
	public Node<T> forEach(Predicate<Node<T>> p) {
		Node<T> target = head;
		Boolean stop = false;
		for (int i = 0; i < size && !stop;i++) {
			stop = p.test(target);
			if (!stop)
				target = target.next;
		}
		return target;
	}

	public void addFirst(T element){
		Node<T> newNode = new Node<T>(element);
		
		newNode.next = head;
		head = newNode;
		size++;
		
		if(tail == null)
			tail = head;
	}
	
	
	public void addLast(T element){
		Node<T> newNode = new Node<T>(element);
		if(head == null){ // empty linked list
			head = tail = newNode;
		}
		
	   tail.next = newNode;
	   tail = tail.next;
	   size++;
	}
	
	public void deleteFirst(){
		if(head == null){
			System.out.println("Empty list. Cannot delete");
		}
		
		if(head == tail)
			head = tail = null;
		
		head = head.next;
		size--;
	}
	
	public void deleteLast(){
		if(tail == null){
			System.out.println("Empty list. Cannot delete");
		}
		Node<T> temp = head;
		
		while(temp.next!=tail)
			temp = temp.next;
		
		tail = temp;
		temp.next = null;
		size--;
		}
	
	
	public void add(T element, int index){
		if(index < 0 || index > size) 
			System.out.println("Invalid index");
		else if(index == 0) addFirst(element);
		else if(index == size) addLast(element);
		else {
			Node<T> newNode = new Node<T>(element);
			Node<T> temp = head;
		}
	}
	
	public void delete( int index) {
		
		if(index>=size) System.out.println("Invalid index");
		else if(index == 0) deleteFirst();
		else if(index == size-1)  deleteLast();
		else 
		{
			Node<T> temp = head;
			for(int i=0; i<index-1; i++){
				temp = temp.next;
			}
			
			Node<T> current = temp.next;
			temp.next = current.next;
			
			size--;
		}
	}
		
	
	public void displayList(){
		Node<T> temp = head;
		while(temp!=null){
			System.out.println(temp.element);
			temp = temp.next;
		}
	}
}
