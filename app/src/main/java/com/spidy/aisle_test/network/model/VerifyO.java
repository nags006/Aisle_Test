package com.spidy.aisle_test.network.model;

public class VerifyO
{
    private String token;

    public VerifyO(String token)
    {
        this.token = token;
    }

    public String getStatus() {
        return token;
    }

    public void setStatus(String token) {
        this.token = token;
    }
}
