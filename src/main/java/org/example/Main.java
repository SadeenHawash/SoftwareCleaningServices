package org.example;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws  IOException {
        Functions functions = new Functions();
        Scanner scanner = new Scanner(System.in);
        Printing printing = new Printing();
        while (true){
            printing.printSomething("\n------ Welcome to Home Page ------"+"\n|                                |"+
                    "\n|          1. Sign up            |"+"\n|          2. Sign in            |"+
                    "\n|          3. Exit               |"+"\n|                                |"+
                    "\n----------------------------------\n"+"Enter your choice: ");
            while (!scanner.hasNextInt()) {
                printing.printSomething("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
            functions.choice = scanner.nextInt();
            if(functions.choice ==1 ) functions.customerSignUp();
            else if(functions.choice == 2 )functions.signInFunction();
            else if(functions.choice == 3) System.exit(0);
            else printing.printSomething("Invalid choice! Please enter a valid choice.");
        }
    }
}
