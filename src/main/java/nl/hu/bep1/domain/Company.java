package nl.hu.bep1.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Company {
    private final List<Customer> allCustomers;
    private static Company company;

    private Company(){
        allCustomers = new ArrayList<>();
    }

    public static Company getCompany(){
        if(company == null) company = new Company();
        return company;
    }

    public boolean addCustomer(Customer toAdd){
        if(!allCustomers.contains(toAdd)){
            toAdd.setId(allCustomers.size());
            System.out.println(toAdd);
            return allCustomers.add(toAdd);

        }
        return false;
    }



    public Customer getCustomerByID(long id){
        if(id<0) throw new IllegalArgumentException("Cannot use negative identifiers");
        return allCustomers.stream().filter(customer -> id==customer.getId()).peek(System.out::println).findFirst().orElse(null);
    }

    public Customer updateCustomer(long id, String name){
        Customer found = getCustomerByID((int)id);
        if(found!=null)
            found.setName(name);
        return getCustomerByID((int)id);
    }

    public Customer updateCustomer(long id, String name, String zipcode, String city, LocalDate dateofbirth) {
        Customer toAdd = new Customer(name, zipcode, city, dateofbirth);
        toAdd.setId(id);
        if(getCustomerByID(id)!=null){
            allCustomers.remove((int)id);
        }
        allCustomers.add((int)id, toAdd);
        return getCustomerByID(id);
    }

    public boolean removeCustomer(long id) {
        return allCustomers.remove((int)id)!=null;
    }
}
