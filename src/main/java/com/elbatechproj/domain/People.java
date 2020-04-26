package com.elbatechproj.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class People
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String country;
    private Date   modified;
    private Boolean vip;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public People() {}

    public People(String first_name, String last_name, String email, String country, Date modified, Boolean vip)
    {
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.country=country;
        this.modified=modified;
        this.vip=vip;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", Firstname=" + first_name + ", Lastname=" + last_name + ", Rmail=" + email + ", Country=" + country + ", Modified on=" + modified + ", IsVIP=" + vip + "]";
    }
}
