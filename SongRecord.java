public class SongRecord implements Cloneable {
        private String title;
        private String artist;
        private int minutes;
        private int seconds;

        public SongRecord() {
            this.title = "";
            this.artist = "";
            this.minutes = 0;
            this.seconds = 0;
        }

        public SongRecord(String title, String artist, int minutes, int seconds) {
            this.title = title;
            this.artist = artist;
            this.setMinutes(minutes);
            this.setSeconds(seconds);
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtist() {
            return this.artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public int getMinutes() {
            return this.minutes;
        }

        public void setMinutes(int minutes) {
            if (minutes < 0) {
                throw new IllegalArgumentException("Minutes cannot be negative.");
            } else {
                this.minutes = minutes;
            }
        }

        public int getSeconds() {
            return this.seconds;
        }

        public void setSeconds(int seconds) {
            if (seconds >= 0 && seconds <= 59) {
                this.seconds = seconds;
            } else {
                throw new IllegalArgumentException("Invalid song Length.");
            }
        }

        public String toString() {
            return String.format("Title: %s, Artist: %s, Length: %d:%02d", this.title, this.artist, this.minutes, this.seconds);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (obj != null && this.getClass() == obj.getClass()) {
                SongRecord a = (SongRecord)obj;
                return this.minutes == a.minutes && this.seconds == a.seconds && this.title.equals(a.title) && this.artist.equals(a.artist);
            } else {
                return false;
            }
        }

        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException var2) {
                CloneNotSupportedException e = var2;
                throw new InternalError(e);
            }
        }
}

