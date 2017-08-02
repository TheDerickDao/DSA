import io.*;
public class DeadEnd
{
	public Pallet[] stack;
	private int count;
	private final int DEFAULT_CAPACITY = 100;
	
	public DeadEnd() //default
		{
		stack = new Pallet[DEFAULT_CAPACITY];
		count = 0;
		}
	
	public DeadEnd(int maxCapacity) //alternate
		{
		stack = new Pallet[maxCapacity];
		count = 0;
		}
	
	public int getCount() // returns the amount of items within the stack
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
	
	public void push(Pallet inValue) // pushes an item onto the stack
		{
		if (isFull() == true)
			{
			throw new IllegalArgumentException("STACK FULL, CAN'T PUSH");
			}
		else
			{
			stack[count] = inValue;
			count = count + 1;
			// System.out.println("Pushing onto stack: " + inValue + ", current stack count is: " + count);
			}
		}
	
	public Pallet pop() // returns and removes the top item of the stack
		{
		Pallet topVal;
		topVal = top();
		stack[count-1] = null;
		count = count - 1;
		return topVal;
		}
	
	public Pallet top() // returns the value of the top most item of the stack
		{
		Pallet topVal;
		if (isEmpty() == true)
			{
			throw new IllegalArgumentException("STACK EMPTY, CAN'T CHECK TOP");
			}
		else
			{
			topVal = stack[count - 1];
			}
		return topVal;
		}
	
	public Pallet getStack(int index) // gets the value of the element in the stack depending on the index
		{
			Pallet stackVal;
			stackVal = stack[index];
			return stackVal;
		}
	
	public int length() // returns the length of the stack
	{
		return stack.length;
	}
}