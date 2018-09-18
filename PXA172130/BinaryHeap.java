package PXA172130;

//Starter code for bounded-size Binary Heap implementation
//Changed signature of heapSort changing <T> to <T extends Comparable<? super T>>
//poll returns null if pq is empty (not false)

import java.util.Comparator;
import java.util.Iterator;

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
	size=0;
 }
 
 /** Build a priority queue with a given array q, using q[0..n-1].
  *  It is not necessary that n == q.length.
  *  Extra space available can be used to add new elements.
  *  Implement this if solving optional problem.  To be called from heap sort.
  */
 private BinaryHeap(T[] q, Comparator<T> c, int n) {
	pq = q;
	comp = c;
	size=0;
	// You need to add more code here to build queue
 }

 public void add(T x) throws Exception { /* throw exception if pq is full */
	 internalAdd(x);
 }
 
 private void internalAdd(T x) throws Exception
 {
	 if(size==pq.length)
		 throw new Exception("Priority queue is full");
	 move(size,x);
	 percolateUp(size);
	 size++;
 }
 public boolean offer(T x) { /* return false if pq is full */
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

 public T remove() throws Exception { /* throw exception if pq is empty */
	return internalRemove();
 }
 
 private T internalRemove() throws Exception
 {
	 if(size==0)
		 throw new Exception("Priority queue is empty");
	T min = pq[0];
	move(0,pq[size-1]);
	size--;
	percolateDown(0);
	return min;
 }

 public T poll() { /* return null if pq is empty */
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

 public T peek() { /* return null if pq is empty */
	if(size==0)
		 return null;
	return pq[0];
 }

 /** pq[i] may violate heap order with parent */
 void percolateUp(int i) { /* to be implemented */
	 T x = pq[i];
	 while((pq[parent(i)].compareTo(x)>0) && i>0)
	 {
		 move(i,pq[parent(i)]);
		 i=parent(i);
	 }
	 move(i,x);
 }

 /** pq[i] may violate heap order with children */
 void percolateDown(int i) { /* to be implemented */
	 T x = pq[i];
	 int c = leftChild(i);
	 while(c<=size-1)
	 {
		 if(c<size-1 && pq[c].compareTo(pq[c+1])>0)
		 {
			 c++;
		 }
		 if(x.compareTo(pq[c])<=0)
			 break;

		 pq[i] = pq[c];
		 i =c;
		 c = leftChild(i);
	 }
	 move(i,c);
 }

 void printBinaryHeap()
 {
	 for(int i=0;i<size;i++)
	 {
		 System.out.print(pq[i]+" ");
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
	 Integer[] a = new Integer[2];
	 BinaryHeap<Integer> obj = new BinaryHeap<Integer>(a);
	 obj.add(1);
	 obj.printBinaryHeap();
	 obj.add(4);
	 obj.printBinaryHeap();
	 obj.remove();
	 obj.printBinaryHeap();
	 obj.remove();
	 obj.printBinaryHeap();
	 obj.poll();
	 /*obj.printBinaryHeap();
	 obj.remove();
	 obj.printBinaryHeap();*/
 }
}
