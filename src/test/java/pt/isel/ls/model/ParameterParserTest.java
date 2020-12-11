package pt.isel.ls.model;

import org.junit.Test;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class ParameterParserTest {

    @Test
    public void emptyStringTest() {
        // Arrange
        String cmd = "";

        // Assert
        Map<String, Object> map = ParameterParser.getParameters(cmd, new HashMap<>());

        // Assert
        assertEquals(0, map.size());
    }

    @Test
    public void normalStringTest() throws InvalidParametersException {
        // Arrange
        String cmd = "POST /cinemas name=CinemaA&city=CityA&otherParameter=something";

        // Action
        Map<String, Object> map = Parser.getParameterMap(cmd, s -> new HashMap<>());

        // Assert
        assertEquals(3, map.size());
        assertEquals("CinemaA", map.get("name"));
        assertEquals("CityA", map.get("city"));
        assertEquals("something", map.get("otherParameter"));
    }

    @Test
    public void wrongOrderedStringTest() throws InvalidParametersException {
        // Arrange
        String cmd = "/cinemas name=CinemaA&city=CityA&otherParameter=something POST";

        // Action
        Map<String, Object> map = Parser.getParameterMap(cmd, s -> new HashMap<>());

        // Assert
        assertEquals(0, map.size());
    }

    @Test
    public void randomStringTest() {
        // Arrange
        String cmd = "TEST /test A==a&&B==b";

        // Action
        Map<String, Object> map = ParameterParser.getParameters(cmd, new HashMap<>());

        // Assert
        assertEquals(0, map.size());
    }
}
