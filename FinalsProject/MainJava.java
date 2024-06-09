import java.util.*;
import java.util.InputMismatchException;

public class MainJava {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
            	System.out.println("\t\t\t\t\t--------------WELCOME!-------------");
                System.out.println("Type 1 to Create Contact: ");
                System.out.println("Type 2 to Search Contact: ");
                System.out.println("Type 3 to Display All Contact: ");
                System.out.println("Type 4 to Update Contact: ");
                System.out.println("Type 5 to Delete Contact: ");
                System.out.println("Type 0 to Close the program: ");
                System.out.print("Enter your choice: ");
                int choice = input.nextInt();

                if (choice == 1) {
                	Scanner scan = new Scanner(System.in);
                	System.out.println("----CREATE CONTACT----");
                	
                    System.out.print("Enter first name: ");
                    String firstname = scan.nextLine();
                    
                    System.out.print("Enter last name: ");
                    String lastName = scan.nextLine();
                    
                    if (!firstname.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
                    	System.out.println("Error. Enter a valid name.");
                    	continue;
                    }
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scan.nextLine();
                    
                    if (!phoneNumber.matches("^09\\d{9}")) {
                    	System.out.println("MESSAGE: Error. \nEnter a number that starts with '09' and have 11 characters only.");
                    	continue;
                    }
                    
                    
                    System.out.print("Enter email: ");
                    String email = scan.nextLine();
                    
                    System.out.print("Enter address: ");
                    String address = scan.nextLine();

                    Contact.saveData(firstname, lastName, phoneNumber, email, address);
                
                    

                } else if (choice == 2) {
                	Scanner scan = new Scanner(System.in);
                	System.out.println();
                	System.out.println("-----SEARCH CONTACT------");
                    System.out.print("Enter first name: ");
                    String firstName = scan.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scan.nextLine();

                    Contact.searchData(firstName, lastName);
                    continue;
                    
                } else if (choice == 3) {
                	System.out.println("------DISPLAY CONTACTS------");
                	Contact.displayData();
                } else if (choice == 4) {
                	Scanner scan = new Scanner(System.in);
                	Contact.displayData();
                	System.out.print("Enter the first name of user that you want to EDIT/UPDATE: ");
                    String searchFirstName = scan.nextLine();
                    
                    System.out.print("Enter the last name of user that you want to EDIT/UPDATE: ");
                    String searchLastName = scan.nextLine();
                    
                    System.out.print("Enter the new/current first name: ");
                    String newFirstName = scan.nextLine();
                    
                    System.out.print("Enter the new/current last name: ");
                    String newLastName = scan.nextLine();
                    
                    if (!newFirstName.matches("[a-zA-Z]+") || !newLastName.matches("[a-zA-Z]+")) {
                    	System.out.println("Please enter a valid name.");
                    	break;
                    }
                    
                    System.out.print("Enter the new phone number: ");
                    String newPhoneNumber = scan.nextLine();
                    
                    if (!newPhoneNumber.matches("^09\\d{9}$")) {
                    	System.out.println("Please enter a phone number that starts with '09' and have only 11 characters.");
                    	continue;
                    }
                    
                    System.out.print("Enter the new email: ");
                    
                    String newEmail = scan.nextLine();
                   
                    
                    System.out.print("Enter the new address: ");
                    String newAddress = scan.nextLine();
                
                    Contact.updateData(searchFirstName, searchLastName, newFirstName, newLastName, newPhoneNumber, newEmail, newAddress);
                    continue;
                    
                } else if (choice == 5) {
                	Scanner scan = new Scanner(System.in);
                	System.out.println("------DELETE CONTACT------");
                	System.out.print("Type 1 to delete all contacts: \nType 2 to delete contact individually: ");
                	int decision = scan.nextInt();
                    switch (decision) {
                        case 1:
                            Contact.deleteAllUserData();
                            break;
                        case 2:
                        Contact.displayData();
                        System.out.print("Enter the first name of user you want to delete: ");
                        String firstname = scan.nextLine();
                        
                        System.out.print("Enter the last name of user you want to delete: ");
                        String lastname = scan.nextLine();
                        
                        Contact.deleteUserData(firstname, lastname);
                            break;
                        default:
                            break;
                    }
                    
                	
                } else if (choice == 0) {
                	System.out.println("Closing program...");
                	Thread.sleep(1000);
                	System.out.println("Program successfully terminated. Thank you for using :)");
                	System.exit(0);
               
                }
                if (choice >= 6) {
                	System.out.println("Invalid input. Please enter 1-5 (0 to exit).");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please enter 1 to 5 only (type 0 to exit).");
                input.next();
                continue;
              
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}