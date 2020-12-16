package com.lambdaschool.oktafoundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "app")
public class Application extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long appid;

    private String name;
    private String address;
    private String phone;
    private String reason;
    private String status;

    @ManyToOne
    @JoinColumn(name = "orgid", nullable = false)
    @JsonIgnoreProperties(value = {"applications","users"}, allowSetters = true)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = {"applications","organizations"}, allowSetters = true)
    private User user;

    public Application()
    {
    }

    public Application(
        String name,
        String address,
        String phone,
        String reason,
        String status,
        Organization organization,
        User user)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.reason = reason;
        this.status = status;
        this.organization = organization;
        this.user = user;
    }

    public long getAppid()
    {
        return appid;
    }

    public void setAppid(long appid)
    {
        this.appid = appid;
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

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Organization getOrganization()
    {
        return organization;
    }

    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }


}
