package pt.isel.ls.model.commands.get;

import org.junit.Test;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static pt.isel.ls.model.RepositoryForTests.TEST_REPO;

public class TestGetMovieCommand {

    @Test
    public void getMovieWithoutExceptionTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("mid", 0);

        // Action
        Object res = new GetMovieCommand().execute(parametersTestMap, TEST_REPO).getValue();

        // Assert
        assertNotNull(res);
    }

    @Test(expected = CommandException.class)
    public void incorrectTypeOfParametersTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("mid", "Test");

        // Action & Assert
        new GetMovieCommand().execute(parametersTestMap, TEST_REPO);
    }

    @Test(expected = CommandException.class)
    public void incorrectAmountOfParametersTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();

        // Action & Assert
        new GetMovieCommand().execute(parametersTestMap, TEST_REPO);
    }
}
