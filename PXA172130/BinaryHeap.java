/**
 * @author Priyanka Awaraddi
 * @author Leejia James
 *
 * Implementing bounded-size Binary Heap
 *
 * Ver 1.0: 2018/09/18
 */

package PXA172130;

//Starter code for bounded-size Binary Heap implementation
//Changed signature of heapSort changing <T> to <T extends Comparable<? super T>>
//poll returns null if pq is empty (not false)

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class BinaryHeap<T extends Comparable<? super T>> {
	T[] pq;
	Comparator<T> comp;
	int size;
	// Constructor for building an empty priority queue using natural ordering of T
	public BinaryHeap(T[] q) {
		// Use a lambda expression to create comparator from compareTo
		this(q, (T a, T b) -> a.compareTo(b));
	}
 
	// Constructor for building an empty priority queue with custom comparator
	public BinaryHeap(T[] q, Comparator<T> c) {
		pq = q;
		comp = c;
		size = 0;
	}
 
	/** Build a priority queue with a given array q, using q[0..n-1].
	*  It is not necessary that n == q.length.
	*  Extra space available can be used to add new elements.
	*  Implement this if solving optional problem.  To be called from heap sort.
	*/
	private BinaryHeap(T[] q, Comparator<T> c, int n) {
		pq = q;
		comp = c;
		size = 0;
		// You need to add more code here to build queue
	}

    /**
     * Inserts a new element x to the priority queue
     *
     * @param x element to add
     * 
     * @exception throws exception if priority queue is full
     */
	public void add(T x) throws Exception {
		internalAdd(x);
	}
 
    /**
     * Internal method to add new element x to the priority queue
     * Used by both add() and offer() methods
     *
     * @param x element to add
     * 
     * @exception throws exception if priority queue is full
     */
	private void internalAdd(T x) throws Exception
	{
		if(size == pq.length)
			throw new Exception("Priority queue is full");
		move(size, x);
		percolateUp(size);
		size++;
	}
	
    /**
     * Inserts a new element x to the priority queue
     *
     * @param x element to add
     * 
     * @return true if the element is added, false if the element 
     * is not added because the priority queue is full
     */
	public boolean offer(T x) {
		try
		{
			internalAdd(x);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

    /**
     * Removes and returns the element with maximum priority
     * 
     * @exception throws exception if priority queue is empty
     * 
     * @return the element with maximum priority (min value)
     */
	public T remove() throws Exception {
		return internalRemove();
	}
 
    /**
     * Internal method to remove and return the element with maximum priority
     * Used by both remove() and poll() methods
     * 
     * @exception throws exception if priority queue is empty
     * 
     * @return the element with maximum priority (min value)
     */
	private T internalRemove() throws Exception
	{
		if(size == 0)
			throw new Exception("Priority queue is empty");
		T min = pq[0];
		move(0, pq[size-1]);
		size--;
		percolateDown(0);
		return min;
	}

    /**
     * Removes and returns the element with maximum priority
     * 
     * @return returns the element with maximum priority (min value), 
     * returns null if priority queue is empty
     */
	public T poll() {
		try
		{
			T x = internalRemove();
			return x;
		}
		catch(Exception e)
		{
			return null;
		}
	}

    /**
     * Returns the element with maximum priority without removing it
     * 
     * @return the element with maximum priority (min value), 
     * returns null if priority queue is empty
     */
	public T peek() {
		if(size == 0)
			return null;
		return pq[0];
	}

	/** pq[i] may violate heap order with parent */
	void percolateUp(int i) { 
		T x = pq[i];
		while((pq[parent(i)].compareTo(x) > 0) && i > 0)
		{
			move(i,pq[parent(i)]);
			i=parent(i);
		}
		move(i,x);
	}

	/** pq[i] may violate heap order with children */
	void percolateDown(int i) { 
		T x = pq[i];
		int c = leftChild(i);
		while(c <= size-1)
		{
			if(c < size-1 && pq[c].compareTo(pq[c+1]) > 0)
			{
				c++;
			}			
			if(x.compareTo(pq[c]) <= 0)
				break;
			pq[i] = pq[c];
			i = c;
			c = leftChild(i);
		}
		move(i,x);
	}

	void printBinaryHeap()
	{
		for(int i=0; i<size; i++)
		{
			System.out.print(pq[i] + " ");
		}
	
		System.out.println();
	}
	
	
	// Assign x to pq[i].  Indexed heap will override this method
	void move(int i, T x) {
		pq[i] = x;
	}

	int parent(int i) {
		return (i-1)/2;
	}

	int leftChild(int i) {
		return 2*i + 1;
	}

//end of functions for team project




//start of optional problem: heap sort (Q2)

 /** Create a heap.  Precondition: none. 
  *  Implement this ifsolving optional problem
  */
 void buildHeap() {
 }

 /* sort array arr[].
    Sorted order depends on comparator used to buid heap.
    min heap ==> descending order
    max heap ==> ascending order
    Implement this for optional problem
  */
 public static<T extends Comparable<? super T>> void heapSort(T[] arr, Comparator<T> c) { /* to be implemented */
 }

 // Sort array using natural ordering
 public static<T extends Comparable<? super T>> void heapSort(T[] arr) {
	heapSort(arr, (T a, T b) -> a.compareTo(b));
 }
//end of optional problem: heap sort (Q2)



//start of optional problem: kth largest element (Q3)

 public void replace(T x) {
	/* TO DO.  Replaces root of binary heap by x if x has higher priority
	     (smaller) than root, and restore heap order.  Otherwise do nothing. 
	   This operation is used in finding largest k elements in a stream.
	   Implement this if solving optional problem.
	 */
 }

 /** Return the kth largest element of stream using custom comparator.
  *  Assume that k is small enough to fit in memory, but the stream is arbitrarily large.
  *  If stream has less than k elements return null.
  */
 public static<T extends Comparable<? super T>> T kthLargest(Iterator<T> stream, int k, Comparator<T> c) {
	return null;
 }

 /** Return the kth largest element of stream using natural ordering.
  *  Assume that k is small enough to fit in memory, but the stream is arbitrarily large.   
  *  If stream has less than k elements return null.
  */
 public static<T extends Comparable<? super T>> T kthLargest(Iterator<T> stream, int k) {
	return kthLargest(stream, k, (T a, T b) -> a.compareTo(b));
 }
//end of optional problem: kth largest element (Q3)
 
 public static void main(String[] args) throws Exception
 {	 
	int n = 5;
	if(args.length > 0) {
		n = Integer.parseInt(args[0]);
	}
	
	Integer[] arr = new Integer[10];
	BinaryHeap<Integer> bh = new BinaryHeap<>(arr);
	for(int i=1; i<=n; i++) {
		bh.offer(Integer.valueOf(i));
	}
	bh.printBinaryHeap();
	
	Scanner in = new Scanner(System.in);
	whileloop:
	while(in.hasNext()) {
		int com = in.nextInt();
		switch(com) {
		case 1:  // Add a new element x to the priority queue
			int elemAdd = in.nextInt();
			System.out.println(bh.offer(elemAdd));
			bh.printBinaryHeap();
			break;
		case 2:  // Add a new element x to the priority queue,
				 // throw exception if priority queue is full
			int elemAddOne = in.nextInt();
			bh.add(elemAddOne);
			bh.printBinaryHeap();
			break;
		case 3:  // Remove and return the element with max priority (min value)
			System.out.println(bh.poll());
			bh.printBinaryHeap();
			break;
		case 4:  // Remove and return the element with max priority (min value),
			 	 // throw exception if priority queue is empty
			System.out.println(bh.remove());
			bh.printBinaryHeap();
			break;
		case 5:  // Return the element with max priority, without removing it 	
			System.out.println(bh.peek());
			bh.printBinaryHeap();
			break;			
		default:  // Exit loop
			break whileloop;
		}
	}
	bh.printBinaryHeap();
 }
}
