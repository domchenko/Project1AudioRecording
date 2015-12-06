package audioproject.disc;

import audioproject.track.MusicStyle;
import audioproject.track.Track;
import java.util.List;

/**
 * Occurs when the container is full
 * 
 * @author Tanya Domchenko
 * @version 1, 08 Nov 2015
 */ 
class StorageIsFullException extends Exception {}
/**
 * Occurs if a track length range is incorrect
 * 
 * @author Tanya Domchenko
 * @version 1, 08 Nov 2015
 */
class InvalidLengthRangeException extends Exception {}

/**
 * Defines methods for any music tracks holder
 * 
 * @author Tanya Domchenko
 * @version 1, 08 Nov 2015
 */
interface MusicContainer {
    /**
     * Returns the biggest amount of tracks in this container 
     * 
     * @return container capacity
     */
    int getCapacity();
    
    /**
     * Returns all tracks names and artists in this container
     * 
     * @return  collection of tracks names and artists  
     */
    List<String> getTracksInfo();
    
    /**
     * Adds a new track into the container if it's not already there
     * 
     * @param t the track to add
     * @throws StorageIsFullException   
     *          if the number of tracks is more than <code>getCapacity()</code>
     */
    void addTrack( Track t ) throws StorageIsFullException;
    
    /**
     * Checks if the track is already in the container
     * 
     * @param other the track to find
     * @return      true - the track is in the container, false - if it's not there
     */
    boolean trackExists( Track other );
        
    /**
     * Calculates total duration of tracks in the container
     * 
     * @return total tracks length in seconds
     */
    public int getDuration();
    
    /**
     * Searches a track in the container collection by name
     * 
     * @param name  name of the track to search
     * @return      track with the given name or null if nothing is found
     */
    public Track findTrackByName( String name );
    
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
    public List<Track> findTrackByLength( int lengthFrom, int lengthTo ) throws InvalidLengthRangeException;
}

/**
 * Defines sorting tracks methods in the container
 * 
 * @author Tanya Domchenko
 * @version 1, 08 Nov 2015
 */
interface SortableMusicContainer {
    /**
     * Sorting tracks in the container by music styles. 
     * The other tracks are unsorted if their styles aren't listed in the input parameters
     * 
     * @param styles the list of music styles to sort tracks
     */
    void sortByStyle( MusicStyle ... styles );
}