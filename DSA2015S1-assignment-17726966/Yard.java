import io.*;
public class Yard
{
	public Pallet[] array;
	private int count;
	private final int DEFAULT_CAPACITY = 100;
	
	public Yard()
	{
		array = new Pallet[DEFAULT_CAPACITY];
		count = 0;
	}
		
	public Yard(int maxCapacity)
	{
		array = new Pallet[maxCapacity];
		count = 0;
	}
		
	public int getCount() // gets the capacity of the current array
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
	
	public void insertFirst(Pallet inValue) // inserts at the start of the array
	{
		if(isFull())
		{
			throw new IllegalArgumentException("Array is full");
		}
		else
		{
			for (int ii = count; ii > 0; ii--)
			{
				array[ii] = array[ii-1];
			}
			array[0] = inValue;
			count++;
		}
	}
	
	public void insertEnd(Pallet inValue) // inserts at the end of the array depending on how many items in the current array
	{
		if(isFull())
		{
			throw new IllegalArgumentException("Array is full");
		}
		else
		{
			array[count]= inValue;
		}
	}
	
	public void insertMiddle(Pallet inValue) // inserts in a middle slot
	{
		int position;
		
		position = 0;
		
		if(isFull())
		{
			throw new IllegalArgumentException("Array is full");
		}
		
		while (array[position] != null && position < count)
		{
			position++;
		}
		
		for(int ii = count; ii > position; ii--)
		{
			array[ii] = array[ii-1];
		}
		
		array[position] = inValue;
		count++;
	}
	
	public Pallet removeLast() // removes the last array item
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("Array is empty");
		}
		
		count--;
		return array[count+1];
	}
	
	public Pallet removeFirst() // removes the first array item
	{
		Pallet value;
		
		if(isEmpty())
		{
			throw new IllegalArgumentException("Array is empty");
		}
		
		value = array[0];
		for (int ii = count; ii > 0; ii--)
		{
			array[ii] = array[ii+1];
		}
		count--;
		
		return value;
	}
	
	public Pallet removeMiddle(Pallet inValue) // removes a middle array item
	{
		Pallet value;
		int location;
		
		if(isEmpty())
		{
			throw new IllegalArgumentException("Array is empty");
		}
		
		location = find(inValue);
		value = array[location];
		for (int ii = count; ii > 0; ii--)
		{
			array[ii] = array[ii+1];
		}
		count--;
		
		return value;
	}
	
	public Pallet removeMiddle(int index) // removes a value in array by giving the index of the slot we want to erase
	{
		Pallet value;
		
		if(isEmpty())
		{
			throw new IllegalArgumentException("Array is empty");
		}
		
		value = array[index];
		for (int ii = count; ii > 0; ii--)
		{
			array[ii] = array[ii+1];
		}
		count--;
		
		return value;
	}
	
	public int find(Pallet key) // finds an array given a imported pallet
	{
		int location;
		boolean found;
		
		location = 0;
		found = false;
		
		do
		{
			if (array[location].equals(key))
			{
				found = true;
			}
			else
			{
				location++;
			}
		} while (!found && location < count);
		
		if (found == false)
		{
			throw new IllegalArgumentException("Key not found in array");
		}
		
		return location;
	}
	
	public Pallet getArray(int index) // looks at the content of an array given an index
		{
			return array[index];
		}
	
	public int length() // returns the length of the array
	{
		return array.length;
	}
}