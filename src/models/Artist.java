package models;

import java.util.ArrayList;
import java.util.List;

public class Artist {
	 private String name;
	 private List<Song> songs;
	 
	 public Artist(String name) {
	     this.name = name;
	     this.songs = new ArrayList<>();
	 }
	 
	 public String getName() {
	     return name;
	 }
	 
	 public List<Song> getSongs() {
	     return songs;
	 }

	 public void addSong(Song song) {
	     songs.add(song);
	 }
}
