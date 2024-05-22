package nl.hu.bep1.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {

    private long id;
    private String name;
    private String zipcode;
    private String city;
    private LocalDate dateOfBirth;

    public Customer(String name, String zipcode, String city, LocalDate dateOfBirth){
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
    }

    public void setId(long id){
        this.id=id;
    }
    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(dateOfBirth, customer.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth);
    }

    public void setName(String name) {
        if(name.isBlank())
            throw new IllegalArgumentException("name cannot be blank");
        this.name=name;
    }
}
