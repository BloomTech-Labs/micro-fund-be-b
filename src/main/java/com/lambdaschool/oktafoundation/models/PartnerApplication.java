package com.lambdaschool.oktafoundation.models;

import javax.persistence.*;

@Entity
@Table(name = "partapp")
public class PartnerApplication extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long partappid;

    private String name;
    private String address;
    private String phone;
    private String type;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public PartnerApplication()
    {
    }

    public PartnerApplication(
        String name,
        String address,
        String phone,
        String type,
        String description,
        String status,
        User user)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    public long getPartappid()
    {
        return partappid;
    }

    public void setPartappid(long partappid)
    {
        this.partappid = partappid;
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
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
