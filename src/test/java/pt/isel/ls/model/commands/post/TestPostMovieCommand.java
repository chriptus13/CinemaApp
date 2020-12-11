package pt.isel.ls.model.commands.post;

import org.junit.Test;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static pt.isel.ls.model.RepositoryForTests.TEST_REPO;

public class TestPostMovieCommand {

    @Test
    public void insertMovieWithoutExceptionTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("id", 2);


        // Action
        Object id = new PostMovieCommand().execute(parametersTestMap, TEST_REPO).getValue();

        // Assert
        assertTrue(id.equals(-1));
    }

    @Test(expected = CommandException.class)
    public void incorrectTypeOfParametersTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("title", "CinemaA");
        // Action & Assert
        new PostMovieCommand().execute(parametersTestMap, TEST_REPO);
    }

    @Test(expected = CommandException.class)
    public void incorrectAmountOfParametersTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("title", "CinemaA");
        parametersTestMap.put("releaseYear", 2018);

        // Action & Assert
        new PostMovieCommand().execute(parametersTestMap, TEST_REPO);
    }
}
