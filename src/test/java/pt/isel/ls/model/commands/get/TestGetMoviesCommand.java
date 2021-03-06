package pt.isel.ls.model.commands.get;

import org.junit.Test;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static pt.isel.ls.model.RepositoryForTests.TEST_REPO;

public class TestGetMoviesCommand {

    @Test
    public void getMoviesWithoutExceptionTest() throws CommandException {
        // Arrange
        Map<String, Object> parametersTestMap = new HashMap<>();

        // Action
        Object res = new GetMoviesCommand().execute(parametersTestMap, TEST_REPO).getValue();

        // Assert
        assertNotNull(res);
    }
}
