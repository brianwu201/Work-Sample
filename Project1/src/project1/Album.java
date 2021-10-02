package project1;

/**
Defines the abstract data type "Album".
@author Jeremy Prasad, Brian Wu
*/
public class Album {
	private String title;
	private String artist;
	private Genre genre; 	//enum class; Classical, Country, Jazz, Pop, Unknown
	private Date releaseDate;
	private boolean isAvailable;
	
	/**
	Defines a necessary constructor used to initialize a new Album object.
	@param title the title of the album
	@param artist the album's artist
	@param genre the genre type of the album
	@param date the release date for the album
	@param available the album's availability status
	*/
	public Album(String title, String artist, Genre genre, Date date, boolean available) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		releaseDate = date;
		isAvailable = available;
	}
	
	/**
	Checks if the title and artist of two albums are the same.
	Example: x = {"A Song", "Artist", ...}; y = {"A Song", "Artist", ...}; x.equals(y) returns true
	@param obj an object (through inheritance it is an Album object) to be compared with the one being called
	@return true if they are equal, false otherwise
	*/
	@Override
	public boolean equals(Object obj) { 
		if(obj instanceof Album) { //ensures that obj is of type 'Album'
			Album temp = (Album) obj; //cast as 'Album'
			return title.equals(temp.title) && artist.equals(temp.artist);
		}
		else
			return false;
	}
	
	/**
	Returns the textual representation of the album in the following format:
	Title::Artist::Genre::Date::Availability
	Example: Tiranno::Kate Lindsey::Classical::5/28/2020::is available
	@return the string following the above format
	*/
	@Override
	public String toString() { 
		String availability;
		if(isAvailable) {
			availability = "is available";
		} else {
			availability = "is not available";
		}
		return title + "::" + artist + "::" + genre + "::" + releaseDate.getMonth() + "/" + 
		releaseDate.getDay() + "/" + releaseDate.getYear() + "::" + availability;
	}
	
	/**
	Helper method: Sets the album's availability status
	@param setTo the boolean value to set the availability status as
	*/
	public void setAvail(boolean setTo) {
		isAvailable = setTo;
	}
	
	/**
	Helper method: Determines the album's availability 
	@return true if available, false otherwise
	*/
	public boolean getAvail() {
		return isAvailable;
	}
	
	/**
	Helper method: Gets the release date value
	@return Date object containing album's release date
	*/
	public Date getDate() {
		return releaseDate;
	}
	
	/**
	Helper method: Gets the Album title
	@return the album's title as a string 
	*/
	public String getTitle() {
		return title;
	}
	
	/**
	Helper method: Gets the artist's name
	@return the album's artist's name as a string 
	*/
	public String getArtist() {
		return artist;
	}
	
	/**
	Helper method: Gets the album's genre
	@return type Genre containing the album's genre
	*/
	public Genre getGenre() {
		return genre;
	}
}