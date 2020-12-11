package pt.isel.ls.model.commands.post;

import org.junit.Test;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static pt.isel.ls.model.RepositoryForTests.TEST_REPO;

public class TestPostTheaterCommand {

    @Test
    public void insertWithoutExceptionTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("cid", 0);
        parametersTestMap.put("name", "TheaterA");
        parametersTestMap.put("rows", 10);
        parametersTestMap.put("seats", 10);

        // Action
        Object id = new PostTheaterCommand().execute(parametersTestMap, TEST_REPO).getValue();

        // Assert
        assertTrue(id.equals(-1));
    }

    @Test(expected = CommandException.class)
    public void incorrectTypeOfParametersTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("cid", 0);
        parametersTestMap.put("name", "TheaterA");
        parametersTestMap.put("rows", "10");
        parametersTestMap.put("seats", 10);

        // Action & Assert
        new PostTheaterCommand().execute(parametersTestMap, TEST_REPO);
    }

    @Test(expected = CommandException.class)
    public void incorrectAmountOfParametersTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("cid", 0);
        parametersTestMap.put("name", "TheaterA");
        parametersTestMap.put("rows", 10);

        // Action & Assert
        new PostTheaterCommand().execute(parametersTestMap, TEST_REPO);
    }
}
