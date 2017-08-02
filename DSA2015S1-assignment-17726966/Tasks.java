import io.*;
import java.io.*;
import java.util.*;
public class Tasks
{
	public Warehouse warehouse;
	
	public Tasks() // Default Constructor
	{
		warehouse = new Warehouse();
	}
	
	public Tasks(int capacity) // Alternate Constructor
	{
		warehouse = new Warehouse(capacity);
	}
	
	public void add(StringTokenizer palletValue, String filename) //Used to add a pallet that came from a CSV file
	{
		StringTokenizer strTok;
		Pallet pallet;
		String serialS, supply, sourceName, expiry, yearS, monthS, dayS;
		int serial, year, month, day;
		
		serialS = palletValue.nextToken();
		serial = Integer.parseInt(serialS);
		supply = palletValue.nextToken();
		sourceName = palletValue.nextToken();
		expiry = palletValue.nextToken();
		
		strTok = new StringTokenizer(expiry, "-");
		year = 0;
		month = 0;
		day = 0;
		while(strTok.hasMoreTokens())
		{
			yearS = strTok.nextToken();
			year = Integer.parseInt(yearS);
			monthS = strTok.nextToken();
			month = Integer.parseInt(monthS);
			dayS = strTok.nextToken();
			day = Integer.parseInt(dayS);
		}
		pallet = new Pallet(serial, supply, sourceName, year, month, day);
		
		int range, operation;
		
		range = 3;
		operation = (int)(Math.random() * range);
		
		if (operation == 0)
		{
			warehouse.insertStack(pallet, filename);
		}
		
		if (operation == 1)
		{
			warehouse.insertQueue(pallet, filename);
		}
		
		if (operation == 2)
		{
			warehouse.insertArray(pallet, filename);
		}		
	} 
	
	public void remove(StringTokenizer palletValue, String filename) // Method used to remove a pallet from stack/queue/array depending on CSV file
	{
		String supply;
		int stackPallet, queuePallet, arrayCount;
		Pallet arrayPallet;
		boolean found;
		
		supply = "";
		
		while (palletValue.hasMoreTokens())
		{
			supply = palletValue.nextToken();
			//System.out.println(supply);
		}
		
		found = false;	

		stackPallet = warehouse.stack.getCount();
		queuePallet = warehouse.queue.getCount();
		arrayCount = warehouse.array.getCount();
		
		
		if(stackPallet != 0)
		{
			if(warehouse.stack.top().getSupply().equals(supply))
			{
				found = true;
				warehouse.removeStack(filename);
			}
		}
		else if (queuePallet != 0)
			{
				if (warehouse.queue.peek().getSupply().equals(supply))
				{
					found = true;
					warehouse.removeQueue(filename);
				}
			}
			else if (arrayCount != 0)
				{
					int ii = 0;
					while(found == false)
					{
						arrayPallet = warehouse.array.getArray(ii);
						if(arrayPallet != null)
						{
							if (arrayPallet.getSupply().equals(supply))
							{
								found = true;
								warehouse.removeArray(ii, filename);
							}
						}
						if(ii < warehouse.array.getCount())
						{
							ii++;
						}
					}
				}
		if(found == false)
		{
			System.out.println("NOT FOUND OR STUCK");
		}
	}
	
	public void search(StringTokenizer palletValue) // Puts through a CSV file that was tokenized in processLine and starts to search
	{
		String thisToken;
		int count;
		boolean isNumber = false;
		boolean found = false;
		Pallet palletVal;
		
		count = 0;
		while(palletValue.hasMoreTokens())
		{
			thisToken = palletValue.nextToken();
			count++;
			
			try {
				Integer.parseInt(thisToken);
				isNumber = true;
			} catch (NumberFormatException ne) {
				isNumber = false;
				}
			
			/** THIS BIT IS FOR IF THE TOKEN IS A SERIAL	** 
			**					NUMBER						**
			**												**
			**												**
			** ******************************************** **/
			
			if(isNumber == true)
			{
				bubbleSortSerial(Integer.parseInt(thisToken), warehouse.stack.stack);
				bubbleSortSerial(Integer.parseInt(thisToken), warehouse.queue.queue);
				bubbleSortSerial(Integer.parseInt(thisToken), warehouse.array.array);
				for (int ii = 0; ii < warehouse.stack.length(); ii++)
				{
					palletVal = warehouse.stack.getStack(ii);
					if(palletVal != null)
					{
						if(palletVal.getSerialNumber() == Integer.parseInt(thisToken))
						{
							printPallet(palletVal, 0, ii);	
							found = true;
						}
					}
				}
				for (int ii = 0; ii < warehouse.queue.length(); ii++)
				{
					Date expiry;
					palletVal = warehouse.queue.getQueue(ii);
					if(palletVal != null)
					{
						if(palletVal.getSerialNumber() == Integer.parseInt(thisToken))
						{
							printPallet(palletVal, 1, ii);	
							found = true;
						}
					}
				}
				for (int ii = 0; ii < warehouse.array.length(); ii++)
				{
					Date expiry;
					palletVal = warehouse.array.getArray(ii);
					if(palletVal != null)
					{
						if(palletVal.getSerialNumber() == Integer.parseInt(thisToken))
						{
							expiry = palletVal.getExpiryDate();
							printPallet(palletVal, 2, ii);		
							found = true;							
						}
					}
				}
			}
			
			/** 	THIS BIT IS FOR IF THE TOKEN IS NOT A 	** 
			**					SERIAL NUMBER				**
			**												**
			**												**
			** ******************************************** **/
			
			if(isNumber == false)
			{
				boolean isDate = false;
				int year, month, day;
				day = 0;
				month = 0;
				year = 0;
				
				try{ // Tries to tokenize the String to see if it is an expiry date or a supply/source name
					StringTokenizer strTok;
					strTok = new StringTokenizer(thisToken, "-");
					while(strTok.hasMoreTokens())
					{
						year = Integer.parseInt(strTok.nextToken());
						month = Integer.parseInt(strTok.nextToken());
						day = Integer.parseInt(strTok.nextToken());
					}
					isDate = true;
				}
				catch(Exception e){
					isDate = false;
				}
				
				/** 	THIS BIT IS FOR IF THE TOKEN IS AN	 	** 
				**					EXPIRY DATE					**
				**												**
				**												**
				** ******************************************** **/
				
				if(isDate == true)
				{
					bubbleSortExpiry(year, warehouse.stack.stack);
					bubbleSortExpiry(year, warehouse.queue.queue);
					bubbleSortExpiry(year, warehouse.array.array);
					
					for (int ii = 0; ii < warehouse.stack.length(); ii++)
					{
						palletVal = warehouse.stack.getStack(ii);
						if(palletVal != null)
						{
							if(palletVal.getExpiryDate().getYear() <= year)
							{
								if(palletVal.getExpiryDate().getMonth() <= month)
								{
									if(palletVal.getExpiryDate().getDay() <= day)
									{
										printPallet(palletVal, 0, ii);
										found = true;
									}
									printPallet(palletVal, 0, ii);
									found = true;
								}
								printPallet(palletVal, 0, ii);
								found = true;
							}
						}
					}
					for (int ii = 0; ii < warehouse.queue.length(); ii++)
					{
						palletVal = warehouse.queue.getQueue(ii);
						if(palletVal != null)
						{
							if(palletVal.getExpiryDate().getYear() <= year)
							{
								if(palletVal.getExpiryDate().getMonth() <= month)
								{
									if(palletVal.getExpiryDate().getDay() <= day)
									{
										printPallet(palletVal, 1, ii);
										found = true;
									}
									printPallet(palletVal, 1, ii);
									found = true;
								}
								printPallet(palletVal, 1, ii);
								found = true;
							}
						}
					}
					for (int ii = 0; ii < warehouse.array.length(); ii++)
					{
						palletVal = warehouse.array.getArray(ii);
						if(palletVal != null)
						{
							if(palletVal.getExpiryDate().getYear() <= year)
							{
								if(palletVal.getExpiryDate().getMonth() <= month)
								{
									if(palletVal.getExpiryDate().getDay() <= day)
									{
										printPallet(palletVal, 2, ii);
										found = true;
									}
									printPallet(palletVal, 2, ii);
									found = true;
								}
								printPallet(palletVal, 2, ii);
								found = true;
							}
						}
					}
				}
				
				/** 	THIS BIT IS FOR IF THE TOKEN IS A	 	** 
				**			SUPPLY OR A SOURCE NAME				**
				**												**
				**												**
				** ******************************************** **/
				
				if(isDate == false)
				{
					bubbleSortSuppSource(thisToken, warehouse.stack.stack);
					bubbleSortSuppSource(thisToken, warehouse.queue.queue);
					bubbleSortSuppSource(thisToken, warehouse.array.array);

					for (int ii = 0; ii < warehouse.stack.length(); ii++)
					{
						palletVal = warehouse.stack.getStack(ii);
						if(palletVal != null)
						{
							if(palletVal.getSupply().equals(thisToken) || palletVal.getSourceName().equals(thisToken))
							{
								printPallet(palletVal, 0, ii);
								found = true;
							}
						}
					}
					for (int ii = 0; ii < warehouse.queue.length(); ii++)
					{
						palletVal = warehouse.queue.getQueue(ii);
						if(palletVal != null)
						{
							if(palletVal.getSupply().equals(thisToken) || palletVal.getSourceName().equals(thisToken))
							{
								printPallet(palletVal, 1, ii);
								found = true;
							}
						}
					}
					for (int ii = 0; ii < warehouse.array.length(); ii++)
					{
						palletVal = warehouse.array.getArray(ii);
						if(palletVal != null)
						{
							if(palletVal.getSupply().equals(thisToken) || palletVal.getSourceName().equals(thisToken))
							{
								printPallet(palletVal, 2, ii);
								found = true;
							}
						}
					}
					
				}
			}
		}
		if (found == false)
		{
			System.out.println("NOT FOUND");
		}
	}
	
	
	public static void bubbleSortSerial(int numberToVal, Pallet[] A) // Method is used to sort an array by passing through a serial number and organises from ascending order of serial numbers
    {
		Pallet temp;
		for(int pass = 0; pass < (A.length - 1) - 1; pass++)
		{
			for(int ii = 0; ii < (A.length - 1 - pass); ii++)
			{
				if(A[ii] != null)
				{
					if(A[ii].getSerialNumber() < numberToVal)
					{
						if(A[ii].getSerialNumber() > A[ii+1].getSerialNumber())
						{
							temp = A[ii];
							A[ii] = A[ii+1];
							A[ii+1] = temp;
						}
					}
				}
				
			}
		}
    }
	
	public static void bubbleSortSuppSource(String stringToVal, Pallet[] A) // Method is used to sort an array by passing through a string to push all indexes that contain the key word towards one end
    {
		Pallet temp;
		for(int pass = 0; pass < (A.length - 1) - 1; pass++)
		{
			for(int ii = 0; ii < (A.length - 1 - pass); ii++)
			{
				if(A[ii] != null)
				{
					if(A[ii].getSupply() == stringToVal || A[ii].getSourceName() == stringToVal)
					{
						temp = A[ii];
						A[ii] = A[ii-1];
						A[ii-1] = temp;
					}
				}
				
			}
		}
    }
	
	
	public static void bubbleSortExpiry(int numberToVal, Pallet[] A) // Method used to push all array elements with the lowest year towards one end
    {
		Pallet temp;
		for(int pass = 0; pass < (A.length - 1) - 1; pass++)
		{
			for(int ii = 0; ii < (A.length - 1 - pass); ii++)
			{
				if(A[ii] != null)
				{
					if(A[ii].getExpiryDate().getYear() > numberToVal)
					{
						temp = A[ii];
						A[ii] = A[ii+1];
						A[ii+1] = temp;
					}
				}
			}
		}
    }
		
	public void processLine(String csvRow, String filename) // Method used to determine what actions to take depending on the input CSV file
	{
		String thisToken = null;
		StringTokenizer strTok;
		
		strTok = new StringTokenizer(csvRow, ",");
		
		while (strTok.hasMoreTokens())
		{
			thisToken = strTok.nextToken();
			if(thisToken.equals("a"))
			{
				add(strTok, filename);
			}
			
			if(thisToken.equals("r"))
			{
				remove(strTok, filename);
			}
			
			if(thisToken.equals("s"))
			{
				search(strTok);
			}	
		}
	}
	
	public void processWarehouse(String csvRow, String d, String r, String y)
	{
		String thisToken = null;
		String thisToken2 = null;
		String supply, sourceName, expiryDate;
		StringTokenizer strTok, expiryDateTok, dTok, rTok, yTok;
		int year, month, day, serialNumber, geoSerial;
		Pallet pallet;
		String token = null;
		
		strTok = new StringTokenizer(csvRow, ",");
		while(strTok.hasMoreTokens())
		{
			serialNumber = Integer.parseInt(strTok.nextToken());
			supply = strTok.nextToken();
			sourceName = strTok.nextToken();
			expiryDate = strTok.nextToken();
			
			dTok = new StringTokenizer(d, ",");
			while(dTok.hasMoreTokens())
			{
				try{
					token = dTok.nextToken();
					if (token.equals("d"))
					{
						token = dTok.nextToken();
					}
				}
				catch(NoSuchElementException e){}
				if (token != null)
				{
					geoSerial = Integer.parseInt(token);
					if(geoSerial == serialNumber)
					{
						expiryDateTok = new StringTokenizer(expiryDate, "-");
						year = Integer.parseInt(expiryDateTok.nextToken());
						month = Integer.parseInt(expiryDateTok.nextToken());
						day = Integer.parseInt(expiryDateTok.nextToken());
						pallet = new Pallet(serialNumber, supply, sourceName, year, month, day);
						warehouse.insertStack(pallet);
					}
				}
				token = null;
			}
			
			rTok = new StringTokenizer(r, ",");
			while(rTok.hasMoreTokens())
			{
				try{
					token = rTok.nextToken();
					if (token.equals("r"))
					{
						token = rTok.nextToken();
					}
				}
				catch(NoSuchElementException e){}
				if(token != null)
				{
					geoSerial = Integer.parseInt(token);
					if(geoSerial == serialNumber)
					{
						expiryDateTok = new StringTokenizer(expiryDate, "-");
						year = Integer.parseInt(expiryDateTok.nextToken());
						month = Integer.parseInt(expiryDateTok.nextToken());
						day = Integer.parseInt(expiryDateTok.nextToken());
						pallet = new Pallet(serialNumber, supply, sourceName, year, month, day);
						warehouse.insertQueue(pallet);
					}
				}
				token = null;
			}
			
			yTok = new StringTokenizer(y, ",");
			while(yTok.hasMoreTokens())
			{
				try{
					token = yTok.nextToken();
					if (token.equals("y"))
					{
						token = yTok.nextToken();
					}
				}
				catch(NoSuchElementException e){}
				if(token != null)
				{
					geoSerial = Integer.parseInt(token);
					if(geoSerial == serialNumber)
					{
						expiryDateTok = new StringTokenizer(expiryDate, "-");
						year = Integer.parseInt(expiryDateTok.nextToken());
						month = Integer.parseInt(expiryDateTok.nextToken());
						day = Integer.parseInt(expiryDateTok.nextToken());
						pallet = new Pallet(serialNumber, supply, sourceName, year, month, day);
						warehouse.insertArray(pallet);
					}
				}
				token = null;
			}		
		}
		
	}
	
	public void readWarehouse(String filename) //reads the warehouse description file input
	{
		FileInputStream fileStrm = null;
		InputStreamReader rdr;
		BufferedReader bufRdr;
		int lineNum;
		String line, d, r, y;
		try
		{
			fileStrm = new FileInputStream(filename);
			rdr = new InputStreamReader(fileStrm);
			bufRdr = new BufferedReader(rdr);
			
			d = bufRdr.readLine();
			r = bufRdr.readLine();
			y = bufRdr.readLine();
			bufRdr.readLine();
			line = bufRdr.readLine();
			while(line != null)
			{
				processWarehouse(line, d, r, y);
				line = bufRdr.readLine();
			}
			
			fileStrm.close();
		}
		catch (IOException e){
			if (fileStrm != null) {
				try { fileStrm.close();} catch (IOException ex2) {}
			}
			System.out.println("Error in file processing: " + e.getMessage());
		}
	}
	
	public void readFile(String filename) //reads the first line of a file given the name as an import.
	{
		FileInputStream fileStrm = null;
		InputStreamReader rdr;
		BufferedReader bufRdr;
		int lineNum;
		String line;
		try
		{
			fileStrm = new FileInputStream(filename);
			rdr = new InputStreamReader(fileStrm);
			bufRdr = new BufferedReader(rdr);
			
			line = bufRdr.readLine();
			while(line != null)
			{
				processLine(line, filename);
				line = bufRdr.readLine();
			}
			
			fileStrm.close();
		}
		catch (IOException e){
			if (fileStrm != null) {
				try { fileStrm.close();} catch (IOException ex2) {}
			}
			System.out.println("Error in file processing: " + e.getMessage());
		}
	}
	
	public void printPallet(Pallet pallet, int bank, int index) // Method to print the contents of an imported pallet to the screen
	{
		System.out.println(bank + "," + index + "," + pallet.getSerialNumber() + "," + pallet.getSupply() + "," + pallet.getSourceName() + "," + pallet.getExpiryDate().getYear() + "-" + pallet.getExpiryDate().getMonth() + "-" + pallet.getExpiryDate().getDay());
	}
}