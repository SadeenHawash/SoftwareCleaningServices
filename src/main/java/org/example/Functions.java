package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Functions {
    static Printing printing = new Printing();
    Scanner scanner = new Scanner(System.in);
    Customer customer;
    static Customer customer2 = new Customer();
    int choice;
    static int n;
    String tmp;
    boolean found;
    Admin admin = new Admin();
    private static final String ENTER_NAME = "Enter New Name: ";
    static final String ORDER_FILE_NAME = "orders.txt";
    static final String CUSTOMER_FILE_NAME = "View.txt";
    static final String PRODUCT_FILE_NAME = "products.txt";
    static final String SPACE = "|                                      |";
    static final String ENTER_CHOICE = "Enter your choice: ";
    static final String ENTER_PASSWORD= "\nEnter Password ";
    static final String INVALID_CHOICE = "Invalid choice! Please enter a valid choice.";
    static final String LINE = "----------------------------------------";
    private static final String VIEW_FILE = "C:\\Users\\sadee\\IdeaProjects\\javaProjects\\SoftwarePro\\View.txt";
    private static final String PRODUCT_FILE= "C:\\Users\\sadee\\IdeaProjects\\javaProjects\\SoftwarePro\\products.txt";
    private String id;
    private String password;
    private final ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    final String[] workersPasswords = new String[]{"12345","12543","76895","43097","55632"};
    final String[] workersNames = new String[]{"Ahmad","Mohammad","Jalal","Ali","Raed"};
    final String[] workersPhones = new String[]{"0599286453","0589394912","0569100453","0599230220","0589794966"};
    final String[] workersAddress = new String[]{"Nablus/Fatayer Street","Nablus/Rafedia Street","Ramallah","Ramallah","Nablus/IbnKhaldoon Street"};
    public ArrayList<Order> getOrders(){
        return orders;
    }
    public List<Worker> workerList(){
        Worker worker ;
        List<Worker> workers1 = new ArrayList<>();
        for(int i=0;i< 5;i++){
            worker = new Worker();
            worker.setId(String.valueOf(i+1));
            worker.setPassword(workersPasswords[i]);
            worker.setName(workersNames[i]);
            worker.setPhone(workersPhones[i]);
            worker.setAddress(workersAddress[i]);
            worker.setStatus("Available");
            workers1.add(worker);
            //addWorkerToFile(worker);
        }
        return workers1;
    }
    final List<Worker> workers = workerList();
    DistributeOrders distributor = new DistributeOrders("orders.txt","workers.txt");
    void distribute(){
        updateOrdersList();
        updateCustomersList();
        for (Worker worker:workers){
            distributor.addAvailableWorker(worker);
        }
        for (Order order:orders){
            distributor.addWaitingOrder(order);
        }
        distributor.distributeOrders();
        for (Worker worker : workers) {
            printing.printSomething("\nWorker ID: " + worker.getId() + ", Name: " + worker.getName()+
                    "\nWorker status: " + worker.getStatus()+"\n\tWorker orders:");
            for (Order order : worker.getOrders()) {
                printing.printSomething("\t\t- Customer ID: " + order.getCustomerId() +", Order ID: " + order.getOrderId() + ", Status: " + order.getStatus());
            }
        }
    }
    boolean searchId(String id) {
        boolean f = false;
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(id)) {
                    f = true;
                }
            }
        } catch (IOException e) {
            printing.printSomething("Error: " + e.getMessage());
        }
        return f;
    }
    void addCustomerToFile(Customer customer) {
        try {
            FileWriter customersFile = new FileWriter(CUSTOMER_FILE_NAME, true);
            customersFile.append(customer.getId()).append(" , ")
                    .append(customer.getName()).append(" , ")
                    .append(customer.getPhone()).append(" , ")
                    .append(customer.getAddress()).append(" , ")
                    .append(customer.getEmail()).append(" , ")
                    .append(customer.getPassword()).append(" , ")
                    .append(String.valueOf(customer.getNumberOfOrders())).append("\n");

            customersFile.close();
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }
    void addOrderToFile(Order order) {
        try {
            String orderFile = "C:\\Users\\sadee\\IdeaProjects\\javaProjects\\SoftwarePro\\orders.txt";
            FileWriter ordersFile = new FileWriter(orderFile, true);
            ordersFile.append(order.getCustomerId()).append(" , ")
                    .append(String.valueOf(order.getOrderId())).append(" , ")
                    .append(order.assignedWorker.getId()).append(" , ")
                    .append(order.assignedWorker.getName()).append(" , ")
                    .append(String.valueOf(order.getStatus())).append(" , ")
                    .append(String.valueOf(order.getTotalPrice())).append("\n");
            ordersFile.close();
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }
    void addProductToFile(Product product) {
        try (FileWriter productsFile = new FileWriter(PRODUCT_FILE, true)) {
            productsFile.append(product.getCustomerId()).append(" , ")
                    .append(String.valueOf(product.getOrderId())).append(" , ")
                    .append(product.getName()).append(" , ")
                    .append(product.getMaterial()).append(" , ")
                    .append(String.valueOf(product.getArea())).append(" , ")
                    .append(product.getTreatment()).append(" , ")
                    .append(product.getPicture()).append("\n");
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }
    public void addWorkerToFile(Worker worker) {
        try {
            String workerFile = "C:\\Users\\sadee\\IdeaProjects\\javaProjects\\SoftwarePro\\workers.txt";
            FileWriter workersFile = new FileWriter(workerFile, true);
            workersFile.append(worker.getId()).append(" , ")
                    .append(worker.getName()).append(" , ")
                    .append(worker.getPhone()).append(" , ")
                    .append(worker.getStatus()).append(" , ")
                    .append(worker.getAddress()).append(" \n");

            workersFile.close();
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }
    void customerSignUp() throws IOException {
        customer = new Customer();
        printing.printSomething("Enter your Id: ");
        id = scanner.next();
        found = searchId(id);
        if (found) {
            printing.printSomething("This account is already existed, Please Sign in.");
            signInFunction();
        } else {
            customer.setId(id);
            printing.printSomething("Enter your Name: ");
            customer.setName(scanner.next());
            printing.printSomething("Enter your Phone: ");
            customer.setPhone(scanner.next());
            printing.printSomething("Enter your Address: ");
            customer.setAddress(scanner.next());
            printing.printSomething("Enter your Email: ");
            customer.setEmail(scanner.next());
            printing.printSomething("\nThank you! Your information have been recorded"+"\nEnter a password: ");
            customer.setPassword(scanner.next());
            printing.printSomething("\nRegistration done successfully\n");
            customers.add(customer);
            addCustomerToFile(customer);
        }
    }
    void inputs() {
        printing.printSomething("Enter Id: ");
        id = scanner.next();
        printing.printSomething(ENTER_PASSWORD);
        password = scanner.next();
    }
    int x =1;
    void adminPage() throws IOException {
        while (x > 0) {
            adminList();
            printing.printSomething(ENTER_CHOICE);
            int c = scanner.nextInt();
            if (c == 1) {
                viewCustomersAndWorkers();
            } else if (c == 2) {
                viewBusinessStatistics();
            } else if (c == 3) {
                viewBusinessReports();
            } else if (c == 4) {
                viewAllOrders();
            } else if (c == 5) {
                adminSendEmails();
            } else if (c == 6) {
                distribute();
            } else if (c == 7) {
                signInFunction();
            } else printing.printSomething(INVALID_CHOICE);
        }
    }
    private void adminSendEmails() {
        String[] to = {"ayatd42@gmail.com", "sadeenghawash593@gmail.com"};
        String subject = "Testing Subject";
        String body = "Dear customer, "
                + "\n\n Your Order is ready for Collection,\n an invoice sent to you, Please Check invoices section\n," +
                "Payment when receiving.";
        NotifyCustomer.sendEmail(admin.getEmail(), admin.getEmailPassword(), to, subject, body);
    }
    private void viewBusinessReports() {
        updateCustomersList();
        updateOrdersList();
        tmp = "=================Reports================="+"\nThe  number of customers " + customers.size()+
                "\nThe  number of orders " + orders.size()+ "\nThe  number of workers " + workers.size()+
                "\n==========================================\n\n";
        printing.printSomething(tmp);
    }
    private void viewBusinessStatistics() {
        int totalDeliveredItems = 10;
        double totalCash = 5000;
        double totalDiscount = 500;
        double totalPaid = 4500;
        double totalDebts = 0.0;
        tmp = "=================statistics=================\n"+"============================================\n"+
                "-->Total Delivered Items: " + totalDeliveredItems+"\n-->Total Cash: $" + totalCash+"\n-->Total Discount: $" + totalDiscount+
                "\n-->Total Paid: $" + totalPaid+"\n-->Total Debts: $" + totalDebts+"\n============================================\n\n";
        printing.printSomething(tmp);
    }
    private void viewCustomersAndWorkers() {
        updateCustomersList();
        printing.printSomething("List of Customers: \n");
        for (Customer customer1 : customers) {
            ArrayList<Order> orders1 = (ArrayList<Order>) getOrdersFromFile(ORDER_FILE_NAME, customer1.getId());
            tmp = customer1.getId() + "\t  "+customer1.getName() + "  "+customer1.getAddress() + "  "+customer1.getPhone() + "  "+customer1.getEmail() + "  "+orders1.size() + "  \n";
            printing.printSomething(tmp);
        }
        printing.printSomething("-----------------------------------------------------------------------\n"+"List of Workers: \n");
        for (Worker worker : workers) {
            printing.printSomething(worker.getId() + "\t  "+worker.getName() + "  "+worker.getAddress() + "  "+worker.getPhone() + "  "+worker.getStatus() + "  \n");
        }
    }
    private void viewAllOrders() {
        updateCustomersList();
        for (Customer customer1 : customers) {
            orders = (ArrayList<Order>) getOrdersFromFile(ORDER_FILE_NAME, customer1.getId());
            tmp ="-----------------------------------------------\n"+customer1.getId() + "  "+customer1.getName() + "  "+customer1.getAddress() + "  "+customer1.getPhone() + "  "+customer1.getEmail() + "  "+orders.size() + "  \n";
            printing.printSomething(tmp);
            for (Order order : orders) {
                printing.printSomething("\n"+order.getOrderId() + "  " +order.getStatus() + "  "+order.getTotalPrice() + "  \n");
                products = (ArrayList<Product>) getProductsFromFile(PRODUCT_FILE_NAME, customer1.getId(), String.valueOf(order.getOrderId()));
                for (Product product : products) {
                    printing.printSomething(product.getName() + "  "+product.getMaterial() + "  "+product.getArea() + "  "+product.getTreatment() + "  "+product.getPicture() + "  \n");
                }
            }
            printing.printSomething("\n\n");
        }
    }
    void signInFunction() throws IOException {
        int c;
        signInPageList();
        printing.printSomething(ENTER_CHOICE);
        choice = scanner.nextInt();
        printing.printSomething("\nEnter Id: ");
        id = scanner.next();
        printing.printSomething("Enter password: ");
        password = scanner.next();
        switch (choice){
            case 1:
                if (id.equals(admin.getAdminId()) && password.equals(admin.getAdminPassword())) {
                    adminPage();
                } else {
                    printing.printSomething("\nSomething went wrong!, Try again.");
                    inputs();
                }
                break;
            case 2:
                 boolean found1 = false;
                updateCustomersList();
                for(Customer customer1: customers){
                    if (id.equals(customer1.getId()) && password.equals(customer1.getPassword())) {
                        found1= true;
                        break;
                    }
                }
                if(found1){
                    while (x>0) {
                        customerPageList();
                        c = scanner.nextInt();
                        customerOptions(c);
                    }
                }
                else{
                    printing.printSomething("\nThis account is not exist, Please Sign up.\n");
                    customerSignUp();
                }
                break;
            case 3:
                for(Worker worker: workers){
                    if(worker.getId().equals(id)){
                        if(worker.getPassword().equals(password)) {
                            while (x > 0) {
                                workerPageList();
                                c = scanner.nextInt();
                                workerOptions(c);
                            }
                        }else{
                            printing.printSomething("\nSigning in failed, Please check your entered password\n");
                            printing.printSomething(ENTER_PASSWORD);
                            password = scanner.next();
                        }
                    }else {
                        printing.printSomething("\nThis account is not exist, Please check the inputs.\n");
                    }
                }
                break;

            default:    printing.printSomething("\n"+INVALID_CHOICE);

        }
    }
    void updateCustomersList() {
        String line;
        customers.clear();
        FileReader customersFileReader;
        try {
            customersFileReader = new FileReader(CUSTOMER_FILE_NAME);
            BufferedReader lineReader = new BufferedReader(customersFileReader);
            while ((line = lineReader.readLine()) != null) {
                if (line.isEmpty()) continue;
                customers.add(Customer.getCustomerFromLine(line));
            }
            lineReader.close();
            customersFileReader.close();
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }
    public void updateOrdersList() {
        String orderFile = "C:\\Users\\sadee\\IdeaProjects\\javaProjects\\SoftwarePro\\orders.txt";
        String line;
        orders.clear();
        FileReader ordersFileReader;
        try {
            ordersFileReader = new FileReader(orderFile);
            BufferedReader lineReader = new BufferedReader(ordersFileReader);
            while ((line = lineReader.readLine()) != null) {
                if (line.isEmpty()) continue;
                orders.add(Order.getOrderFromLine(line));
            }
            lineReader.close();
            ordersFileReader.close();
        } catch (IOException e){
            printing.printSomething("An error occurred: " + e.getMessage());
        }

    }
    public static void updateFile(String filePath, String oldValue, String newValue) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        String line;
        long lastPos = 0;
        while ((line = file.readLine()) != null) {
            if (line.contains(oldValue)) {
                String updatedLine = line.replace(oldValue, newValue);
                file.seek(lastPos);
                file.writeBytes(updatedLine);
            }
            lastPos = file.getFilePointer();
        }
        file.close();
    }
    public static void replaceLastValueInLine(String fileName, int lineNumber, String newValue) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line;
            int currentLine = 1;
            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber) {

                    String[] values = line.split(" , ");
                    int oldValue = Integer.parseInt(values[values.length - 1]);
                    int tmp = oldValue + Integer.parseInt(newValue);
                    values[values.length - 1] = String.valueOf(tmp);
                    line = String.join(" , ", values);
                }
                sb.append(line);
                sb.append(System.lineSeparator());
                currentLine++;
            }
            innerTry(fileName, sb);
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }
    public static void innerTry(String fileName, StringBuilder sb) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
    }
    public static int getLineIndexById(String fileName, String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int currentLine = 1;
            while ((line = reader.readLine()) != null) {
                if (line.contains(id)) {
                    return currentLine;
                }
                currentLine++;
            }
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
        return -1;
    }
    public static List<Product> getProductsFromFile(String fileName, String customerId, String orderId) {
        List<Product> products1 = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" , ");
                String custId = values[0];
                String ordId = values[1];
                String proName = values[2];
                String proMaterial = values[3];
                float proArea = Float.parseFloat(values[4]);
                String proTreatment = values[5];
                String proPic = values[6];
                if (custId.equals(customerId) && ordId.equals(orderId)) {
                    products1.add(new Product(proName, proMaterial, proArea, proTreatment, proPic));
                }
            }
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
        return products1;
    }
    public static List<Order> getOrdersFromFile(String fileName, String customerId) {
        ArrayList<Order> orders1 = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" , ");
                String custId = values[0];
                int ordId = Integer.parseInt(values[1]);
                String workerId = values[2];
                String workerName = values[3];
                String status = values[4];
                double total = Double.parseDouble(values[5]);
                if (custId.equals(customerId)) {
                    orders1.add(new Order(ordId, workerName, workerId, total, status));
                }
            }
        } catch (IOException e) {
            printing.printSomething("An error occurred: " + e.getMessage());
        }
        return orders1;
    }
    public void customerOptions(int x) throws IOException {
        switch (x){
            case 1:
                updateCustomersList();
                printing.printSomething("Which info you want to update?\n1. Name  2.Phone  3. Address  4. Email"+"\n"+ENTER_CHOICE);
                updateCustomerProfile(scanner.nextInt());
                break;
            case 2:
                updateOrdersList();
                n = orders.size();
                updateCustomersList();
                customer2.setId(id);
                int l = getLineIndexById(CUSTOMER_FILE_NAME, id);
                System.out.println(l);
                do {
                    n++;
                    Order order = new Order(n);
                    order.setStatus(Order.Status.WAITING);
                    order.setCustomerId(id);
                    do {
                        Product product = new Product();
                        printing.printSomething("-----Product Information-----\n"+"Enter Name [Carpet,Cover]: ");
                        product.setName(scanner.next());
                        printing.printSomething("Enter Material [Wool,Nylon,Olefin,Polyester,Triexta,Cotton,Microfiber,Velvet]: ");
                        product.setMaterial(scanner.next());
                        printing.printSomething("Enter Area [Height X Width]: ");
                        float area = scanner.nextFloat();
                        product.setArea(area);
                        printing.printSomething("Is required special treatment? [Yes,No]: ");
                        product.setTreatment(scanner.next());
                        printing.printSomething("Enter picture [picture path]: ");
                        product.setPicture(scanner.next());
                        product.setCustomerId(id);
                        product.setOrderId(n);
                        order.addProduct(product);
                        double t = area * 10;
                        order.setTotalPrice(t);
                        products.add(product);
                        addProductToFile(product);
                        printing.printSomething("Add another product? [Yes,No]: ");
                    } while (!scanner.next().equalsIgnoreCase("No"));
                    customer2.addOrder(order);
                    orders.add(order);
                    customer2.setNumberOfOrders(orders.size());
                    addOrderToFile(order);
                    printing.printSomething("Add another order? [Yes,No]: ");
                } while (!scanner.next().equalsIgnoreCase("No"));
                replaceLastValueInLine(CUSTOMER_FILE_NAME, l, String.valueOf(customer2.getOrders().size()));
                break;
            case 3:
                printing.printSomething("\n\n");
                break;
            case 4:
                printing.printSomething("\n");
                break;
            case 5:
                updateCustomersList();
                for (Customer customer1 : customers) {
                    if (customer1.getId().equals(id)) {
                        printing.printSomething(LINE+"\n|                INVOICE               |\n"+LINE);
                        Invoice invoice = new Invoice(customer1);
                        invoice.invoiceRes(customer1);
                        printing.printSomething(LINE);
                    }
                }
                break;
            case 6:
                printing.printSomething("\t\t\n--- Delete profile! ---\n\nUr info will be Deleted & ur orders will be cancelled!!\nAre you sure? ");
                String str = scanner.next();
                deleteCustomerProfile(str);
                break;
            case 7:
                signInFunction();
                break;
            default: printing.printSomething(INVALID_CHOICE);
        }
    }
    public void deleteCustomerProfile(String val) throws IOException {
        if(val.equalsIgnoreCase("yes")){
            deleteLineByValue(CUSTOMER_FILE_NAME,id);
            deleteLineByValue(ORDER_FILE_NAME,id);
            deleteLineByValue(PRODUCT_FILE_NAME,id);
            printing.printSomething("\nAccount Successfully Deleted\n\n");
        }
    }
    public void updateProduct(int n) throws IOException {
        for (Product product : products) {
            switch (n){
                case 1:
                    printing.printSomething(ENTER_NAME);
                    tmp = scanner.next();
                    updateFile(PRODUCT_FILE, product.getName(), tmp);
                    product.setName(tmp);
                    break;
                case 2:
                    printing.printSomething("Enter New Material: ");
                    tmp = scanner.next();
                    updateFile(PRODUCT_FILE, product.getMaterial(), tmp);
                    product.setMaterial(tmp);
                    break;
                case 3:
                    printing.printSomething("Enter New Area: ");
                    tmp= scanner.next();
                    updateFile(PRODUCT_FILE, String.valueOf(product.getArea()), tmp);
                    product.setArea(Float.parseFloat(tmp));
                    break;
                default:    printing.printSomething(INVALID_CHOICE);
            }
        }
    }
    public void updateCustomerProfile(int n) throws IOException {
        String tmp1;
        for (Customer customer1 : customers) {
            if (customer1.getId().equals(id)) {
                switch (n){
                    case 1:
                        printing.printSomething(ENTER_NAME);
                        tmp1 = scanner.next();
                        updateFile(VIEW_FILE, customer1.getName(), tmp1);
                        customer1.setName(tmp1);
                        break;
                    case 2:
                        printing.printSomething("Enter New Phone: ");
                        tmp1 = scanner.next();
                        updateFile(VIEW_FILE, customer1.getPhone(), tmp1);
                        customer1.setPhone(tmp1);
                        break;
                    case 3:
                        printing.printSomething("Enter New Address: ");
                        tmp1= scanner.next();
                        updateFile(VIEW_FILE, customer1.getAddress(), tmp1);
                        customer1.setAddress(tmp1);
                        break;
                    case 4:
                        printing.printSomething("Enter New Email: ");
                        tmp1 = scanner.next();
                        updateFile(VIEW_FILE, customer1.getEmail(), tmp1);
                        customer1.setEmail(tmp1);
                        break;
                    default:    printing.printSomething(INVALID_CHOICE);
                }
            }
        }
    }
    public void updateWorkerProfile(int n) throws IOException {
        String fileWorker = "C:\\Users\\sadee\\IdeaProjects\\javaProjects\\SoftwarePro\\workers.txt";
        String tmp2;
        for (Worker worker : workers) {
            if (worker.getId().equals(id)) {
                if (n == 1) {
                    printing.printSomething(ENTER_NAME);
                    tmp2= scanner.next();
                    updateFile(fileWorker, worker.getName(), tmp2);
                    worker.setName(tmp2);
                } else if (n == 2) {
                    printing.printSomething("Enter New Phone: ");
                    tmp2 = scanner.next();
                    updateFile(fileWorker, worker.getPhone(), tmp2);
                    worker.setPhone(tmp2);
                } else if (n == 3) {
                    printing.printSomething("Enter New Address: ");
                    tmp2 = scanner.next();
                    updateFile(fileWorker, worker.getAddress(), tmp2);
                    worker.setAddress(tmp2);
                } else printing.printSomething(INVALID_CHOICE);
            }
        }
    }
    public void workerOptions(int n) throws IOException {
        switch (n){
            case 1:
                printing.printSomething("Which info you want to update?\n1. Name  2.Phone  3. Address "+"\n"+ENTER_CHOICE);
                int c = scanner.nextInt();
                updateWorkerProfile(c);
                break;
            case 2:
                updateOrdersList();
                for (Worker worker:workers){
                    distributor.addAvailableWorker(worker);
                }
                for (Order order:orders){
                    distributor.addWaitingOrder(order);
                }
                distributor.distributeOrders();
                for (Worker worker : distributor.getAvailableWorkers()) {
                    if(worker.getId().equals(id)){
                        printing.printSomething("\nWorker ID: " + worker.getId() + ", Name: " + worker.getName()+
                                "\nWorker status: " + worker.getStatus()+"\n\tWorker orders:");
                        for (Order order : worker.getOrders()) {
                            printing.printSomething("\t\t- Customer ID: " + order.getCustomerId() +", Order ID: " + order.getOrderId() + ", Status: " + order.getStatus());
                        }
                    }
                }
                printing.printSomething("\n\n");
                break;
            case 3:
                printing.printSomething("\n\n");
                break;
            case 4:
                signInFunction();
                break;
            default:    printing.printSomething(INVALID_CHOICE);
        }
    }
    public void adminList(){
        printing.printSomething("\n--------- Welcome to Admin Page --------\n"+SPACE+
                "\n|   1. View Customer/Product/Workers   |"+"\n|   2. View Business Statistics        |"+
                "\n|   3. View Business Reports           |"+"\n|   4. View All Orders                 |"+
                "\n|   5. Notify Customer By Email        |"+"\n|   6. View Workers with their missions|"+
                "\n|   7. Log Out                         |\n"+SPACE+"\n"+LINE+"\n"
        );
    }
    public void signInPageList(){
        printing.printSomething("\n---------- Sign in Page ----------"+"\n|                                |"+
                "\n|        1. Administrator        |"+"\n|        2. Customer             |"+
                "\n|        3. Worker               |"+"\n|                                |"+
                "\n----------------------------------\n" );
    }
    public void customerPageList(){
        printing.printSomething("\n------- Welcome to Customer Page -------\n"+SPACE+"\n|        1. Update My Profile          |"+
                "\n|        2. Make An Order              |"+"\n|        3. Update Order               |"+
                "\n|        4. Cancel Order               |"+ "\n|        5. Invoices                   |"+
                "\n|        6. Delete My Profile          |"+"\n|        7. Log Out                    |\n"+SPACE+"\n"+LINE+"\n"
                +ENTER_CHOICE );
    }
    public void workerPageList(){
        printing.printSomething("\n-------- Welcome to Worker Page --------\n"+SPACE+
                "\n|        1. Update My Profile          |"+"\n|        2. View My Missions           |"+
                "\n|        3. Update Order Status        |"+"\n|        4. Log Out                    |\n"+
                SPACE+ "\n----------------------------------------\n"+ENTER_CHOICE);
    }
    public static void deleteLineByValue(String filePath, String value) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(value)) {
                    sb.append(line).append("\n");
                }
            }

            writer.write(sb.toString());
        }
    }

}
