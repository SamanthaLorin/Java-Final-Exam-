/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalexamination;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Samantha Lorin
 */


class Cart {
   public Registration registration;
   
   private ArrayList<String> items = new ArrayList<>();
   private ArrayList<MenuItem> menuItems = new ArrayList<>(); // para makuha yung laman doon sa menu item
   private ArrayList<Integer> itemQty = new ArrayList<>();
   private ArrayList<ArrayList<String>> orderHistory = new ArrayList<>(); // List of all past receipts
   private ArrayList <Double> itemPrice = new ArrayList<>();
   
   private Scanner input = new Scanner(System.in); 
   static double total = 0.0;
   static double finalGrandTotal = 0.0;
   static double finalVat = 0.0;
   static String buyItem;
   
   private int deliverMode;
   static double vat = 0.0;
  
   //initialize and instantiate registration
   // para mapasok or makuha yung mga parameters / in general makuha yung sa registration class
   public Cart (Registration registration){
       this.registration = registration;       
   }
   
   public void addItem(MenuItem item, int qty) {
      
       double productPrice = qty * item.getPrice(); 
       
       itemPrice.add(productPrice);
       total += productPrice;
       
       itemQty.add(qty); // add qty 
       menuItems.add(item); // kaya tayo may ganiyan kasi kinukuha niya yung laman doon sa kabilang class tapos para makuha yung new total kapag madami kang update
       items.add(qty + "x " + item.getName() + "\t@" + item.getPrice() + "\t\t" + productPrice);
       

       System.out.println("Added to cart!");
   }
    
   public void displayCart() {
        System.out.println("\n--------------- Your Cart ----------------");
       if (items.isEmpty()) {
           System.out.println("Cart is empty.");
       } else {
            System.out.printf("%-30s %-10s %-10s\n", "Qty and Product", "\t\tUnit", "\tTotal"); 
              for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            String[] parts = item.split("\t");
            String qtyAndProduct = parts[0];
            String unit = parts[1];
            String itemTotal = String.format("%.2f", itemPrice.get(i));

            System.out.printf("[%d]. %-40s %-15s %-10s\n", i, qtyAndProduct, unit, itemTotal);
        }
        FinalExamination.chooseActionInCart(this);
    }
   }
   
       public void removeItem(){
        System.out.print("\nWould you like to remove an item? (y/n): ");
        String boolRemove = input.nextLine().trim();
        
        if(boolRemove.equalsIgnoreCase("Y")){
            System.out.print("Select a number to remove (from 0 up to " + (items.size() - 1) + "): ");
            int removeItems = input.nextInt();
            input.nextLine();
            
            if (removeItems >= 0 && removeItems < items.size()){
                items.remove(removeItems);
                
                if(items.isEmpty()){
                    System.out.println("Your cart is empty! ");
                } else {
                    displayCart();                
                }
            } else {
                System.out.println("Invalid. Try again.");
                removeItem();
            }
        }else if(boolRemove.equalsIgnoreCase("N")){   
            FinalExamination.chooseActionInCart(this);
        } else {
            System.out.println("Invalid!");
        }

    }
       
   public void editItem(){
    System.out.println("\n---NOTE: This can only edit quantity.---");
    System.out.print("\nEnter the number of an item that you'd like to edit (from 0 to " + (items.size() - 1) + "): ");
    int editIndexItem = input.nextInt();
    input.nextLine();

    if (editIndexItem >= 0 && editIndexItem < items.size()) {
        System.out.print("Enter the new quantity: ");
        int editQty = input.nextInt(); 
        input.nextLine();

        double oldProductPrice = itemPrice.get(editIndexItem);

        double newProductPrice = editQty * menuItems.get(editIndexItem).getPrice();

        total += (newProductPrice - oldProductPrice);

        itemQty.set(editIndexItem, editQty);
        itemPrice.set(editIndexItem, newProductPrice); 
        
        String updatedItem = editQty + "x " + menuItems.get(editIndexItem).getName() 
            + "\t@" + menuItems.get(editIndexItem).getPrice() 
            + "\t\t" + newProductPrice;
        
        items.set(editIndexItem, updatedItem); 
        
        System.out.println("\nUpdated Cart: ");
        confirmCart(); 
    } else {
        System.out.println("Invalid index. Try again.");
        editItem();
    }
}
   
   
   public void confirmCart(){
        System.out.printf("%-30s %-10s\n", "Qty and Product", "\t\tPrice");

    for (int i = 0; i < items.size(); i++) { 
        String itemList = items.get(i);
        String[] parts = itemList.split("\t");
        String qtyAndProduct = parts[0];  
        
        String price = String.format("%.2f", itemPrice.get(i));  

        System.out.printf("%-40s %-10s\n", qtyAndProduct, price);
    }
}
   public void checkOutItem(){
       System.out.print("Would you like to check out the items? (y/n): ");
       String boolOrder = input.nextLine().trim();
    
       if(boolOrder.equalsIgnoreCase("Y")){
           System.out.println("\n------------ ORDER CONFIRMATION -------------");
            
           System.out.print("Would you like to buy the items? (y/n):  ");    
           buyItem = input.nextLine();
            
           if(buyItem.equalsIgnoreCase("Y")){
               ArrayList<String> receiptItems = new ArrayList<>(items); 
               ArrayList<Double> priceItems = new ArrayList<>();
                orderHistory.add(receiptItems); 
                finalGrandTotal = total; 
                finalVat = total * 0.12;
                
                  modeOfPayment();
                // I cleared them para it will be removed on view cart option since the user already checked out   
               items.clear();
                
               receipt();
               items.clear();

               menuItems.clear();
               itemQty.clear();
               itemPrice.clear();
               total = 0.0;
           } else {
               System.out.println("Invalid Option!");
           }
       } else if (boolOrder.equalsIgnoreCase("N")){
           FinalExamination.MainMenu(this);
       } else {
           System.out.println("Invalid option!");
       }
   }
   
    public void modeOfPayment() {
       System.out.println("\n[1] Cash on Delivery");
       System.out.println("[2] Bank / e-wallet");
       System.out.print("\nChoose your mode of payment: ");
       int paymentMode = input.nextInt();
       input.nextLine();
       
    if (paymentMode == 1) {
        modeOfDelivery();
    } else if (paymentMode == 2) {
        if (registration.getBankDetails() != null && !registration.getBankDetails().isEmpty()) {
            System.out.println("You have a linked bank account: " + registration.getBankDetails());
            modeOfDelivery();
        } else {
            // to let user link an account
            System.out.print("\nIt looks like you haven't linked any accounts. Would you like to link one now? (y/n): ");
            String linkAcc = input.nextLine(); 
            if (linkAcc.equalsIgnoreCase("Y")) {
                //para makapag enter si user ng bank details
                String bankDetails = FinalExamination.bankAccount();
                registration.setBankDetails(bankDetails); // Store Bank Details
                System.out.println("Bank account linked successfully: " + bankDetails);
                modeOfDelivery(); 
            } else if (linkAcc.equalsIgnoreCase("N")) {
                System.out.println("No bank account linked.");
                modeOfPayment(); 
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                modeOfPayment(); 
            }
        }
    } else {
        System.out.println("Invalid Option. Try again.");
        modeOfPayment(); 
    }
}
   public void modeOfDelivery(){
       //for delivery option
       System.out.println("\n[1] Pick-up");
       System.out.println("[2] Deliver");
       System.out.print("\nChoose your mode of delivery: ");
       deliverMode = input.nextInt();
       
       switch(deliverMode){
           case 1:
               System.out.println("\nYou have selected Pick-up.");
               receipt();
               break;
               
           case 2:
               System.out.println("\nYou have selected Deliver."); 
               receipt();
               break;
        default:
            System.out.println("Invalid Option. Please try again.");
            modeOfDelivery();
            break;
       }
   }
   
   public void receipt(){
        System.out.println("\n*****Here's your receipt!*****");
        System.out.println("\n***************************************************");
        System.out.println("\n\t\tMCBytes Restaurant");
        System.out.println("\n***************************************************");

    // PARA MAKUHA YUNG USER INFO
        System.out.println("\nUser's name: " + registration.getFirstName());
        System.out.println("Address: " + registration.getAddress());
        System.out.println("Number: " + registration.getPhoneNumber() + "\n");
        
      for (int i = 0; i < items.size(); i++) { 
        String itemList = items.get(i);
        String[] parts = itemList.split("\t");
        String qtyAndProduct = parts[0];  

        String price = String.format("%.2f", itemPrice.get(i));  

        System.out.printf("%-40s %-10s\n", qtyAndProduct, price);   
    }

        System.out.println("\n***************************************************");

        double vat = total * 0.12;
        double grandTotal = total + vat;

        itemPrice.add(grandTotal);
        itemPrice.add(vat);
        System.out.printf("\nTotal:\t\t%.2f\n", total);
        System.out.printf("VAT (12%%):\t%.2f\n", vat);
        System.out.printf("Grand Total:\t%.2f\n", grandTotal);

        System.out.println("\n***************************************************");
        System.out.println("\tThank you for dining with us!");
        
           if(deliverMode == 2){
           System.out.println("");
           System.out.println("\nDelivery Option: Deliver.");
           System.out.println("Estimated delivery time: 20-30 minutes.");
       } else if(deliverMode == 1){
           System.out.println("Delivery Option: Pick-up");
           System.out.println("Please visit our store to pick up your order. Our address is:");
           System.out.println("143 Byte Street, Dasmarinas, 11024");
           System.out.println("Store hours: Monday to Saturday, 8 AM to 7 PM");
       }
       System.out.println("\n***************************************************");
       System.out.println("\tThank you for dining with us!"); 
}
   
    public void myOrders() {
        if(orderHistory.isEmpty()){
           System.out.println("You have no orders!");
       } else if (buyItem == null){
           System.out.println("Check out your items first!");

       } else if (buyItem.equalsIgnoreCase("Y")) {
            System.out.println("\n--------------- Your Orders ----------------");
            
            for (int i = 0; i < orderHistory.size(); i++) {
            System.out.println("\nOrder #" + (i + 1) + ":");
            System.out.printf("%-30s %-10s\n", "Product", "Price");

            double orderTotal = 0.0;

            for (String itemList : orderHistory.get(i)) {
                String[] parts = itemList.split("\t+");
                String qtyAndProduct = parts[0]; 
                String price = parts[parts.length - 1]; 

                System.out.printf("%-30s %-10s\n", qtyAndProduct, price);
                
                double itemPrice = Double.parseDouble(price);
                orderTotal += itemPrice;
            }
            
           
            double orderVAT = finalVat;
            double grandTotal = orderTotal + orderVAT;
            
            System.out.printf("\nTotal:\t\t%.2f\n", orderTotal);
            System.out.printf("VAT (12%%):\t%.2f\n", orderVAT);
            System.out.printf("Grand Total:\t%.2f\n", grandTotal);

           System.out.println("\n***************************************************");     
       }        
   }
   }
}