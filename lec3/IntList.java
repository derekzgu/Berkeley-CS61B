public class IntList {
	public int head;
	public IntList tail;

	public IntList(int h, IntList t) {
		head = h;
		tail = t;
	}

	public int size() {
		if (tail == null) {
			return 1;
		}
		return 1 + tail.size();
	}

	/* Return the ith item in the list. */
	public int get(int i) {
		if (i == 0) {
			return head;
		}
		return tail.get(i - 1);
	}
	public String toString() {
		if (tail == null) {
			return Integer.toString(head);
		}
		return Integer.toString(head) + " " + tail.toString();
	}

	/** Return an IntList identical to L, but with all values incremented by x without 
	  * changing L. */
	public static IntList incrList(IntList L, int x) {
		if (L == null) {
			return null;
		}
		return new IntList(L.head + x, incrList(L.tail, x));
	}

	/** Return an IntList identical to L, but with all values incremented by x,
	  * without using 'new' keyword. */
	public static IntList dincrList(IntList L, int x) {
		if (L == null) {
			return null;
		}
		L.head += x;
		dincrList(L.tail, x);
		return L;
	}
	public static void main(String[] args) {
		IntList L = new IntList(1, null);
		L = new IntList(2, L);
		L = new IntList(55, L);
		IntList L1 = dincrList(L, 2);
		System.out.println(L1.head);
		System.out.println(L1.tail.head);
		System.out.println(L1.tail.tail);
	}
}