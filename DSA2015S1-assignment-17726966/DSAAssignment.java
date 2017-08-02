import io.*;
import java.io.*;
public class DSAAssignment
{
	public static void main(String [] args)
	{
		String filename, warehouseFile, taskFile;
		Tasks task;
		boolean exit = false;
		
		warehouseFile = args[0];
		taskFile = args[1];
		
		task = new Tasks();
		
		task.readWarehouse(warehouseFile);
		System.out.println("");
		task.readFile(taskFile);
		/* try{
			task.warehouse.warehouseDescription(warehouseFile);
		}
		catch (IOException e){} */
		
		System.exit(0);
	}
}