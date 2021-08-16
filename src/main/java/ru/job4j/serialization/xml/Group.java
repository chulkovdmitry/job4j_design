package ru.job4j.serialization.xml;

import java.util.Arrays;

public class Group {
    private String name;
    private Album album;
    private boolean active;
    private int yearOfCreation;
    private String[] crew;

    public Group(String name, Album album,
               boolean active, int yearOfCreation, String[] crew) {
        this.name = name;
        this.album = album;
        this.active = active;
        this.yearOfCreation = yearOfCreation;
        this.crew = crew;
    }

    public String getName() {
        return name;
    }

    public Album getAlbum() {
        return album;
    }

    public boolean isActive() {
        return active;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public String[] getCrew() {
        return crew;
    }

    @Override
    public String toString() {
        return "Group{"
                + "name='" + name + '\''
                + ", album=" + album
                + ", active=" + active
                + ", yearOfCreation=" + yearOfCreation
                + ", crew=" + Arrays.toString(crew)
                + '}';
    }

    public static void main(String[] args) {
        final Group groupInfo = new Group(
                "Fury",
                new Album(14, "V", 82),
                true,
                1983,
                new String[]{"Jim", "Fred", "Jack"}
        );
    }
}
