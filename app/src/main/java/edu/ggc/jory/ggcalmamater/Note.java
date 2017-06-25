package edu.ggc.jory.ggcalmamater;

/**
 * Created by Jory on 4/6/17.
 */

public class Note {

    private double frequency;
    private String noteString;

    public Note(double frequency, String noteString) {
        this.frequency = frequency;
        this.noteString = noteString;
    }


    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public String getNoteString() {
        return noteString;
    }

    public void setNoteString(String noteString) {
        this.noteString = noteString;
    }
}
