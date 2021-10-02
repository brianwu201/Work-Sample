package project1;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
Handles the dates: initialization, validation, and formatting.
@author Brian Wu, Jeremy Prasad
*/
public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;
	
	/**
	1 arg Constructor: Creates a Date object from the specified date. 
	@param date in string form (mm/dd/yyyy)
	*/
	public Date(String date) { //take "mm/dd/yyyy" and create a Date object
		StringTokenizer str = new StringTokenizer(date, "/");
		month = Integer.parseInt(str.nextToken());
		day = Integer.parseInt(str.nextToken());
		year = Integer.parseInt(str.nextToken());
	} 
	
	/**
	No arg Constructor: Creates a Date object with today's date.
	*/
	public Date() { //create an object with today's date (see Calendar class)
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DATE);
		year = cal.get(Calendar.YEAR);
	} 
	
	/**
	Helper method: Returns the day of a given date
	@return day an integer representing the day
	*/
	public int getDay() {
		return day;
	}
	
	/**
	Helper method: Returns the month of a given date
	@return month an integer representing the month
	*/
	public int getMonth() {
		return month;
	}
	
	/**
	Helper method: Returns the year of a given date
	@return year an integer representing the year
	*/
	public int getYear() {
		return year;
	}
	
	
	/**
	Checks to see if a Date object is valid (earlier than 1980's or later than today is invalid).
	@return true if valid, false otherwise
	*/
	public boolean isValid() {
		
		final int QUADRENNIAL = 4;
		final int CENTENNIAL = 100;
		final int QUATERCENTENNIAL = 400;
		final int THE_EIGHTYS = 1980;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		
		if(year < THE_EIGHTYS || year > cal.get(Calendar.YEAR) || day < 1 || month < 1 || month > 12) { 
			return false;
		}
		
		if(month >= cal.get(Calendar.MONTH) && year == cal.get(Calendar.YEAR)) { //check if month/day is over today
			if(day > cal.get(Calendar.DATE) || month > cal.get(Calendar.MONTH)) {
				return false;
			}
		}
		
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if(day <= 31) {
				return true;
			}
			return false;
		}
		
		else if(month == 4 || month == 6 || month == 9 || month == 11) {
			if(day <=30) {
				return true;
			}
			return false;
		}
		else{	// check for leap year if it is February
			if(day <= 28) {
				return true;
			}
			else if(day == 29) {
				if(year % QUADRENNIAL == 0) {
					if(year % CENTENNIAL == 0) {
						if(year % QUATERCENTENNIAL == 0) {
							return true;
						}
						return false;
					}
					return true;
				}
				return false;
			}
			return false;
		}
	}
	
	/**
	Compares two dates and returns either 1, 0, or -1 depending on how recent the first release date
	compared to the other is.
	Example: x = 08/03/2003; y = 09/03/2003; x.compareTo(y) returns -1 since 08/03/2003 is not as
	recent as 09/03/2003.
	@param date the Date object to be compared to
	@return 1, 0, or -1 depending on how recent the first release date compared to the other is.
	*/
	@Override
	public int compareTo(Date date) {
		
		if(year > date.year) { //check if year is more recent
			return 1;
		}
		else if(year == date.year) { //check if year is the same
			if(month >= date.month) {
				if(day <= date.day && month == date.month) {
					if(day < date.day) {
						return -1;
					}
					return 0;
				}
				return 1;
			}
			return -1;
		}
		else { //year is older
			return -1;
		}
	}
	
	/**
	Testbed main for the Date class
	@param args the commandline arguments stored as an array
	*/
	public static void main(String[] args) {
		
		//test case #1, a date with year before 1980 is invalid
		Date date = new Date("10/25/1825");
		boolean expectedResult = false;
		boolean result = date.isValid();
		System.out.print("Test case #1: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #2, date that is in the future
		date = new Date("10/25/2023");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #2: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #3, date with a month greater than 12
		date = new Date("13/21/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #3: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #4, date with a month less than 1
		date = new Date("0/21/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #4: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #5, date with day less than 1
		date = new Date("10/0/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #5: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #6, date with a day above 31 in January
		date = new Date("1/32/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #6: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #7, date with a day above 31 in March
		date = new Date("3/32/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #7: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #8, date with a day above 30 in April
		date = new Date("4/31/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #8: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #9, date with a day above 31 in May
		date = new Date("5/32/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #9: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #10, date with a day above 30 in June
		date = new Date("6/31/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #10: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #11, date with a day above 31 in July
		date = new Date("7/32/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #11: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #12, date with a day above 31 in August
		date = new Date("8/32/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #12: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #13, date with a day above 30 in September
		date = new Date("9/31/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #13: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #14, date with a day above 31 in October
		date = new Date("10/32/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #14: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #15, date with a day above 30 in November
		date = new Date("11/31/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #15: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #16, date with a day above 31 in December
		date = new Date("12/32/2001");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #16: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #17, date that is on February 29, but not on a leap year.
		date = new Date("2/29/2021");
		expectedResult = false;
		result = date.isValid();
		System.out.print("Test case #17: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
			
		//test case #18, date that is on February 29, and on a leap year.
		date = new Date("2/29/2020");
		expectedResult = true;
		result = date.isValid();
		System.out.print("Test case #18: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #19, date that is January 1, 2000.
		date = new Date("1/1/2000");
		expectedResult = true;
		result = date.isValid();
		System.out.print("Test case #19: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
		
		//test case #20, date that is one day within the valid start period.
		date = new Date("1/1/1980");
		expectedResult = true;
		result = date.isValid();
		System.out.print("Test case #20: ");
		if(result == expectedResult) {
			System.out.println("Pass.");
		}
		else {
			System.out.println("Fail.");
		}
	}
}