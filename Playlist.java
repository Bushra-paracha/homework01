import java.util.Arrays;

public class Playlist implements Cloneable {
    private static final int MAX_SONGS = 50;
    private SongRecord[] songs = new SongRecord[50];
    private int songNum = 0;

    public Playlist() {
    }

    public Object clone() {
        try {
            Playlist clonedPlaylist = (Playlist)super.clone();
            clonedPlaylist.songs = new SongRecord[50];

            for(int i = 0; i < this.songNum; ++i) {
                if (this.songs[i] != null) {
                    clonedPlaylist.songs[i] = (SongRecord)this.songs[i].clone();
                }
            }

            clonedPlaylist.songNum = this.songNum;
            return clonedPlaylist;
        } catch (CloneNotSupportedException var3) {
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            Playlist playlist = (Playlist)obj;
            return this.songNum == playlist.songNum && Arrays.equals(this.songs, playlist.songs);
        } else {
            return false;
        }
    }

    public int size() {
        return this.songNum;
    }

    public void addSong(SongRecord song, int Position) throws IllegalArgumentException, FullPlaylistException {
        if (Position >= 1 && Position <= this.songNum + 1) {
            if (this.songNum >= this.songs.length) {
                throw new FullPlaylistException("No more room in the Playlist.");
            } else {
                for(int i = this.songNum; i >= Position; --i) {
                    this.songs[i] = this.songs[i - 1];
                }

                this.songs[Position - 1] = song;
                ++this.songNum;
            }
        } else {
            throw new IllegalArgumentException("Invalid position for adding the new song.");
        }
    }

    public void removeSong(int position) throws IllegalArgumentException {
        if (position >= 1 && position <= this.songNum) {
            for(int i = position - 1; i < this.songNum - 1; ++i) {
                this.songs[i] = this.songs[i + 1];
            }

            this.songs[this.songNum - 1] = null;
            --this.songNum;
        } else {
            throw new IllegalArgumentException("No song at position " + position + " to remove.");
        }
    }

    public SongRecord getSong(int position) throws IllegalArgumentException {
        if (position >= 1 && position <= this.songNum) {
            return this.songs[position - 1];
        } else {
            throw new IllegalArgumentException("Position is out of bounds");
        }
    }

    public void printAllSongs() {
        System.out.println("Song#   Title               Artist             Length");
        System.out.println("_____________________________________________________");

        for(int i = 0; i < this.songNum; ++i) {
            SongRecord song = this.songs[i];
            System.out.printf("%-6d %-20s %-20s %d:%02d%n", i + 1, song.getTitle(), song.getArtist(), song.getMinutes(), song.getSeconds());
        }

    }

    public SongRecord[] getSongsByArtist(String artist) {
        SongRecord[] result = new SongRecord[this.songNum];
        int count = 0;

        for(int i = 0; i < this.songNum; ++i) {
            if (this.songs[i].getArtist().equalsIgnoreCase(artist)) {
                result[count] = this.songs[i];
                ++count;
            }
        }

        return (SongRecord[])Arrays.copyOf(result, count);
    }
}

