package com.izaiasvalentim.general.Domain;

import java.util.Date;

public class Access {
    private long id;
    private ApiUser user;
    private Date date;

    public Access(ApiUser user, Date date) {
        this.user = user;
        this.date = date;
    }

    public Access() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApiUser getUser() {
        return user;
    }

    public void setUser(ApiUser user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
