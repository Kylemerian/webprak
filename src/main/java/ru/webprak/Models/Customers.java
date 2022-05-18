package ru.webprak.Models;

import javax.persistence.*;

@Entity
@Table(name = "Customers")
public class Customers
{
    public Customers(String lastname, String firstname,
                     String address, String phone_number,
                     String mail) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.phone_number = phone_number;
        this.mail = mail;
    }

    public Customers() {    }

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        final Customers other = (Customers) obj;
        return (this.customer_id == other.customer_id) &&
                (this.lastname.equals(other.lastname)) &&
                (this.firstname.equals(other.firstname)) &&
                (this.address.equals(other.address)) &&
                (this.phone_number.equals(other.phone_number)) &&
                (this.mail.equals(other.mail));
    }

    @Column(name = "lastname") private String lastname;
    @Column(name = "firstname") private String firstname;
    @Column(name = "address") private String address;
    @Column(name = "phone_number") private String phone_number;
    @Column(name = "mail") private String mail;

}
