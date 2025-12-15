package controllers;

import library.MusicLibrary;
import models.*;
import services.*;
import ui.MenuDisplay;
import java.util.Scanner;

public class MainController {
    private Scanner scanner;
    private SongService songService;
    private ArtistService artistService;
    private AlbumService albumService;
    
    public MainController() {
        scanner = new Scanner(System.in);
        
        MusicLibrary<Song> songLibrary = new MusicLibrary<>();
        MusicLibrary<Artist> artistLibrary = new MusicLibrary<>();
        MusicLibrary<Album> albumLibrary = new MusicLibrary<>();
        
        artistService = new ArtistService(artistLibrary, scanner);
        albumService = new AlbumService(albumLibrary, scanner);
        songService = new SongService(songLibrary, artistService, albumService, scanner);
    }
    
    public void start() {
        MenuDisplay.displayWelcome();
        
        boolean running = true;
        
        while (running) {
            MenuDisplay.displayMainMenu();
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    manageSongs();
                    break;
                case "2":
                    manageArtists();
                    break;
                case "3":
                    manageAlbums();
                    break;
                case "4":
                    songService.playSong();
                    break;
                case "5":
                    displayAllData();
                    break;
                case "0":
                case "EXIT":
                    running = false;
                    MenuDisplay.displayGoodbye();
                    break;
                default:
                    System.out.println("✗ Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private void manageSongs() {
        boolean back = false;
        
        while (!back) {
            MenuDisplay.displaySongMenu();
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    songService.addSong();
                    break;
                case "2":
                    songService.viewAllSongs();
                    break;
                case "3":
                    songService.updateSong();
                    break;
                case "4":
                    songService.deleteSong();
                    break;
                case "5":
                    songService.sortSongs();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("✗ Invalid choice!");
            }
        }
    }
    
    private void manageArtists() {
        boolean back = false;
        
        while (!back) {
            MenuDisplay.displayArtistMenu();
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    artistService.addArtist();
                    break;
                case "2":
                    artistService.viewAllArtists();
                    break;
                case "3":
                    artistService.viewArtistSongs();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("✗ Invalid choice!");
            }
        }
    }
    
    private void manageAlbums() {
        boolean back = false;
        
        while (!back) {
            MenuDisplay.displayAlbumMenu();
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    albumService.addAlbum();
                    break;
                case "2":
                    albumService.viewAllAlbums();
                    break;
                case "3":
                    albumService.viewAlbumSongs();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("✗ Invalid choice!");
            }
        }
    }
    
    private void displayAllData() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    LIBRARY SUMMARY");
        System.out.println("=".repeat(70));
        
        System.out.println("Total Songs: " + songService.getLibrary().getAll().size());
        System.out.println("Total Artists: " + artistService.getLibrary().getAll().size());
        System.out.println("Total Albums: " + albumService.getLibrary().getAll().size());
        
        songService.viewAllSongs();
        artistService.viewAllArtists();
        albumService.viewAllAlbums();
    }
}