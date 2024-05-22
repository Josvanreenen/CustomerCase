package nl.hu.bep1.setup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("ServletContextListener started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        sce.getServletContext().log("ServletContextListener ended");
    }
}
