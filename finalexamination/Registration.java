/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalexamination;

/**
 *
 * @author Samantha Lorin
 */
public class Registration {
    private String firstName, lastName, email, pass, phoneNumber, address, bankDetails;

       public Registration(String firstName, String lastName, String email, String password, String phoneNumber, String address, String bankDetails) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.pass = password;
            this.phoneNumber = phoneNumber;
            this.address = address;
    }

      public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return pass;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getAddress() {
        return address;
    }
    public String getBankDetails(){
        return bankDetails;
    }
    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }
    
}
