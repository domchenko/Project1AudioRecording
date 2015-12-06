/*
 * Track
 *
 * Version 1
 */
package audioproject.track;

import audioproject.artist.Artist;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides music track
 * 
 * @author Tanya Domchenko
 * @version 1, 08 Nov 2015
 */
public class Track implements Comparable<Track> {
    private String title;           // name of the track
    private List<Artist> artists;   // performers
    // album
    private MusicStyle style;       // genre
    private int length;             // duration in seconds
    private int year;               // released in
    private int bitRate;            // bits per second

    /**
     * Class constructor specifying track title, performer, style, duration, 
     * year and bit rate.
     * 
     * @param title     track name
     * @param artist    performer: singer, band or musician
     * @param style     music style
     * @param length    track duration in seconds
     * @param year      year of release
     * @param bitRate   bits per second
     */
    public Track( String title, Artist artist, MusicStyle style,
            int length, int year, int bitRate ) {
        this.title = title;
        artists = new ArrayList<>();
        this.artists.add( artist );
        this.style = style;
        this.length = length;
        this.year = year;
        this.bitRate = bitRate;
    }

    /**
     * @return the track name
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the track name
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the track music style
     */
    public MusicStyle getStyle() {
        return style;
    }

    /**
     * @param style the track music style
     */
    public void setStyle(MusicStyle style) {
        this.style = style;
    }

    /**
     * @return the track length in seconds
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length in seconds
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the year of release
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year of release
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the bits per second
     */
    public int getBitRate() {
        return bitRate;
    }

    /**
     * @param bitRate the bits per second
     */
    public void setBitRate(int bitRate) {
        this.bitRate = bitRate;
    }

    /**
     * Adds a track performer
     * 
     * @param a new performer 
     */
    public void addArtist( Artist a ) {
        if ( !artistExists( a ) ) {
            artists.add( a );
        }
    }
    
    /**
     * Excludes an artist from the track performers list
     * 
     * @param artist the performer
     */
    public void removeArtist( Artist artist ) {
        artists.remove( artist );
    }
    
    /**
     * Checks if an artist is already in the list of the track performers
     * 
     * @param otherArtist   the artist to find
     * @return              true - the artist is in the list, false - if it's not there
     */
    public boolean artistExists( Artist otherArtist ) {
       for ( Artist a: artists ) {
           if ( a.compareTo( otherArtist ) == 0 ) {
               return true;
           }
       } 
       return false; // artist is not found
    }
    
    /**
     * Returns track performer name 
     * or names with separator ", " if there are several artists 
     * 
     * @param onlyFirst return only first artist name
     * @return          track performers names
     */
    public String getArtistNames( boolean onlyFirst ) {
        String str = "";
        for ( Artist a: artists ) {
            str += ( ( str.isEmpty() ) ? "" : ", " ) + a.getName();
            if ( onlyFirst ) {
                break;
            }
        }
        return str;
    }

    @Override
    public int compareTo(Track o) {
        if ( getTitle().compareToIgnoreCase( o.getTitle() ) == 0 
                && this.getLength() == o.getLength()
                && this.getYear() == o.getYear() ) {
            if ( this.artists.size() == o.artists.size() ) {
                for ( Artist a: this.artists ) {
                    boolean isExists = false;
                    for ( Artist otherArtist: o.artists ) {
                        isExists = otherArtist.compareTo( a ) == 0;
                        if ( isExists ) {
                            break;
                        }
                    }
                    if ( !isExists ) {
                        return 1; // differents performers
                    }
                }
                return 0; 
            }
            else {
                return 1; // different amount of performers
            }
        }
        else {
            return 1; // different titles, length or years released
        }
    }
    
    @Override
    public String toString() {
        String str = "";
        if ( artists.size() >= 1 ) {
            str += artists.get(0).getName();
        }
        if ( artists.size() == 2 ) {
            str += " ft " + artists.get(1).getName();
        }
        if ( artists.size() > 2 ) {
            str += " & others";
        }
        str += " - " + getTitle();
        int m = getLength() / 60;
        int s = getLength() % 60;
        str += " " + m + ":" + s + " /" + getYear() + ", " + getStyle() + "/";        
        return str;
    }
}
