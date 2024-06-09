import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Contact extends MainJava{
    private String firstName;
    private String lastName;
    private long phoneNum;
    private String email;
    private String address;

    public Contact(String firstName, String lastName, long phoneNum, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
    }

    public static void saveData(String firstName, String lastName, String phoneNumber, String email, String address) {
    	 firstName = capitalizeFirstLetter(firstName);
         lastName = capitalizeFirstLetter(lastName);
        try {
        	
        	boolean userExist = false;
        	 if (!userExist) {
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\jwces\\OneDrive\\Desktop\\New folder (2)\\contacts.txt"));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    
                    if (data.length == 5 && data[0].trim().equalsIgnoreCase(firstName) && 
                        data[1].trim().equalsIgnoreCase(lastName)) {
                    	userExist = true;
                        break;
                    }
                }
                
                reader.close();

                if (userExist) {
                    System.out.println("User already exists. Contact not created.");
                } else {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\jwces\\OneDrive\\Desktop\\New folder (2)\\contacts.txt", true));
                    bw.write(firstName + "," + lastName + "," + phoneNumber + "," + email + "," + address);
                    bw.newLine();
                    bw.close();
                    System.out.println("Contact successfully created.");
                }
            }
         	
        } catch (IOException e) {
            System.out.println("Error, "); 
            e.printStackTrace();
        }
    }
    public static String capitalizeFirstLetter(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

  
    public static void searchData(String firstname, String lastname) {
        try {
        	BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\jwces\\OneDrive\\Desktop\\New folder (2)\\contacts.txt"));
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 5 && data[0].trim().equalsIgnoreCase(firstname) && 
                		data[1].trim().equalsIgnoreCase(lastname)) {
                	System.out.println();
                	System.out.println("----USER INFORMATION----");
                    System.out.println("Name: " + data[0] + " " + data[1]);
                    System.out.println("Phone number: " + data[2]);
                    System.out.println("Email: " + data[3]);
                    System.out.println("Address: " + data[4]);
                    found = true;
                }
            }

            if (!found) {
                System.out.println(firstname.toUpperCase() + " " + lastname.toUpperCase() + " does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void displayData() {
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\jwces\\OneDrive\\Desktop\\New folder (2)\\contacts.txt"));
    		String line;
    		int userCount = 0;
    		boolean userExist = true;
    		while ((line = reader.readLine()) != null) {
    			String[] data = line.split(",");
    			userCount++;
    			System.out.println("Contact NO. " + userCount);
    			System.out.println("Name: " + data[0] + " " + data[1]);
                System.out.println("Phone number: " + data[2]);
                System.out.println("Email: " + data[3]);
                System.out.println("Address: " + data[4]);
                System.out.println("--------------------------");
               
    		}
    		reader.close();
    	} catch (Exception e) {
			System.out.println("Can't read data or data doesn't exist.");
		}
    	
    }
   
    public static void updateData(String searchFirstName, String searchLastName, String newFirstName, String newLastName, String newPhoneNumber, String newEmail, String newAddress) {
        try {
            newFirstName = capitalizeFirstLetter(newFirstName);
            newLastName = capitalizeFirstLetter(newLastName);
            File file = new File("contacts.txt");
            File tempFile = new File("temp.txt");
    
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
            String line;
            boolean userFound = false;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String firstName = data[0].trim();
                String lastName = data[1].trim();
    
                if (firstName.equalsIgnoreCase(searchFirstName) && lastName.equalsIgnoreCase(searchLastName)) {
                    data[0] = newFirstName;
                    data[1] = newLastName;
                    data[2] = newPhoneNumber;
                    data[3] = newEmail;
                    data[4] = newAddress;
    
                    userFound = true;
                }
    
                writer.write(String.join(",", data) + "\n");
            }
            reader.close();
            writer.close();
    
            if (!userFound) {
                System.out.println(searchFirstName.toUpperCase() + " " + searchLastName.toUpperCase() + " does not exist.");
                return;
            }
    
            if (file.delete() && tempFile.renameTo(file)) {
                System.out.println("User data updated successfully.");
            } else {
                System.out.println("Failed to update user data.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    
    
    public static void deleteUserData(String deleteFirstName, String deleteLastName) {
        try {
            File file = new File("contacts.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean userFound = false;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String firstName = data[0].trim();
                String lastName = data[1].trim();

                if (firstName.equalsIgnoreCase(deleteFirstName) && lastName.equalsIgnoreCase(deleteLastName)) {
                    userFound = true;
                } else {
                    writer.write(line);
                }
            }

            reader.close();
            writer.close();

            if (userFound) {
              
                if (file.delete() && tempFile.renameTo(file)) {
                    System.out.println("User data deleted successfully.");
                } else {
                    System.out.println("Failed to delete user data.");
                }
            } else {
                System.out.println(deleteFirstName.toUpperCase() + " " + deleteLastName.toUpperCase() + " is not found in the data file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deleteAllUserData() {
        File file = new File("contacts.txt");
    
        try {
            FileWriter fw = new FileWriter(file);
            fw.flush();
            fw.close();
    
            BufferedReader br = new BufferedReader(new FileReader("contacts.txt"));
    
            String line;
            while ((line = br.readLine()) == null) {
                System.out.println("All data have been deleted.");
                break;
            }
            br.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}