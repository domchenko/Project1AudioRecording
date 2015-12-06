/*
 * Artist
 *
 * Version 1
 */
package audioproject.artist;

import audioproject.track.Track;

/**
 * Provides song/track performer
 * 
 * @author Tanya Domchenko
 * @version 1, 08 Nov 2015
 */
abstract public class Artist implements Comparable<Artist>{
    private ArtistType type;
    private String name;
    
    /**
     * Types of performers
     * 
     * @author Tanya Domchenko
     */
    protected enum ArtistType {
        Singer, Musician, Band;
    }
 
    /**
     * Class constructor specifying name and type of the artist
     * 
     * @param name  the artist's name
     * @param type  the artist's type
     */
    public Artist( String name, ArtistType type ) {
       this.name = name;
       this.type = type;
    }    

    /**
     * @return the artist's type
     */
    public ArtistType getType() {
        return type;
    }

    /**
     * @param type the artist's type
     */
    public void setType(ArtistType type) {
        this.type = type;
    }

    /**
     * @return the artist's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the artist's name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public abstract int compareTo(Artist o);
    
}


