package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name="inventory_item")
public class InventoryItem {
    @Id @GeneratedValue
    @Column(name = "inventory_item_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inventory_id")
    Inventory inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    Item item;

    Long quantity;
}
