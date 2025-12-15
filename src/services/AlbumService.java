package services;

import library.MusicLibrary;
import models.Album;
import models.Song;
import utils.InputValidator;
import java.util.List;
import java.util.Scanner;

public class AlbumService {
    private MusicLibrary<Album> albumLibrary;
    private Scanner scanner;
    
    public AlbumService(MusicLibrary<Album> albumLibrary, Scanner scanner) {
        this.albumLibrary = albumLibrary;
        this.scanner = scanner;
    }
    
    public void addAlbum() {
        System.out.print("\nEnter album name: ");
        String name = scanner.nextLine().trim();
        
        if (!InputValidator.isValidString(name)) {
            System.out.println("✗ Album name cannot be empty!");
            return;
        }
        
        Album album = new Album(name);
        albumLibrary.add(album);
        System.out.println("✓ Album added successfully!");
    }
    
    public void viewAllAlbums() {
        List<Album> albums = albumLibrary.getAll();
        
        if (albums.isEmpty()) {
            System.out.println("\n✗ No albums in library!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                  ALL ALBUMS");
        System.out.println("=".repeat(50));
        
        for (int i = 0; i < albums.size(); i++) {
            Album album = albums.get(i);
            System.out.printf("%d. %-30s (Songs: %d)\n", 
                (i + 1), album.getName(), album.getSongs().size());
        }
        System.out.println("=".repeat(50));
    }
    
    public void viewAlbumSongs() {
        viewAllAlbums();
        List<Album> albums = albumLibrary.getAll();
        
        if (albums.isEmpty()) return;
        
        System.out.print("\nEnter album number: ");
        String input = scanner.nextLine().trim();
        
        Integer index = InputValidator.validateIndex(input, albums.size());
        if (index == null) {
            System.out.println("✗ Invalid album number!");
            return;
        }
        
        Album album = albums.get(index);
        List<Song> songs = album.getSongs();
        
        if (songs.isEmpty()) {
            System.out.println("✗ No songs in this album!");
            return;
        }
        
        System.out.println("\nSongs in " + album.getName() + ":");
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            System.out.printf("%d. %s by %s [%s]\n", 
                (i + 1), song.getTitle(), song.getArtist().getName(), 
                song.getGenre());
        }
    }
    
    public Album selectOrCreateAlbum() {
        List<Album> albums = albumLibrary.getAll();
        
        if (albums.isEmpty()) {
            System.out.print("No albums found. Enter new album name: ");
            String name = scanner.nextLine().trim();
            if (!InputValidator.isValidString(name)) {
                System.out.println("✗ Album name cannot be empty!");
                return null;
            }
            Album album = new Album(name);
            albumLibrary.add(album);
            return album;
        }
        
        System.out.println("\nSelect Album:");
        for (int i = 0; i < albums.size(); i++) {
            System.out.println((i + 1) + ". " + albums.get(i).getName());
        }
        System.out.println((albums.size() + 1) + ". Create New Album");
        System.out.print("Enter choice: ");
        
        String choice = scanner.nextLine().trim();
        Integer index = InputValidator.validateInteger(choice);
        
        if (index == null) {
            System.out.println("✗ Invalid input!");
            return null;
        }
        
        if (index == albums.size() + 1) {
            System.out.print("Enter new album name: ");
            String name = scanner.nextLine().trim();
            if (!InputValidator.isValidString(name)) {
                System.out.println("✗ Album name cannot be empty!");
                return null;
            }
            Album album = new Album(name);
            albumLibrary.add(album);
            return album;
        } else if (index > 0 && index <= albums.size()) {
            return albums.get(index - 1);
        } else {
            System.out.println("✗ Invalid choice!");
            return null;
        }
    }
    
    public MusicLibrary<Album> getLibrary() {
        return albumLibrary;
    }
}
