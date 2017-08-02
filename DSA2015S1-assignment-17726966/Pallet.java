import io.*;
import java.io.*;
import java.util.*;
public class Pallet implements Serializable
{
	private int serialNumber;
	private String supply;
	private String sourceName;
	private Date expiryDate;
	
	public Pallet()
	{
		serialNumber = 1;
		supply = "";
		sourceName = "";
		expiryDate = new Date(1, 1, 1);
	}
	
	public Pallet (int inSerialNumber, String inSupply, String inSourceName, int year, int month, int day) //Alternate Constructor
	{
		serialNumber = inSerialNumber;
		supply = inSupply;
		sourceName = inSourceName;
		expiryDate = new Date(year, month, day);
	}
	
	public Pallet (Pallet inPallet) //Copy Constructor
	{
		serialNumber = inPallet.getSerialNumber();
		supply = inPallet.getSupply();
		sourceName = inPallet.getSourceName();
		expiryDate = inPallet.getExpiryDate();
	}
	
	public int getSerialNumber() // gets the serial number of a pallet
	{
		return serialNumber;
	}
	
	public void setSerialNumber(int inSerialNumber) // sets the serial number of a pallet
	{
		if (inSerialNumber < 1024 || inSerialNumber > 0)
		{
			serialNumber = inSerialNumber;
		}
	}
	
	public String getSupply() // gets the supply from a pallet
	{
		return supply;
	}
	
	public void setSupply(String inSupply) // sets the supply of a pallet
	{
		supply = inSupply;
	}
	
	public String getSourceName() // gets the source name of a pallet
	{
		return sourceName;
	}
	
	public void setSourceName(String inSourceName) // sets the source name for a pallet
	{
		sourceName = inSourceName;
	}
	
	public Date getExpiryDate() // gets the expiry date in a Date Class form.
	{
		return expiryDate;
	}
	
	public void setExpiryDate(int year, int month, int day) // sets the expiry date given the year, month and day imports
	{
		expiryDate.setYear(year);
		expiryDate.setMonth(month);
		expiryDate.setDay(day);
	}
}