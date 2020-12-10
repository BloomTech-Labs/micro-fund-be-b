package com.lambdaschool.oktafoundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizations")
public class Organization extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orgid;

    @NotNull
    private String name;

    private String address;
    private String phone;

    //applications - KM
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "organization", allowSetters = true)
    private List<Application> applications = new ArrayList<>();

    // tying organization to users - KM
    @ManyToMany(mappedBy = "organizations")
    @JsonIgnoreProperties(value = "organizations", allowSetters = true)
    private List<User> users = new ArrayList<>();

    public Organization()
    {
    }

    public Organization(String name,
                        String address,
                        String phone)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public long getOrgid()
    {
        return orgid;
    }

    public void setOrgid(long orgid)
    {
        this.orgid = orgid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public List<Application> getApplications()
    {
        return applications;
    }

    public void setApplications(List<Application> applications)
    {
        this.applications = applications;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }


}