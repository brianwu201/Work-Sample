package project1;

/**
Defines the array-like data structure that holds the album collection.
It also provides necessary functions to alter/perform operations on the album.
@author Jeremy Prasad, Brian Wu
*/
public class Collection {
	private Album[] albums;
	private int numAlbums; //number of albums currently in the collection
	
	/**
	No arg constructor: Initializes the collection of albums 
	*/
	public Collection() {
		final int INIT_SIZE = 4;
		albums = new Album[INIT_SIZE];
		numAlbums = 0;
	}
	
	/**
	Finds the index of the specified album.
	@param album the album to check
	@return the index if found, -1 otherwise
	*/
	private int find(Album album) {
		for(int i = 0; i < numAlbums; i++) {
			if(albums[i].equals(album)) {return i;}
		}
		return -1;
	}
	
	/**
	Increases the array structure containing all albums by 4.
	*/
	private void grow() {
		Album[] temp = new Album[numAlbums + 4];
		for(int i = 0; i < numAlbums; i++) {
			temp[i] = albums[i];
		}
		albums = temp;
	}
	
	/**
	Adds the specified album to the array-like data structure and updates numAlbums. 
	@param album the album to add
	@return true if added successfully, false otherwise
	*/
	public boolean add(Album album) {
		if(find(album) != -1) {
			return false; // album is already in the collection
		}
		if(isFull()) {
			grow();
		}
		albums[numAlbums] = album;
		++numAlbums;
		return true;
	}
	
	/**
	Removes the specified album from the list, maintains the order, and updates numAlbums.  If not found, does nothing.
	@param album the album to be deleted
	@return true if successfully deleted, false otherwise
	*/
	public boolean remove(Album album) {
		if(find(album) >= 0) {
			int i = find(album);
			for(; i < numAlbums; i++) {
				albums[i] = albums[i+1];
			}
			//clear the last entry
			albums[i] = null;
			--numAlbums;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	Sets the availability to not available.  Does nothing if album does not exist or is already lent out (unavailable).
	@param album the album to be lent out
	@return true if successfully lent out, false otherwise
	*/
	public boolean lendingOut(Album album) {
		if(find(album) >= 0) {
			if(albums[find(album)].getAvail()) {
				albums[find(album)].setAvail(false);
				return true;
			} else 
				return false;
		} else 
			return false;
	}
	
	/**
	Sets the availability to available.  Does nothing if album does not exist or is already available.
	@param album the album to be returned
	@return true if successfully returned, false otherwise
	*/
	public boolean returnAlbum(Album album) {
		if(find(album) >= 0) {
			if(!albums[find(album)].getAvail()) {
				albums[find(album)].setAvail(true);
				return true;
			} else
				return false;
		} else 
			return false;
	}
	
	/**
	Helper method: determines whether the collection is full or not 
	@return true is full, false otherwise
	*/
	private boolean isFull() {
		if(numAlbums == albums.length) {
			return true;
		} else
			return false;
	}
	
	/**
	Prints out the list AS IS - no sorting.
	*/
	public void print() {
		if(numAlbums == 0) {
			System.out.println("The collection is empty!");
			return;
		}
		System.out.println("*List of albums in the collection.");
		for(int i = 0; i < numAlbums; i++) {
			System.out.println(albums[i].toString());
		}
		System.out.println("*End of list");
	}
	
	/**
	Prints out the list in order of release date (ASCENDING) 
	*/
	public void printByReleaseDate() {
		if(numAlbums == 0) {
			System.out.println("The collection is empty!");
			return;
		}
		Album[] result = albums;  //duplicate the collection to edit directly onto this version
		Album temp = new Album("", "", Genre.Unknown, new Date(), true);  //create a temp Album obj for swapping
		for(int i = 1; i < numAlbums; i++) {
			int j = i;
			while(j > 0 && result[j - 1].getDate().compareTo(result[j].getDate()) > 0) {
				temp = result[j - 1];
				result[j - 1] = result[j];
				result[j] = temp;
				j -= 1;
			}
		}
		System.out.println("*Album collection by the release dates.");
		for(int i = 0; i < numAlbums; i++) {
			System.out.println(result[i].toString());  //print out collection
		}
		System.out.println("*End of list");
	}
	
	/**
	Prints out the list in order of genre (ALPHABETICAL)
	*/
	public void printByGenre() {
		if(numAlbums == 0) {
			System.out.println("The collection is empty!");
			return;
		}
		Album[] result = albums;  //duplicate the collection to edit directly onto this version
		Album temp = new Album("", "", Genre.Unknown, new Date(), true);  //create a temp Album obj for swapping
		for(int i = 1; i < numAlbums; i++) {
			int j = i;
			while(j > 0 && result[j - 1].getGenre().compareTo(result[j].getGenre()) > 0) {
				temp = result[j - 1];
				result[j - 1] = result[j];
				result[j] = temp;
				j -= 1;
			}
		}
		System.out.println("*Album collection by genre.");
		for(int i = 0; i < numAlbums; i++) {
			System.out.println(result[i].toString());  //print out collection
		}
		System.out.println("*End of list");
	}
}