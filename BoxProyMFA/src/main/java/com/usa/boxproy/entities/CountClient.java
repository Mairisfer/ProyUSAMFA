package com.usa.boxproy.entities;

public class CountClient {
    private Long total;
    private Client client;


    public CountClient(Long total, Client client){
        this.total = total;
        this.client = client;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
