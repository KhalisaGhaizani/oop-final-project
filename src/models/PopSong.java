package models;

public class PopSong extends Song {
	 public PopSong(String title, Artist artist, Album album) {
	     super(title, artist, album);
	 }
	 
	 @Override
	 public String getGenre() {
	     return "Pop";
	 }
}

