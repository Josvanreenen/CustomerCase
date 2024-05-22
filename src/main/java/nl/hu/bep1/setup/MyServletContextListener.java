package nl.hu.bep1.setup;

import nl.hu.bep1.domain.Company;
import nl.hu.bep1.domain.Customer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDate;
import java.time.Month;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Company.getCompany().addCustomer(new Customer("you", "1234", "there", LocalDate.of(1983, Month.NOVEMBER, 25)));
        sce.getServletContext().log("ServletContextListener started");
        System.out.println("local log only for testing");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        sce.getServletContext().log("ServletContextListener ended");
    }
}
