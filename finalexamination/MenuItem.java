/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalexamination;

/**
 *
 * @author Samantha Lorin
 */
public class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    //pantay na to FINALLY
    public String toString() {
        return String.format("%-30s %10.2f", name, price);
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
