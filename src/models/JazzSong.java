package models;

public class JazzSong extends Song {
	 public JazzSong(String title, Artist artist, Album album) {
	     super(title, artist, album);
	 }
	 
	 @Override
	 public String getGenre() {
	     return "Jazz";
	 }
}