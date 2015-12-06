/*
 * Singer
 *
 * Version 1
 */
package audioproject.artist;

/**
 * Provides singer performer
 * 
 * @author Tanya Domchenko
 * @version 1, 08 Nov 2015
 */
public class Singer extends Artist implements Comparable<Artist>{
    private int vocalRangeFrom; // low voice range 
    private int vocalRangeTo;   // high voice range
    
    /**
     * Class constructor specifying singer's name
     * 
     * @param name singer's name
     */
    public Singer( String name ) {
        super( name, ArtistType.Singer );
    }

    /**
     * @return the low voice range
     */
    public int getVocalRangeFrom() {
        return vocalRangeFrom;
    }

    /**
     * @param vocalRangeFrom the low voice range
     */
    public void setVocalRangeFrom(int vocalRangeFrom) {
        this.vocalRangeFrom = vocalRangeFrom;
    }

    /**
     * @return the high voice range
     */
    public int getVocalRangeTo() {
        return vocalRangeTo;
    }

    /**
     * @param vocalRangeTo the high voice range
     */
    public void setVocalRangeTo(int vocalRangeTo) {
        this.vocalRangeTo = vocalRangeTo;
    }

    @Override
    public int compareTo(Artist o) {
        if ( this.getType() == o.getType() 
                && this.getName().compareToIgnoreCase( o.getName() ) == 0 ) {
            if ( this.getVocalRangeFrom() > 0 && this.getVocalRangeTo() > 0
                    && ( (Singer) o ).getVocalRangeFrom() > 0 
                    && ( (Singer) o ).getVocalRangeTo() > 0 ) {
                return ( this.getVocalRangeFrom() - ( (Singer) o ).getVocalRangeFrom() )
                        + ( this.getVocalRangeTo() - ( (Singer) o ).getVocalRangeTo() );
            }
            else { // equal if vocal range data is unknown
                return 0;
            }
        }
        else {
            return 1; // different cause type and name are different
        }
    }
   
}
