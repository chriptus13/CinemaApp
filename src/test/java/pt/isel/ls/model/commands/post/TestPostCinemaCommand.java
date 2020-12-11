package pt.isel.ls.model.commands.post;

import org.junit.Test;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static pt.isel.ls.model.RepositoryForTests.TEST_REPO;

public class TestPostCinemaCommand {

    @Test
    public void insertCinemaWithoutExceptionTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("name", "CinemaA");
        parametersTestMap.put("city", "CityA");

        // Action
        Object id = new PostCinemaCommand().execute(parametersTestMap, TEST_REPO).getValue();

        // Assert
        assertTrue(id.equals(-1));
    }

    @Test(expected = CommandException.class)
    public void incorrectTypeOfParametersTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("name", 1);
        parametersTestMap.put("city", 2);

        // Action & Assert
        new PostCinemaCommand().execute(parametersTestMap, TEST_REPO);
    }

    @Test(expected = CommandException.class)
    public void incorrectAmountOfParametersTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("name", "CinemaA");

        // Action & Assert
        new PostCinemaCommand().execute(parametersTestMap, TEST_REPO);
    }
}
