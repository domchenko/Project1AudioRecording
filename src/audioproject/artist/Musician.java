/*
 * Musician
 *
 * Version 1
 */
package audioproject.artist;

import java.util.ArrayList;
import java.util.List;

/**
 * Names of the musical instruments
 * 
 * @author Tanya Domchenko
 */
enum MusicalInstrument {
    Drums, Gitar, Trumpet;
}

/**
 * Provides musician performer
 * 
 * @author Tanya Domchenko
 * @version 1, 08 Nov 2015
 */
public class Musician extends Artist implements Comparable<Artist>{
    private List<MusicalInstrument> instruments;

    /**
     * Class constructor specifying the musician name
     * 
     * @param name the musician name
     */
    public Musician(String name) {
        super( name, ArtistType.Musician );
        instruments = new ArrayList<>();
    }

    /**
     * Searches if this musician plays on the instrument
     * 
     * @param intrumentName     the name of an instrument
     * @return                  true/false
     */
    public boolean canPlayOn( String intrumentName ) {
        for ( MusicalInstrument instr: instruments ) {
            if ( intrumentName.compareToIgnoreCase( instr.toString() ) == 0 ) {
                return true;
            }
        }
        return false; // musician cannot play on <intrumentName>
    }
    
    /**
     * Adds an instrument that musician can play on
     * 
     * @param newInstrument the new instrument
     */
    public void addInstrument( MusicalInstrument newInstrument ) {
        if ( !instruments.contains( newInstrument ) ) {
            instruments.add( newInstrument );
        }
    }
    
    /**
     * Excludes the musical instrument 
     * 
     * @param instrument the musical instrument
     */
    public void removeInstrument( MusicalInstrument instrument ) {
        instruments.remove( instrument );
    }

    @Override
    public int compareTo(Artist o) {
        if ( this.getType() == o.getType() 
                && this.getName().compareToIgnoreCase( o.getName() ) == 0 ) {
            if ( this.instruments.size() == ( (Musician) o ).instruments.size() ) {
                for ( MusicalInstrument instr: instruments ) {
                    boolean isExists = false;
                    for ( MusicalInstrument otherInstrument: ( (Musician) o ).instruments ) {
                        isExists = otherInstrument.compareTo( instr ) == 0;
                        if ( isExists ) {
                            break;
                        }
                    }
                    if ( !isExists ) {
                        return 1;
                    }
                }
                return 0;
            }
            else {
                return 1; // different instruments amount
            }
        }
        else {
            return 1; // different names and/or types 
        }
    }
}
