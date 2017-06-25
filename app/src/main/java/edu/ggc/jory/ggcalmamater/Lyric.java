package edu.ggc.jory.ggcalmamater;

/**
 * Created by Jory on 4/16/17.
 */

public class Lyric {
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    int time;
    String lyric;

    public Lyric(int time, String lyric) {
        this.time = time;
        this.lyric = lyric;
    }
}
