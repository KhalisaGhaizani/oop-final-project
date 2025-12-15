package models;

import java.util.ArrayList;
import java.util.List;


public class Album {
	 private String name;
	 private List<Song> songs;
	 
	 public Album(String name) {
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