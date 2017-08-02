import io.*;
import java.io.*;
public class Date implements Serializable
{
	private int year;
	private int month;
	private int day;
	
	public Date(int inYear, int inMonth, int inDay) // Alternate Constructor
	{
		year = inYear;
		month = inMonth;
		day = inDay;
	}
	
	public void setYear(int inYear) // sets the year for a date object
	{
		if (inYear < 0 || inYear > 9999)
		{
			throw new IllegalArgumentException("Year out of range");
		}
		year = inYear;
	}
	
	public void setMonth(int inMonth) // sets the month for a date object
	{
		if (inMonth > 12 || inMonth < 0)
		{
			throw new IllegalArgumentException("Month out of range");
		}
		month = inMonth;
	}
	
	public void setDay(int inDay) // sets the day for a date object
	{
		switch(getMonth())
		{
			case 1: case 3: case 5: case 7: case 10: case 12:
				if (inDay < 0 || inDay > 31)
				{
					throw new IllegalArgumentException("Day out of range of 1 to 31");
				}
				day = inDay;
				break;
			
			case 2:
				if(getYear() % 4 == 0 && getYear() % 100 == 0 && getYear() % 400 == 0)
				{
					if (inDay < 0 || inDay > 29)
					{
					throw new IllegalArgumentException("Day out of range of 1 to 29");
					}
					day = inDay;
				}
				else
				{
					if (inDay < 0 || inDay > 28)
					{
					throw new IllegalArgumentException("Day out of range of 1 to 30");
					}
					day = inDay;
				}
				break;
			
			default:
				if (inDay < 0 || inDay > 30)
				{
					throw new IllegalArgumentException("Day out of range of 1 to 31");
				}
				day = inDay;
				break;	
		}	
	}
	
	public int getYear() // returns the value of year
	{
		return year;
	}
	
	public int getMonth() // returns the value of month
	{
		return month;
	}
	
	public int getDay() // returns the value of day
	{
		return day;
	}
	
}