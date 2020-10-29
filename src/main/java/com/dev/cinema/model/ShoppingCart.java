package com.dev.cinema.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    private Long id;
    @OneToMany
    private List<Ticket> tickets;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        tickets = new ArrayList<>();
        this.user = user;
    }
}
