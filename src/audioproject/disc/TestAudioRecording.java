/*
 * TestAudioRecording
 *
 * Version 1
 */
package audioproject.disc;

import audioproject.artist.Band;
import audioproject.artist.Singer;
import audioproject.track.MusicStyle;
import audioproject.track.Track;
import java.util.Collection;

/**
 * Writes track on the disc, sorting and searching tracks
 * 
 * @author Tanya Domchenko
 */
public class TestAudioRecording {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Disc d = new Disc( 8 );
        
        try {
            Track t1 = new Track( "Numb", new Band( "Linkin Park" ), 
                    MusicStyle.Rock, 210, 2004, 192 );
            d.addTrack(t1);
            // try to add the same song
            Track t2 = new Track( "Numb", new Band( "Linkin Park" ), 
                    MusicStyle.Rock, 210, 2004, 192 );
            d.addTrack(t2);    
            Track t3 = new Track( "Not Strong Enough", new Singer( "Brent Smith" ), 
                    MusicStyle.Rock, 302, 2012, 192 );
            t3.addArtist( new Band( "Apocalyptica" ));
            d.addTrack( t3 );
            d.addTrack( new Track( "Let The Morning Sleep", new Band( "Reamonn" ), 
                    MusicStyle.Rock, 251, 2010, 192 ) );
            d.addTrack( new Track( "Lonely Hunter", new Band( "Foals" ), 
                    MusicStyle.Indie, 278, 2015, 192 ) );
            d.addTrack( new Track( "Bad Company", new Band( "Five Finger Death Punch" ), 
                    MusicStyle.Metal, 294, 2009, 192 ) );
            d.addTrack( new Track( "Hold On To Me", new Band( "Placebo" ), 
                    MusicStyle.Alternative, 227, 2013, 192 ) );
            d.addTrack( new Track( "Diary of Jane", new Band( "Breaking Benjamin" ), 
                    MusicStyle.Rock, 236, 2007, 192 ) );
            d.addTrack( new Track( "Come On Over", new Band( "Royal Blood" ), 
                    MusicStyle.Alternative, 227, 2013, 192 ) );
            d.addTrack( new Track( "Creep", new Band( "Radiohead" ), 
                    MusicStyle.Alternative, 199, 1993, 192 ) );
                        
            System.out.println( d.toString() );
        } catch ( StorageIsFullException e ) {
            System.out.println( "Operation stopped: Disc is full" );
            System.out.println( d.toString() );
        }
        
        System.out.println( "Sorting..." );
        d.sortByStyle( MusicStyle.Alternative, MusicStyle.Rock );
        System.out.println( d.toString() );
        
        System.out.println( "Searching..." );
        try {
            Collection<Track> c = d.findTrackByLength( 240, 290 );
            for ( Object o: c ) {
                System.out.println( o.toString() );
            }
        } catch (InvalidLengthRangeException ex) {
            System.out.println( "Invalid track length range" );
        }
    }
    
}
