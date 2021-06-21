package com.example.finaltesttravel;

import java.io.Serializable;

public class Attraction implements Serializable {
     String id;
     String name;
     String address;
     String price;
     String photo;
     String description;

    public Attraction(String id, String name, String address, String price, String photo, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.photo = photo;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", price='" + price + '\'' +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
