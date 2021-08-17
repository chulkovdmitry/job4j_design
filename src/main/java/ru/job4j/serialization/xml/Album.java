package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "album")
public class Album {
        @XmlAttribute
        private int numberOfTracks;
        @XmlAttribute
        private String name;
        @XmlAttribute
        private int minutes;

        public Album() {
        }

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
