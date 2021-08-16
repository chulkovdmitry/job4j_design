package ru.job4j.serialization.xml;

public class Album {
        private int numberOfTracks;
        private String name;
        private int minutes;

        public Album(int numberOfTracks, String name, int minutes) {
            this.numberOfTracks = numberOfTracks;
            this.name = name;
            this.minutes = minutes;
        }

        @Override
        public String toString() {
            return "Album{"
                    + "numberOfTracks=" + numberOfTracks
                    + ", name=" + name
                    + ", minutes=" + minutes
                    + '}';
        }

        public int getNumberOfTracks() {
            return numberOfTracks;
        }

        public String getName() {
            return name;
        }

        public int getMinutes() {
            return minutes;
        }
}
