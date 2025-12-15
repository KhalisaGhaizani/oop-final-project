package services;

import library.MusicLibrary;
import models.*;
import utils.InputValidator;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SongService {
    private MusicLibrary<Song> songLibrary;
    private ArtistService artistService;
    private AlbumService albumService;
    private Scanner scanner;
    
    public SongService(MusicLibrary<Song> songLibrary, 
                      ArtistService artistService,
                      AlbumService albumService,
                      Scanner scanner) {
        this.songLibrary = songLibrary;
        this.artistService = artistService;
        this.albumService = albumService;
        this.scanner = scanner;
    }
    
    public void addSong() {
        System.out.println("\n--- Add New Song ---");
        
        System.out.print("Enter song title: ");
        String title = scanner.nextLine().trim();
        if (!InputValidator.isValidString(title)) {
            System.out.println("✗ Title cannot be empty!");
            return;
        }
        
        Artist artist = artistService.selectOrCreateArtist();
        if (artist == null) return;
        
        Album album = albumService.selectOrCreateAlbum();
        if (album == null) return;
        
        System.out.println("\nSelect Genre:");
        System.out.println("1. Pop");
        System.out.println("2. Rock");
        System.out.println("3. Jazz");
        System.out.print("Enter genre choice (1-3): ");
        
        String genreChoice = scanner.nextLine().trim();
        Song song = null;
        
        switch (genreChoice) {
            case "1":
                song = new PopSong(title, artist, album);
                break;
            case "2":
                song = new RockSong(title, artist, album);
                break;
            case "3":
                song = new JazzSong(title, artist, album);
                break;
            default:
                System.out.println("✗ Invalid genre choice!");
                return;
        }
        
        songLibrary.add(song);
        artist.addSong(song);
        album.addSong(song);
        
        System.out.println("✓ Song added successfully!");
    }
    
    public void viewAllSongs() {
        List<Song> songs = songLibrary.getAll();
        
        if (songs.isEmpty()) {
            System.out.println("\n✗ No songs in library!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                       ALL SONGS");
        System.out.println("=".repeat(70));
        
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            System.out.printf("%d. %-25s | %-15s | %-15s | %s\n", 
                (i + 1), song.getTitle(), song.getArtist().getName(), 
                song.getAlbum().getName(), song.getGenre());
        }
        System.out.println("=".repeat(70));
    }
    
    public void updateSong() {
        viewAllSongs();
        List<Song> songs = songLibrary.getAll();
        
        if (songs.isEmpty()) return;
        
        System.out.print("\nEnter song number to update: ");
        String input = scanner.nextLine().trim();
        
        Integer index = InputValidator.validateIndex(input, songs.size());
        if (index == null) {
            System.out.println("✗ Invalid song number!");
            return;
        }
        
        System.out.print("Enter new title: ");
        String title = scanner.nextLine().trim();
        if (!InputValidator.isValidString(title)) {
            System.out.println("✗ Title cannot be empty!");
            return;
        }
        
        Song oldSong = songs.get(index);
        Song newSong;
        
        if (oldSong instanceof PopSong) {
            newSong = new PopSong(title, oldSong.getArtist(), oldSong.getAlbum());
        } else if (oldSong instanceof RockSong) {
            newSong = new RockSong(title, oldSong.getArtist(), oldSong.getAlbum());
        } else {
            newSong = new JazzSong(title, oldSong.getArtist(), oldSong.getAlbum());
        }
        
        songLibrary.update(index, newSong);
        System.out.println("✓ Song updated successfully!");
    }
    
    public void deleteSong() {
        viewAllSongs();
        List<Song> songs = songLibrary.getAll();
        
        if (songs.isEmpty()) return;
        
        System.out.print("\nEnter song number to delete: ");
        String input = scanner.nextLine().trim();
        
        Integer index = InputValidator.validateIndex(input, songs.size());
        if (index == null) {
            System.out.println("✗ Invalid song number!");
            return;
        }
        
        songLibrary.remove(index);
        System.out.println("✓ Song deleted successfully!");
    }
    
    public void sortSongs() {
        songLibrary.sort(Comparator.comparing(Song::getTitle));
        System.out.println("✓ Songs sorted by title!");
        viewAllSongs();
    }
    
    public void playSong() {
        viewAllSongs();
        List<Song> songs = songLibrary.getAll();
        
        if (songs.isEmpty()) return;
        
        System.out.print("\nEnter song number to play: ");
        String input = scanner.nextLine().trim();
        
        Integer index = InputValidator.validateIndex(input, songs.size());
        if (index == null) {
            System.out.println("✗ Invalid song number!");
            return;
        }
        
        System.out.println();
        songs.get(index).play();
    }
    
    public MusicLibrary<Song> getLibrary() {
        return songLibrary;
    }
}