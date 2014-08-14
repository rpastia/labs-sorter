package ro.pastia.labs.sorter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SorterAppTest {

    @Test
    public void testGetType() {
        assertEquals(SorterApp.Type.INTEGER, SorterApp.getType("1"));
        assertEquals(SorterApp.Type.INTEGER, SorterApp.getType("-1"));
        assertEquals(SorterApp.Type.FLOAT, SorterApp.getType("1.1"));
        assertEquals(SorterApp.Type.STRING, SorterApp.getType("test"));
        assertEquals(SorterApp.Type.STRING, SorterApp.getType("1 2"));
    }

}