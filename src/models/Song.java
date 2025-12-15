package models;

import interfaces.Playable;

public abstract class Song implements Playable {
	private String title;
	private Artist artist;
	private Album album;
	
	public Song(String title, Artist artist, Album album) {
	   this.title = title;
	   this.artist = artist;
	   this.album = album;
	}
	
	public String getTitle() {
	   return title;
	}
	
	public Artist getArtist() {
	   return artist;
	}
	
	public Album getAlbum() {
	   return album;
	}
	
	public abstract String getGenre();
	
	@Override
	public void play() {
	   System.out.println("♪ Now playing: " + title + " by " + artist.getName() + 
	                    " [" + getGenre() + "]");
	}
}