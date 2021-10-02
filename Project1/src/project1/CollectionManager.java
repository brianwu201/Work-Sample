package project1;

import java.util.Scanner;

/**
Front-end user interface that handles the input and outputs.
@author Brian Wu, Jeremy Prasad
*/
public class CollectionManager {
	
	/**
	Read command lines from the console
	*/
	public void run() {
		System.out.print("Collection Manager starts running.\n");
		Scanner sc = new Scanner(System.in);
		Collection col = new Collection();
		while(true) {
			String command = sc.nextLine();
			String[] part = command.split(",");
			if(part[0].equals("Q")) {
				System.out.print("Collection Manager terminated.");
				break;
			}
			runCommand(part, part[0], col);
		}
		sc.close();
	}
	
	/**
	Running the appropriate functions that the command line calls for.
	@param commandLine the commandLine args stored in an array
	@param command the command to be performed
	@param col the array structure Collection object containing all albums
	*/
	private void runCommand(String[] commandLine, String command, Collection col) {
		switch(command) {
			case "A":
				A(commandLine, col);
				break;
			case "D":
				D(commandLine, col);
				break;
			case "L":
				L(commandLine, col);
				break;
			case "R":
				R(commandLine, col);
				break;
			case "P":
				col.print();
				break;
			case "PD":
				col.printByReleaseDate();
				break;
			case "PG":
				col.printByGenre();
				break;
			default: 
				System.out.print("Invalid command!\n");
		}
	}
	
	/**
	Adds a new album into collection
	@param commandLine the commandLine args stored in an array
	@param col the array structure Collection object conatining all albums
	*/
	private void A(String[] commandLine, Collection col) {
		if(commandLine.length != 5) {
			System.out.print("Invalid command!\n");
			return;
		}
		Date date = new Date(commandLine[4]);
		if(!(date.isValid())) {
			System.out.print("Invalid Date!\n");
			return;
		}
		Genre genre;
		commandLine[3] = commandLine[3].toLowerCase();
		switch(commandLine[3]) {
			case "classical":
				genre = Genre.Classical;
				break;
			case "country":
				genre = Genre.Country;
				break;
			case "jazz":
				genre = Genre.Jazz;
				break;
			case "pop":
				genre = Genre.Pop;
				break;
			default: 
				genre = Genre.Unknown;
		}
		Album temp = new Album(commandLine[1], commandLine[2], genre , date, true);
		if(col.add(temp)) {
			System.out.print(commandLine[1] + "::" + commandLine[2] + "::" + genre + "::" + date.getMonth() + "/" + 
					date.getDay() + "/" + date.getYear() + "::" + "is available >> added.\n");
		}
		else {
			System.out.print(commandLine[1] + "::" + commandLine[2] + "::" + genre + "::" + date.getMonth() + "/" + 
					date.getDay() + "/" + date.getYear() + "::" + "is available >> is already in the collection.\n");
		}
	}
	
	/**
	Remove an album from the collection
	@param commandLine the command line args stored in an array
	@param col the Collection object storing all albums
	*/
	private void D(String[] commandLine, Collection col) {
		if(commandLine.length != 3) {
			System.out.print("Invalid command!\n");
			return;
		}
		Album temp = new Album(commandLine[1], commandLine[2], null , null, true);
		if(col.remove(temp)) {
			System.out.print(commandLine[1] + "::" + commandLine[2] + " >> deleted.\n");
		}
		else {
			System.out.print(commandLine[1] + "::" + commandLine[2] + " >> is not in the collection.\n");
		}
	}
	
	/**
	Lend out an album
	@param commandLine the command line args stored in an array
	@param col the Collection object used to store all albums
	*/
	private void L(String[] commandLine, Collection col) {
		if(commandLine.length != 3) {
			System.out.print("Invalid command!\n");
			return;
		}
		Album temp = new Album(commandLine[1], commandLine[2], null, null, false);
		if(col.lendingOut(temp)) {
			System.out.print(commandLine[1] + "::" + commandLine[2] + " >> lending out and set to not available.\n");
		}
		else {
			System.out.print(commandLine[1] + "::" + commandLine[2] + " >> is not available.\n");
		}
	}
	
	/**
	Return an album
	@param commandLine the command line args stored in an array
	@param col the Collection object used to store all albums
	*/
	private void R(String[] commandLine, Collection col) {
		if(commandLine.length != 3) {
			System.out.print("Invalid command!\n");
			return;
		}
		Album temp = new Album(commandLine[1], commandLine[2], null, null, false);
		if(col.returnAlbum(temp)) {
			System.out.print(commandLine[1] + "::" + commandLine[2] + " >> returning and set to available.\n");
		}
		else {
			System.out.print(commandLine[1] + "::" + commandLine[2] + " >> return cannot be completed.\n");
		}
	}
}