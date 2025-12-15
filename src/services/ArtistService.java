package services;

import library.MusicLibrary;
import models.Artist;
import models.Song;
import utils.InputValidator;
import java.util.List;
import java.util.Scanner;

public class ArtistService {
    private MusicLibrary<Artist> artistLibrary;
    private Scanner scanner;
    
    public ArtistService(MusicLibrary<Artist> artistLibrary, Scanner scanner) {
        this.artistLibrary = artistLibrary;
        this.scanner = scanner;
    }
    
    public void addArtist() {
        System.out.print("\nEnter artist name: ");
        String name = scanner.nextLine().trim();
        
        if (!InputValidator.isValidString(name)) {
            System.out.println("✗ Artist name cannot be empty!");
            return;
        }
        
        Artist artist = new Artist(name);
        artistLibrary.add(artist);
        System.out.println("✓ Artist added successfully!");
    }
    
    public void viewAllArtists() {
        List<Artist> artists = artistLibrary.getAll();
        
        if (artists.isEmpty()) {
            System.out.println("\n✗ No artists in library!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                  ALL ARTISTS");
        System.out.println("=".repeat(50));
        
        for (int i = 0; i < artists.size(); i++) {
            Artist artist = artists.get(i);
            System.out.printf("%d. %-30s (Songs: %d)\n", 
                (i + 1), artist.getName(), artist.getSongs().size());
        }
        System.out.println("=".repeat(50));
    }
    
    public void viewArtistSongs() {
        viewAllArtists();
        List<Artist> artists = artistLibrary.getAll();
        
        if (artists.isEmpty()) return;
        
        System.out.print("\nEnter artist number: ");
        String input = scanner.nextLine().trim();
        
        Integer index = InputValidator.validateIndex(input, artists.size());
        if (index == null) {
            System.out.println("✗ Invalid artist number!");
            return;
        }
        
        Artist artist = artists.get(index);
        List<Song> songs = artist.getSongs();
        
        if (songs.isEmpty()) {
            System.out.println("✗ No songs for this artist!");
            return;
        }
        
        System.out.println("\nSongs by " + artist.getName() + ":");
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            System.out.printf("%d. %s [%s]\n", 
                (i + 1), song.getTitle(), song.getGenre());
        }
    }
    
    public Artist selectOrCreateArtist() {
        List<Artist> artists = artistLibrary.getAll();
        
        if (artists.isEmpty()) {
            System.out.print("No artists found. Enter new artist name: ");
            String name = scanner.nextLine().trim();
            if (!InputValidator.isValidString(name)) {
                System.out.println("✗ Artist name cannot be empty!");
                return null;
            }
            Artist artist = new Artist(name);
            artistLibrary.add(artist);
            return artist;
        }
        
        System.out.println("\nSelect Artist:");
        for (int i = 0; i < artists.size(); i++) {
            System.out.println((i + 1) + ". " + artists.get(i).getName());
        }
        System.out.println((artists.size() + 1) + ". Create New Artist");
        System.out.print("Enter choice: ");
        
        String choice = scanner.nextLine().trim();
        Integer index = InputValidator.validateInteger(choice);
        
        if (index == null) {
            System.out.println("✗ Invalid input!");
            return null;
        }
        
        if (index == artists.size() + 1) {
            System.out.print("Enter new artist name: ");
            String name = scanner.nextLine().trim();
            if (!InputValidator.isValidString(name)) {
                System.out.println("✗ Artist name cannot be empty!");
                return null;
            }
            Artist artist = new Artist(name);
            artistLibrary.add(artist);
            return artist;
        } else if (index > 0 && index <= artists.size()) {
            return artists.get(index - 1);
        } else {
            System.out.println("✗ Invalid choice!");
            return null;
        }
    }
    
    public MusicLibrary<Artist> getLibrary() {
        return artistLibrary;
    }
}