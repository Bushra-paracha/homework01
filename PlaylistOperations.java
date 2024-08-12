import java.util.Scanner;

public class PlaylistOperations {
    public PlaylistOperations() {
    }

    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        Scanner input = new Scanner(System.in);
        boolean str = true;

        while(str) {
            System.out.println("\nA) Add Song");
            System.out.println("B) Print Songs By Artist");
            System.out.println("G) Get Song");
            System.out.println("R) Remove Song");
            System.out.println("P) Print All Songs");
            System.out.println("S) Size");
            System.out.println("Q) Quit");
            System.out.println("\nSelect a menu option: ");
            String command = input.nextLine().trim().toUpperCase();

            try {
                switch (command) {
                    case "A":
                        addSong(playlist, input);
                        break;
                    case "B":
                        printSongsByArtist(playlist, input);
                        break;
                    case "G":
                        getSong(playlist, input);
                        break;
                    case "R":
                        removeSong(playlist, input);
                        break;
                    case "P":
                        printAllSongs(playlist);
                        break;
                    case "S":
                        printSize(playlist);
                        break;
                    case "Q":
                        str = false;
                        System.out.println("\nProgram terminating normally...");
                        break;
                    default:
                        System.out.println("Invalid command. Please try again.");
                }
            } catch (FullPlaylistException | IllegalArgumentException var7) {
                Exception e = var7;
                System.out.println(e.getMessage());
            }
        }

        input.close();
    }

    private static void addSong(Playlist playlist, Scanner input) throws FullPlaylistException {
        System.out.println("Enter the song title: ");
        String title = input.nextLine();
        System.out.println("Enter the song artist: ");
        String artist = input.nextLine();
        System.out.println("Enter the song length (minutes): ");
        int minutes = Integer.parseInt(input.nextLine());
        System.out.println("Enter the song length (seconds): ");
        int seconds = Integer.parseInt(input.nextLine());
        System.out.print("Enter the position: ");
        int position = Integer.parseInt(input.nextLine());
        SongRecord newSong = new SongRecord(title, artist, minutes, seconds);
        playlist.addSong(newSong, position);
        newSong.setArtist(artist);
        System.out.println("Song Added: " + title + " By " + artist);
    }

    private static void printSongsByArtist(Playlist playlist, Scanner input) {
        System.out.print("Enter the artist: ");
        String artist = input.nextLine();
        SongRecord[] songsByArtist = playlist.getSongsByArtist(artist);
        if (songsByArtist.length == 0) {
            System.out.println("Song#   Title               Artist             Length");
            System.out.println("______________________________________________________");
        } else {
            System.out.println("Song#   Title               Artist             Length");
            System.out.println("_____________________________________________________");

            for(int i = 0; i < songsByArtist.length; ++i) {
                SongRecord a = songsByArtist[i];
                System.out.printf("%-6d %-20s %-20s %d:%02d%n", i + 1, a.getTitle(), a.getArtist(), a.getMinutes(), a.getSeconds());
            }
        }

    }

    private static void getSong(Playlist playlist, Scanner input) {
        System.out.print("Enter the position: ");
        int position = Integer.parseInt(input.nextLine());
        SongRecord song = playlist.getSong(position);
        System.out.println("Song#   Title               Artist             Length");
        System.out.println("_____________________________________________________");
        System.out.printf("%-6d %-20s %-20s %d:%02d%n", position, song.getTitle(), song.getArtist(), song.getMinutes(), song.getSeconds());
    }

    private static void removeSong(Playlist playlist, Scanner input) {
        System.out.print("Enter the position: ");
        int position = Integer.parseInt(input.nextLine());

        try {
            playlist.removeSong(position);
            System.out.println("\"Song removed at position \" + position)");
        } catch (IllegalArgumentException var4) {
            IllegalArgumentException e = var4;
            System.out.println(e.getMessage());
        }

    }

    private static void printAllSongs(Playlist playlist) {
        System.out.println("Printing All Songs: ");
        playlist.printAllSongs();
    }

    private static void printSize(Playlist playlist) {
        System.out.println("There are " + playlist.size() + " song (s) in the current playlist.");
    }
}

