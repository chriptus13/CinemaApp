package pt.isel.ls.model.commands.post;

import org.junit.Test;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static pt.isel.ls.model.RepositoryForTests.TEST_REPO;

public class TestPostSessionCommand {

    @Test
    public void insertSessionWithoutExceptionTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("cid", 10);
        parametersTestMap.put("tid", 10);
        parametersTestMap.put("mid", 10);
        parametersTestMap.put("date", "2018-03-23 23-59");

        // Action
        Object id = new PostSessionCommand().execute(parametersTestMap, TEST_REPO).getValue();

        // Assert
        assertNotNull(id);
    }

    @Test(expected = CommandException.class)
    public void incorrectTypeOfParametersTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("cid", 10);
        parametersTestMap.put("tid", 10);
        parametersTestMap.put("mid", "aa");
        parametersTestMap.put("date", new Date(1000));

        // Action & Assert
        new PostSessionCommand().execute(parametersTestMap, TEST_REPO);
    }

    @Test(expected = CommandException.class)
    public void incorrectAmountOfParametersTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();
        parametersTestMap.put("cid", 10);
        parametersTestMap.put("tid", 10);
        parametersTestMap.put("mid", 10);

        // Action & Assert
        new PostSessionCommand().execute(parametersTestMap, TEST_REPO);
    }


}
