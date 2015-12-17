public class StackMethods <AnyType> {

	private Node <AnyType> head;
	
	public StackMethods(){
		head = new Node <AnyType> ();
	}
	
	public boolean isEmpty() {
		return head.next == null;
	}

	public void push(AnyType x) {
		Node <AnyType> newNode = new Node <AnyType> ();
		newNode.data = x;
		
		Node <AnyType> current;
		current = head;
		
		newNode.next = current.next;
		current.next = newNode;
	}

	
	public AnyType pop() {
		if(!isEmpty()){
			Node <AnyType> current;
			current = head;
			
			Node <AnyType> retrieve;
			retrieve = current.next;
			
			current.next = current.next.next;
			
			return retrieve.data;
		} else{
			return null;
		}
	}

	
	public AnyType peek() {
		if(!isEmpty()){
			Node <AnyType> current;
			current = head;
			
			Node <AnyType> retrieve;
			retrieve = current.next;
			
			return retrieve.data;
		} else{
			return null;
		}
		
	}

}
