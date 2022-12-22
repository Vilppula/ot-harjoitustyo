package laatikkopeli.algorithm;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for SPAlgortihm-class
 * @author lasse
 */
public class SPAlgorithmTest {
    
    SPAlgorithm SPA;
    
    
    @Before
    public void setUpClass() {
        char[][] charState = {
            {',',',',','},
            {'1','3','5'},
            {',',',',','},
            {',',',',','}
        };
        SPA = new SPAlgorithm(charState, 3, 4);
    }
    
    @Test
    public void newSPAWasCreatedWithGivenArguments() {
        assertEquals(SPAlgorithm.class, SPA.getClass());
    }
    
    @Test
    public void algorithmReturnsDistanceOne() {
        char[][] charState = {
            {',',',',','},
            {'1','3','5'},
            {',',',',','},
            {',',',',','}
        };
        assertEquals(1, SPA.count(charState, 1, 2));
    }
    
    @Test
    public void algorithmReturnsDistanceSix() {
        char[][] charState = {
            {'1',',',','},
            {',','3',','},
            {',',',',','},
            {',',',','5'}
        };
        assertEquals(6, SPA.count(charState, 3, 2));
    }
    
    @Test
    public void algorithmReturnsMinusOneIfCantFindPath() {
        char[][] charState = {
            {'1',',','3'},
            {',',',',','},
            {',',',',','},
            {',',',','5'}
        };
        assertEquals(-1, SPA.count(charState, 3, 2));
    }
}    
