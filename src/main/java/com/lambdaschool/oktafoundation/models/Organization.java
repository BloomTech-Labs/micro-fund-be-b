package com.lambdaschool.oktafoundation.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
}