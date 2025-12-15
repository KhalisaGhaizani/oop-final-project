package models;

public class RockSong extends Song {
	 public RockSong(String title, Artist artist, Album album) {
	     super(title, artist, album);
	 }
	 
	 @Override
	 public String getGenre() {
	     return "Rock";
	 }
}
