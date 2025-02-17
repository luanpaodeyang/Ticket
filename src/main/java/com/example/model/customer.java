package com.example.model;

import javax.persistence.*;

@Entity
public class customer {
    //用于映射数据库表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String idCard;
    private String showName;
    private int quantity;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private home ticket; // 购票信息，关联到 home 表的某一条记录

    @ManyToOne
    @JoinColumn(name = "home_id")
    private home home;



    // Getter 和 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public home getTicket() {
        return ticket;
    }

    public void setTicket(home ticket) {
        this.ticket = ticket;
    }

    public home getHome() {
        return home;
    }

    public void setHome(home home) {
        this.home = home;
    }

}

