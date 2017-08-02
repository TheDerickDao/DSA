import io.*;
public class RollingCorridor
{
	public Pallet[] queue;
	private int count;
	private final int DEFAULT_CAPACITY = 100;
	
	public RollingCorridor()
		{
		queue = new Pallet[DEFAULT_CAPACITY];
		count = 0;
		}
		
	public RollingCorridor(int maxCapacity)
		{
		queue = new Pallet[maxCapacity];
		count = 0;
		}
	
	public int getCount() // returns the amount of elements currently in the queue
		{
		return count;
		}
		
	public boolean isEmpty()
		{
		return count == 0;
		}
	
	public boolean isFull()
		{
		return count == DEFAULT_CAPACITY;
		}
	
	public void enqueue(Pallet inValue) // inserts an element into the queue
		{
		if (isFull() == true)
			{
			throw new IllegalArgumentException("ABORT QUEUE");
			}
		else
			{
			queue[count] = inValue;
			count = count + 1;
			// System.out.println("Pushing onto queue: " + inValue + ", current queue count is: " + count);
			}
		}
	
	public Pallet dequeue() // removes and returns the front most item in the queue
		{
		Pallet frontVal;
		frontVal = peek();
		for (int ii = 0; ii <= count - 1; ii++)
			{
			queue[ii] = queue[ii+1];
			}
		count = count - 1;
		return frontVal;
		}
	
	public Pallet peek() // returns the value of the front most item in the queue
		{
		Pallet topVal;
		if (isEmpty() == true)
			{
			throw new IllegalArgumentException("QUEUE IS EMPTY");
			}
		else
			{
			topVal = queue[0];
			}
		return topVal;
		}
	
	public Pallet getQueue(int index) // returns values of elements in the queue given their index
		{
			return queue[index];
		}
	
	public int length() // returns the length of the queue
	{
		return queue.length;
	}
}
		