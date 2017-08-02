import io.*;
import java.io.*;
import java.util.*;
public class Warehouse
{
	public DeadEnd stack;
	public RollingCorridor queue;
	public Yard array;
	public Pallet pallet;
	
	public Warehouse() // Default Constructor
	{
		stack = new DeadEnd();
		queue = new RollingCorridor();
		array = new Yard();
	}
	
	public Warehouse(int capacity) // Alternate Constructor
	{
		stack = new DeadEnd(capacity);
		queue = new RollingCorridor(capacity);
		array = new Yard(capacity);
	}
	
	public void insertStack(Pallet inValue) // Inserting into stack
	{
		stack.push(inValue);
	}
	
	public void insertStack(Pallet inValue, String filename) // Inserting into stack
	{
		stack.push(inValue);
		System.out.println("0," + (stack.getCount()-1));
		try
		{
			warehouseDescription(filename);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void insertQueue(Pallet inValue) // Inserting into queue
	{
		queue.enqueue(inValue);
	}
	
	public void insertQueue(Pallet inValue, String filename) // Inserting into queue
	{
		queue.enqueue(inValue);
		System.out.println("1," + (queue.getCount()-1));
		try
		{
			warehouseDescription(filename);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void insertArray(Pallet inValue) // Inserting into array
	{
		array.insertMiddle(inValue);
	}
	
	public void insertArray(Pallet inValue, String filename) // Inserting into array
	{
		array.insertMiddle(inValue);
		System.out.println("2," + (array.getCount()-1));
		try
		{
			warehouseDescription(filename);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Pallet removeStack(String filename) // This returns the top value of the stack (stack)
	{
		Pallet removal;
		removal = stack.pop();
		System.out.println("0," + (queue.getCount()));
		try
		{
			warehouseDescription(filename);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return removal;
	}
	
	public Pallet removeQueue(String filename) // Returns and removes front value of the queue
	{
		Pallet removal;
		removal = queue.dequeue();
		System.out.println("1," + (queue.getCount()));
		try
		{
			warehouseDescription(filename);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return removal;
	}
	
	public Pallet removeArray(Pallet value, String filename) // Returns and removes the value stated from the array
	{
		Pallet removal;
		removal = array.removeMiddle(value);
		System.out.println("2," + (queue.getCount()));
		try
		{
			warehouseDescription(filename);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return removal;
	}
	
	public Pallet removeArray(int index, String filename) // Returns and removes an array using a known index
	{
		Pallet removal;
		removal = array.removeMiddle(index);
		System.out.println("2," + (queue.getCount()));
		try
		{
			warehouseDescription(filename);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return removal;
	}
	
	public void warehouseDescription(String filename) throws IOException //Writes and saves a file with the warehouse description and prints it on screen
	{
		File fileOut = new File(filename + "-output");
		FileOutputStream fileStrm = new FileOutputStream(fileOut);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileStrm));

		
		Pallet palletHold;
		String stackSerials;
		String serial;
		int serialNo;
	
		/** THIS BIT IS FOR STACK	** 
		**	WRITES THE STACK'S		**
		**	SERIAL NUMBERS AFTER	**
		**	THE LETTER 'd'			**
		** ************************ **/
		
		serial = "";
		stackSerials = "";
		for(int ii = 0; ii < (stack.length()); ii++)
		{
			palletHold = stack.getStack(ii);
			if (palletHold != null)
			{
				serialNo = palletHold.getSerialNumber();
				stackSerials = stackSerials + "," + serialNo;
			}
			else
			{
				serial = "";
				stackSerials = stackSerials + "," + serial ;
			}
		}
		bw.write("d" + stackSerials);
		bw.newLine();
		
		System.out.println("d" + stackSerials);
		
		/** THIS BIT IS FOR QUEUE	** 
		**	WRITES THE QUEUE'S		**
		**	SERIAL NUMBERS AFTER	**
		**	THE LETTER 'r'			**
		** ************************ **/
		
		serial = "";
		stackSerials = "";
		for(int ii = 0; ii < (queue.length()); ii++)
		{
			palletHold = queue.getQueue(ii);
			if (palletHold != null)
			{
				serialNo = palletHold.getSerialNumber();
				stackSerials = stackSerials + "," + serialNo;
			}
			else
			{
				serial = "";
				stackSerials = stackSerials + "," + serial ;
			}
		}
		bw.write("r" + stackSerials);
		bw.newLine();
		
		System.out.println("r" + stackSerials);
		
		/** THIS BIT IS FOR ARRAY	** 
		**	WRITES THE ARRAY'S		**
		**	SERIAL NUMBERS AFTER	**
		**	THE LETTER 'y'			**
		** ************************ **/
		
		serial = "";
		stackSerials = "";
		for(int ii = 0; ii < (array.length()); ii++)
		{
			palletHold = array.getArray(ii);
			if (palletHold != null)
			{
				serialNo = palletHold.getSerialNumber();
				stackSerials = stackSerials + "," + serialNo;
			}
			else
			{
				serial = "";
				stackSerials = stackSerials + "," + serial ;
			}
		}
		bw.write("y" + stackSerials);
		bw.newLine();
		System.out.println("y" + stackSerials);
		
		/** 		THIS BIT IS FOR SEPERATION			** 
		**												**
		**												**
		**												**
		** ******************************************** **/
		
		bw.write("%");
		bw.newLine();
		System.out.println("%");		
		
		/** THIS BIT IS FOR STACK PALLET DESCRIPTIONS	** 
		**												**
		**												**
		**												**
		** ******************************************** **/
		
		String description, supply, source, expiry;
		int year, month, day;
		Date date;
		
		
		for(int ii = 0; ii < (stack.length()); ii++)
		{
			description = "";
			supply = "";
			source = "";
			expiry = "";
			palletHold = stack.getStack(ii);
			if (palletHold != null)
			{
				serialNo = palletHold.getSerialNumber();
				supply = palletHold.getSupply();
				source = palletHold.getSourceName();
				date = palletHold.getExpiryDate();
				year = date.getYear();
				month = date.getMonth();
				day = date.getDay();
				expiry = year + "-" + month + "-" + day;
				description = description + serialNo + "," + supply + "," + source + "," + expiry;
				System.out.println(description);
			}
		}
		
		/** THIS BIT IS FOR QUEUE PALLET DESCRIPTIONS	** 
		**												**
		**												**
		**												**
		** ******************************************** **/
				
		for(int ii = 0; ii < (queue.length()); ii++)
		{
			description = "";
			supply = "";
			source = "";
			expiry = "";
			palletHold = queue.getQueue(ii);
			if (palletHold != null)
			{
				serialNo = palletHold.getSerialNumber();
				supply = palletHold.getSupply();
				source = palletHold.getSourceName();
				date = palletHold.getExpiryDate();
				year = date.getYear();
				month = date.getMonth();
				day = date.getDay();
				expiry = year + "-" + month + "-" + day;
				description = description + serialNo + "," + supply + "," + source + "," + expiry;
				bw.write(description);
				bw.newLine();
				System.out.println(description);
			}
		}
		
		/** THIS BIT IS FOR ARRAY PALLET DESCRIPTIONS	** 
		**												**
		**												**
		**												**
		** ******************************************** **/
		
		for(int ii = 0; ii < (array.length()); ii++)
		{
			description = "";
			supply = "";
			source = "";
			expiry = "";
			palletHold = array.getArray(ii);
			if (palletHold != null)
			{
				serialNo = palletHold.getSerialNumber();
				supply = palletHold.getSupply();
				source = palletHold.getSourceName();
				date = palletHold.getExpiryDate();
				year = date.getYear();
				month = date.getMonth();
				day = date.getDay();
				expiry = year + "-" + month + "-" + day;
				description = description + serialNo + "," + supply + "," + source + "," + expiry;
				bw.write(description);
				bw.newLine();
				System.out.println(description);
			}
		}
		
		bw.close();
	}
}