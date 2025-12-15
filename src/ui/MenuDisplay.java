package ui;

public class MenuDisplay {
    
    public static void displayWelcome() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   WELCOME TO MUSIC SPOTITI	         ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    public static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("          MAIN MENU");
        System.out.println("=".repeat(40));
        System.out.println("1. Manage Songs");
        System.out.println("2. Manage Artists");
        System.out.println("3. Manage Albums");
        System.out.println("4. Play a Song");
        System.out.println("5. Display All Data");
        System.out.println("0 or EXIT. Exit Application");
        System.out.println("=".repeat(40));
        System.out.print("Enter your choice: ");
    }
    
    public static void displaySongMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          SONG MANAGEMENT");
        System.out.println("-".repeat(40));
        System.out.println("1. Add New Song");
        System.out.println("2. View All Songs");
        System.out.println("3. Update Song");
        System.out.println("4. Delete Song");
        System.out.println("5. Sort Songs by Title");
        System.out.println("0. Back to Main Menu");
        System.out.println("-".repeat(40));
        System.out.print("Enter your choice: ");
    }
    
    public static void displayArtistMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          ARTIST MANAGEMENT");
        System.out.println("-".repeat(40));
        System.out.println("1. Add New Artist");
        System.out.println("2. View All Artists");
        System.out.println("3. View Artist's Songs");
        System.out.println("0. Back to Main Menu");
        System.out.println("-".repeat(40));
        System.out.print("Enter your choice: ");
    }
    
    public static void displayAlbumMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          ALBUM MANAGEMENT");
        System.out.println("-".repeat(40));
        System.out.println("1. Add New Album");
        System.out.println("2. View All Albums");
        System.out.println("3. View Album's Songs");
        System.out.println("0. Back to Main Menu");
        System.out.println("-".repeat(40));
        System.out.print("Enter your choice: ");
    }
    
    public static void displayGoodbye() {
        System.out.println("\n✓ Thank you for using Spotiti!");
        System.out.println("Goodbye! ♪♫");
    }
}