package pt.isel.ls.control;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.repositories.PostgresRepository;

public class Server {
    private Repository repository;
    private org.eclipse.jetty.server.Server server;

    Server(int port, Repository repo, boolean join) {
        repository = repo;
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(port);
        this.server = server;
        if(join) {
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);
            addServlets(context.getServletHandler());
        } else {
            ServletHandler handler = new ServletHandler();
            server.setHandler(handler);
            addServlets(handler);
        }
        try {
            server.start();
            if(join) server.join();
        } catch(Exception e1) {
            System.out.println("Error starting server please contact the server monkeys at this number : 420 133 796 ");
        }
    }

    public static void main(String[] args) {
        //Main for heroku deploy
        new Server(Integer.valueOf(System.getenv("PORT")), new PostgresRepository(), true);
    }

    void closeServer() {
        try {
            server.stop();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addServlets(ServletHandler handler) {
        handler.addServletWithMapping(new ServletHolder(new ServletGen(repository)), "/*");
    }
}
