package com.entity;

import javax.persistence.*;

@Entity
@Table(name="item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int quantity;
    private int amount;
    private int view;
    private int modal;
    private int salary;
    


   


    


    //UNTUK INPUT VIEW
    public Item(long id, int view) {
        this.id = id;
        this.view = view;
    }


    public Item(){

    }


    //Buat INPUT
    public Item(String name, int quantity, int amount, int modal) {
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
        this.modal = modal;
    }


    //BUAT BACA DATA
    public Item(long id, String name, int quantity, int amount, int view, int modal, int salary) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
        this.view = view;
        this.modal = modal;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getModal() {
        return modal;
    }


    public void setModal(int modal) {
        this.modal = modal;
    }

     public int getSalary() {
        return salary;
    }


    public void setSalary(int salary) {
        this.salary = salary;
    }
}
