public class QueueMethods <AnyType> {

	private DoublyNodeInterface <AnyType> head;
	private DoublyNodeInterface <AnyType> tail;
	
	public QueueMethods(){
		head = new DoublyNodeInterface<AnyType>();
		tail = new DoublyNodeInterface<AnyType>();
		
		head.prev = null;
		head.next = tail;
		tail.next = null;
		tail.prev = head;
	}
	
	public boolean isEmpty() {
		return tail.prev == head;
	}

	public void enqueue(AnyType x) { 		//insert
		DoublyNodeInterface <AnyType> newNode = new DoublyNodeInterface <AnyType>();
		newNode.data = x;
		
		DoublyNodeInterface <AnyType> last;
		last = tail;
		
		last.prev.next = newNode;
		newNode.next = last;
		last.prev = newNode;
		newNode.prev = last.prev;
		
	}

	public AnyType dequeue() {			// delete and retrieve the deleted
		if(!isEmpty()){
			DoublyNodeInterface <AnyType> current;
			current = head;
			
			DoublyNodeInterface <AnyType> retrieve;
			retrieve = current.next;
			
			(current.next.next).prev = current;
			current.next = current.next.next;
			
			return retrieve.data;
		} else{
			return null;
		}
	}

	public AnyType peek() {
		if(!isEmpty()){
			DoublyNodeInterface<AnyType> current;
			current = head;
			
			DoublyNodeInterface <AnyType> retrieve;
			retrieve = current.next;
			
			return retrieve.data;
		} else{
			return null;
		}
	}
	
	// returns a string of everything in queue. this is for writing postfix notation in file
	public String getCurrent(){
		String expression = "";
		DoublyNodeInterface <AnyType> current = head.next;
		while(current.next != null){
			expression += current.data;
			current = current.next;
		}
		
		return expression;
	}
}
