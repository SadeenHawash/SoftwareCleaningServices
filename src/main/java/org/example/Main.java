package org.example;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws  IOException {
        Functions functions = new Functions();
        Scanner scanner = new Scanner(System.in);
        Logger logger = Logger.getLogger(" ");
        while (true){
            logger.info("------ Welcome to Home Page ------"+"\n|                                |"+
                    "\n|          1. Sign up            |"+"\n|          2. Sign in            |"+
                    "\n|          3. Exit               |"+"\n|                                |"+
                    "\n----------------------------------\n"+"Enter your choice: ");
            while (!scanner.hasNextInt()) {
                logger.info("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
            functions.choice = scanner.nextInt();
            if(functions.choice ==1 ) functions.customerSignUp();
            else if(functions.choice == 2 )functions.signInFunction();
            else if(functions.choice == 3) System.exit(0);
            else logger.info("Invalid choice! Please enter a valid choice.");
        }
    }
}