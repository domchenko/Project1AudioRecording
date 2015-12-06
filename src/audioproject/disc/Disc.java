/*
 * Disc
 *
 * Version 1
 */
package audioproject.disc;

import audioproject.track.MusicStyle;
import audioproject.track.Track;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides disc with music tracks
 * 
 * @author Tanya Domchenko
 * @version 1, 08 Nov 2015
 */
public class Disc implements MusicContainer, SortableMusicContainer {
    protected List<Track> tracks;
    private int capacity;
    
    /**
     * Disc constructor. Specifies default parameters
     */
    public Disc() {
        tracks = new ArrayList<>();
        capacity = 10;
    }
    
    /**
     * Disc constructor specifying the maximum number of tracks 
     * 
     * @param size 
     */
    public Disc( int size ) {
        this();
        this.capacity = size;
    }

    /**
     * Returns the biggest amount of tracks on the disc 
     * 
     * @return disc capacity
     */
    @Override
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Adds a new track on the disc if it's not already there
     * 
     * @param t the track to add
     * @throws StorageIsFullException   
     *          if the number of tracks is more than <code>getCapacity()</code>  
     */
    @Override
    public void addTrack( Track t ) throws StorageIsFullException {
        if ( tracks.size() == getCapacity() ) {
            throw new StorageIsFullException();
        }
        if ( !trackExists( t ) ) {
            tracks.add( t );
        }
    }
    
    /**
     * Checks if the track is already in the container
     * 
     * @param other the track to find
     * @return      true - the track is in the container, false - if it's not there
     */
    @Override
    public boolean trackExists( Track other ) {
       for ( Track t: tracks ) {
           if ( t.compareTo( other ) == 0 ) {
               return true;
           }
       } 
       return false; // track is not found
    }
    
    /**
     * Removes track from a disc
     * 
     * @param t 
     */
    public void removeTrack( Track t ) {
        tracks.remove( t );
    }
    
    /**
     * Calculates total duration of tracks in the container
     * 
     * @return total tracks length in seconds
     */
    @Override
    public int getDuration() {
        int totalLen = 0;
        for ( Track t: tracks ) {
            totalLen += t.getLength();
        }
        return totalLen;
    }
    
    /**
     * Searches a track in the container collection by name
     * 
     * @param name  name of the track to search
     * @return      track with the given name or null if nothing is found
     */
    @Override
    public Track findTrackByName( String name ) {
        for ( Track t: tracks ) {
            if ( t.getTitle().compareToIgnoreCase( name ) == 0 ) {
                return t;
            }
        }
        return null; // track is not found
    }
    
    /**
     * Searches tracks which duration (length) is in the defined range
     * 
     * @param lengthFrom    the minimum track length
     * @param lengthTo      the maximum track length
     * @return              tracks with the length between lengthFrom and lengthTo
     * @throws InvalidLengthRangeException 
     *                      if the length range bounds is less than <code>0</code>
     *                      or <code>lengthFrom > lengthTo</code>C
     */
    @Override
    public List<Track> findTrackByLength( int lengthFrom, int lengthTo ) throws InvalidLengthRangeException {
        if ( lengthFrom < 0 || lengthTo < 0 || lengthFrom > lengthTo ) {
            throw new InvalidLengthRangeException();
        }
        List<Track> res = new ArrayList<>();
        for ( Track t: tracks ) {
            if ( t.getLength() >= lengthFrom && t.getLength() <= lengthTo ) {
                res.add( t );
            }
        }
        return res;
    }
    
    /**
     * Sorting tracks on the disc by music styles. 
     * The other tracks are unsorted if their styles aren't listed in the input parameters
     * 
     * @param styles the list of music styles to sort tracks
     */
    @Override
    public void sortByStyle( MusicStyle ... styles ) {
        List<Track> orderedTracks = new ArrayList<>();
        for ( MusicStyle ms: styles ) {
            for ( int i = 0; i < tracks.size(); i++ ) {
                if ( tracks.get(i).getStyle() == ms ) {
                    orderedTracks.add( tracks.get(i) );
                }
            }
        }
        for ( int i = 0; i < tracks.size(); i++ ) {
            if ( !orderedTracks.contains( tracks.get(i) ) ) {
                orderedTracks.add( i, tracks.get(i) );
            }
        }
        tracks = orderedTracks;
    }

    @Override
    public String toString() {
        String str = "";
        int num = 1;
        for ( Track t: tracks ) {
            str += String.format( "%s%2d/%2d. %s", 
                    ( str.isEmpty() ) ? "" : "\n", num, getCapacity(), t.toString() );
            num++;
        }
        if ( str.isEmpty() ) {
            return "Disc is empty";
        }
        else {
            return "Disc content: \n" + str;
        }
    }

    /**
     * Track names and first artists names
     * 
     * @return 
     */
    @Override
    public List<String> getTracksInfo() {
  
        List<String> l = new ArrayList<>();
        for ( Track t: tracks ) {
            l.add( t.getTitle() + " - " + t.getArtistNames( true ) );
        }
        return l;
    }
    
}
