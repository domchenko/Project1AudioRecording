/*
 * Band
 *
 * Version 1
 */
package audioproject.artist;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides band performer
 * 
 * @author Tanya Domchenko
 * @version 1, 08 Nov 2015
 */
public class Band extends Artist {
    private Singer soloist;
    private List<Musician> members;
    
    /**
     * Class constructor specifying the band name
     * 
     * @param name band name
     */
    public Band( String name ) {
        super( name, ArtistType.Band );
        members = new ArrayList<>();
    }

    /**
     * @return the band soloist
     */
    public Singer getSoloist() {
        return soloist;
    }

    /**
     * @param soloist the band soloist 
     */
    public void setSoloist(Singer soloist) {
        this.soloist = soloist;
    }

    /**
     * Searches band artist by name 
     * 
     * @param name  artist name
     * @return      the first band member with defined name
     */
    public Musician getMemberByName( String name ) {
        for ( Musician m: members ) {
            if ( name.compareToIgnoreCase( m.getName() ) == 0 ) {
                return m;
            }
        }
        return null; // no musician is found
    }
    
    /**
     * Adds a new musician into the band
     * 
     * @param newMusician the new band musician
     */
    public void addMember( Musician newMusician ) {
        if ( !members.contains( newMusician ) ) {
            members.add( newMusician );
        }
    }
    
    /**
     * Excludes the musician from the band
     * 
     * @param musician the band musician
     */
    public void removeMember( Musician musician ) {
        members.remove( musician );
    }
    
    /**
     * Returns names of all band members
     * 
     * @return artists names
     */
    public List<String> getArtistsNames() {
        List<String> lNames = new ArrayList<>();
        if ( soloist != null ) {
            lNames.add( soloist.getName() );
        }
        for ( Musician m: members ) {
            lNames.add( m.getName() );
        }
        return lNames;
    }

    @Override
    public int compareTo(Artist o) {
        if ( this.getType() == o.getType() 
                && this.getName().compareToIgnoreCase( o.getName() ) == 0 ) {
            int compareSolist = 0; // equal if bands without solist
            if ( this.getSoloist() != null && ( (Band) o ).getSoloist() != null ) {
                compareSolist = this.getSoloist().compareTo( 
                    ( (Band) o ).getSoloist() );
            }
            int compareOthers = 
                    this.members.size() - ( (Band) o ).members.size();
            if ( compareOthers != 0 || compareSolist != 0 ) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            return 1; // different cause names and types are different
        }
    }
}