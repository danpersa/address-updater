package de.regis.regulator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StreetRegulatorTest {

    private StreetRegulator streetRegulator = new StreetRegulator();

    @Test
    public void testRegulate() throws Exception {
        assertThat(streetRegulator.regulate("Gubener Straße 29"), is("Gubener  29"));
        assertThat(streetRegulator.regulate("Zionskirchstraße 73 A"), is("Zionskirch 73 A"));
        assertThat(streetRegulator.regulate("Hansastr. 202"), is("Hansa 202"));
        assertThat(streetRegulator.regulate("Zehdenicker Str. 21"), is("Zehdenicker  21"));
    }
}