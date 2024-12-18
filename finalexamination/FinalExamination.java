/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finalexamination;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Samantha Lorin
 */
public class FinalExamination {
    
static ArrayList<Registration> users = new ArrayList<>();
static Scanner input = new Scanner(System.in); //inisahan ko na para hindi paulit-ulit gumawa ng scanner

    // MAIN METHOD 
    public static void main(String[] args) {
         System.out.println("\n \n***********************************************************");
        System.out.println("\t\tWelcome to MCBytes Restaurant");
        System.out.println("***********************************************************");
        Open();
        
}
   
        // open() - ito yung unang makikita ni user bago yung menu.
        // need niya muna mag login/sign up bago makapasok sa system. 
        public static void Open() {
        
        System.out.println("[1] Login");
        System.out.println("[2] Sign up");
        
        System.out.print("\nSelect an Option: ");
        int login_signup = input.nextInt();
        
        switch (login_signup) {
            case 1: 
                System.out.println("Login your account. Please fill in the information below");
                login();
                break;
            case 2: 
                System.out.println("Sign Up!");
                signUp();
                break;
            default:
                System.out.println("You chose an invalid option. Please choose between 1 and 2.");    
                Open(); 
        }
    }
        
        // Guys, sign up to guys. Sign-up. 
        public static void signUp() {
        Scanner input = new Scanner(System.in); 
        String fname, lname, email, pass, address, pnumber;
        
        System.out.println("\nUSER DETAILS: ");
        
        System.out.print("First Name: ");
        fname = input.nextLine();
        
        System.out.print("Last Name: ");
        lname = input.nextLine();
        
        System.out.println("\nLOGIN AND CONTACT DETAILS: ");
        
        System.out.print("Email: ");
        email = input.nextLine();
        
        System.out.print("Password: ");
        pass = input.nextLine(); 
        
        System.out.print("Phone Number: ");
        pnumber = input.nextLine();
        
        System.out.print("Address: ");
        address = input.nextLine();
        
        
        
        if (users.contains(email)) {
            System.out.println("\nThis account is already registered. Please try logging in.");
            Open();
            return;
        }
        
        String bankDetails = bankAccount();
        
        // PARA MAKAPAG REGISTER ULIT SI USER (arraylist = add)
        Registration newUser = new Registration(fname, lname, email, pass, pnumber, address, bankDetails);
        users.add(newUser);
                
        System.out.println("\nSign up Successful! You can now log in.");
        Open(); 
        
    }
        
    public static void login(){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Email: ");
        String email = input.nextLine();
        
        System.out.print("Password: ");
        String password = input.nextLine();  
        
        Registration user = null;
        for (Registration signup : users) {
         
            if (signup.getEmail().equals(email)) {
                user = signup;
                break;
            }
        }
        
         //ito lalabas kapag wala pang account si user 
         //null = nothing, empty, waley laman
        if (user == null) {
        System.out.println("\nUser not found. Please try signing up.");
        Open();
        return;
        }
                
        // ito lalabas after maglogin ni user. 
         if (user.getPassword().equals(password)) {
            System.out.println("\nLogin successful! Welcome, " + user.getFirstName() + "!");
         } else {
            System.out.println("\nIncorrect password. Try again.");
            login();
             
         }
        
         OpenHomePage(user);
        // AFTER NG LOGIN TSAKA SIGNUP
        }
        
       public static String bankAccount(){ 
        Scanner input = new Scanner(System.in);
        System.out.println("Bank Details: ");
        System.out.println("\n[1] Debit / Credit Card");
        System.out.println("[2] Gcash");
        System.out.println("[3] Maya");
        System.out.println("[4] None");
        
        System.out.print("\nChoose a bank / e-wallet to link your account: ");
        int bankOption =  input.nextInt();
        input.nextLine();
        String bankDetails = "";
        
        switch(bankOption){
            case 1:    
                System.out.print("Card Number: ");
                String cardNum = input.nextLine();
                
                System.out.print("Expiry Date: ");
                String expiryDate = input.nextLine();
                
                System.out.print("CVV: ");
                int cvv = input.nextInt();
                input.nextLine();
                
                System.out.print("Name on card: ");
                String cardName = input.nextLine();
                
                bankDetails = "Card: " + cardNum + ", Expiry: " + expiryDate + ", CVV: " + cvv + ", Name: " + cardName;
                break;       
                
            case 2:
                System.out.print("Gcash Number: ");
                String gcashNum = input.nextLine();
                bankDetails = "Gcash: " + gcashNum;
                
                break;
            
            case 3:
                System.out.print("Maya number: ");
                String mayaNum = input.nextLine();
                bankDetails = "Maya: " + mayaNum;
                break;
            case 4:
                bankDetails = null;
                break;
        default:
            System.out.println("Invalid option. No bank account linked.");
            bankDetails = "None";
            break;
        }
     return bankDetails;
    }    
    
    public static void OpenHomePage(Registration user){    
        System.out.println("\n \n***********************************************************");
        System.out.println("\t\tWelcome to MCBytes Restaurant");
        System.out.println("***********************************************************");
        
        //call cart, main menu
        Cart cart = new Cart(user);
        MainMenu(cart);
        
    }
        // TO DISPLAY MAIN MENU 
        public static void MainMenu(Cart cart) {
        System.out.println("\n[1] View Menu");
        System.out.println("[2] View Cart");
        System.out.println("[3] View My Orders");
        System.out.println("[4] Exit");
        
        System.out.print("\nChoose your option: ");
        int option = input.nextInt();
        
         switch (option) {
            case 1:
                ViewMenuCategories(cart);
                break;
            case 2:
                cart.displayCart();
                MainMenu(cart);
                break;
            case 3:
                cart.myOrders();
                MainMenu(cart);
                break;
            case 4:
                System.out.println("Thank you for choosing McBytes!");
                System.exit(0);
            default:
                System.out.println("The option you chose is invalid");
                MainMenu(cart);
        }}
        
        public static void ViewMenuCategories(Cart cart) {
        System.out.println("\n--- Choose an option ---");
        System.out.println("[1] Rice Meals");
        System.out.println("[2] Burger");
        System.out.println("[3] Beverages");
        System.out.println("[4] Sides & Desserts");

        System.out.print("\nChoose your category: ");
        int category = input.nextInt();

        switch (category) {
            case 1:
                displayRiceMeals(cart);
                break;
            case 2:
                displayBurgers(cart);
                break;
            case 3:
                displayBeverages(cart);
                break;
            case 4:
                displaySidesAndDesserts(cart);
                break;
            default:
                System.out.println("Invalid category.");
                ViewMenuCategories(cart);
        }
    }
        
        // DISPLAY RICE MEALS OPTION
        public static void displayRiceMeals(Cart cart) {
        MenuItem[] riceMeals = {
            new MenuItem("Fish & Rice Meal", 212.00),
            new MenuItem("Spicy Chicken & Rice", 179.00),
            new MenuItem("1-pc Chicken & Rice", 179.00),
            new MenuItem("Mushroom Steak & Rice", 160.00)
        };
        
        displayMenuItems(cart, riceMeals);
    }
        
        // DISPLAY BURGERS OPTION
        public static void displayBurgers(Cart cart) {
        MenuItem[] burgers = {
            new MenuItem("Cheesy Burger", 160.00),
            new MenuItem("Spicy Fillet Burger", 220.00),
            new MenuItem("Crispy Chicken Sandwich BLT", 166.00),
            new MenuItem("Quarter Pounder B.L.T.", 271.00)
        };
        
        displayMenuItems(cart, burgers);
    }
        
        // DISPLAY BEVERAGES 
        public static void displayBeverages(Cart cart) {
                
        MenuItem[] beverages = {
            new MenuItem("Water in a bottle", 20.00),
            new MenuItem("Coke in can", 35.00),
            new MenuItem("Sprite", 35.00),
            new MenuItem("Pepsi", 35.00),
            new MenuItem("Iced Coffee", 55.00),
            new MenuItem("Iced Caramel Coffee", 65.00)
        };
        
        displayMenuItems(cart, beverages);
    }
         
        // DISPLAY SIDES AND DESSERTS OPTION
        public static void displaySidesAndDesserts(Cart cart) {
        MenuItem[] sidesAndDesserts = {
            new MenuItem("Hashbrown", 37.00),
            new MenuItem("4pcs. Chicken Nuggets", 147.00),
            new MenuItem("10pcs. Chicken Nuggets", 210.00),
            new MenuItem("Sundae - Vanilla", 43.00),
            new MenuItem("Sundae - Chocolate", 43.00),
            new MenuItem("Sundae - Strawberry", 43.00),
            new MenuItem("Sundae - Caramel", 43.00),
            new MenuItem("Apple Pie", 43.00)
        };
        
        displayMenuItems(cart, sidesAndDesserts);
    }
        
        // TO LET THE USER CHOOSE A MEAL & TO ENTER A QUANTITY 
        public static void displayMenuItems(Cart cart, MenuItem[] items) {
        System.out.println("\n--- Menu ---");
        for (int i = 0; i < items.length; i++) {
            System.out.println("[" + (i + 1) + "] " + items[i]);
        }

        System.out.println("[0] Go Back to Categories"); 

        System.out.print("Choose your meal or go back: ");
        int choice = input.nextInt();

        if (choice >= 1 && choice <= items.length) {
            System.out.print("Enter quantity: ");
            int qty = input.nextInt();
            cart.addItem(items[choice - 1], qty);
        } else if (choice == 0) {
            ViewMenuCategories(cart);  
        } else {
            System.out.println("Invalid choice.");
        }

        MainMenu(cart);  
        }

    public static void chooseActionInCart(Cart cart){
        System.out.println("\n[1] Edit my cart");
        System.out.println("[2] Remove an item in my cart");
        System.out.println("[3] Check out"); 
        System.out.println("[4] Go back");
        
        System.out.print("\nChoose an action: ");
        int chooseInCart = input.nextInt();
        
        switch(chooseInCart){
            case 1:
                cart.editItem();
                break;
            case 2:
                cart.removeItem();
                MainMenu(cart);
                break;
            case 3:
                cart.checkOutItem();
                break;
            case 4:
                MainMenu(cart);
                break;
        }

    }
        
}




    



    

