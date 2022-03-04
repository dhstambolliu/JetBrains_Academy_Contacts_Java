package contacts.userInterface;

import contacts.domain.Contact;
import contacts.domain.ContactCompany;
import contacts.domain.ContactPerson;
import contacts.phoneBook.PhoneBook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final PhoneBook contacts;

    public UserInterface() {
        scanner = new Scanner(System.in);
        contacts= new PhoneBook();
    }

    public void start(){

        label:
        while (true) {
            System.out.println("[menu]Enter action (add, list, search, count, exit):");
            String input = scanner.nextLine();
            switch (input){
                case "add":
                    System.out.println("Enter the type (person, organization):");
                    String add = scanner.nextLine();
                    if("person".equals(add)){
                        addPerson();
                    }else if("organization".equals(add)){
                        addOrganization();
                    }
                    break;
                case "list":
                    listUI();
                    contacts.print();
                    break;
                case "count":
                    System.out.println("The Phone Book has "+ contacts.size()+ " records.");
                    break;
                case "search":
                    searchUI();
                    break;
                case "exit":
                    break label;
            }
            System.out.println();
        }
    }

    private void listUI(){
        contacts.print();
        while (true) {
            System.out.println("\n[list] Enter action ([number], back):");
            String input = scanner.nextLine();
            if ("back".equals(input)) {
                break;
            }
            try {
                int i = Integer.parseInt(input);
                recordUI(i);
                break;
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

    private void searchUI(){
        System.out.println("Enter search query:");
        ArrayList<Contact> c= contacts.search(scanner.nextLine());
        System.out.println("Found "+c.size()+" results:");
        contacts.print(c);

        while (true){
            System.out.println("\n[search] Enter action ([number], back, again):");
            String input = scanner.nextLine();
            if ("back".equals(input)) {
                break;
            } else if ("again".equals(input)) {
                System.out.println("Enter search query:");
                c= contacts.search(scanner.nextLine());
                System.out.println("Found "+c.size()+" results:");
                contacts.print(c);
            }else{
                try {
                    int i = Integer.parseInt(input);
                    recordUI(i);
                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void recordUI(int i){
        System.out.println(contacts.get(i));
        label:
        while (true){
            System.out.println("\n[record] Enter action (edit, delete, menu):");
            String input = scanner.nextLine();
            switch (input){
                case "edit":
                    editUI(i);
                    break;
                case "delete":
                    contacts.remove(i);
                    break;
                case "menu":
                    break label;
            }
        }
    }

    private void editUI(int i){
        if (contacts.get(i).getClass()==ContactPerson.class) {
            System.out.println("Select a field (name, surname, birth, gender, number):");
            String field = scanner.nextLine();
            String value = getValueUI(field);
            contacts.editContact(i,field,value);
        }else if(contacts.get(i).getClass()== ContactCompany.class){
            System.out.println("Select a field (name, address, number):");
            String field = scanner.nextLine();
            String value = getValueUI(field);
            contacts.editContact(i, field,value);
        }
        System.out.println("Saved");
        System.out.println(contacts.get(i));
    }

    private String getValueUI(String field){
        switch (field){
            case "name":
                System.out.println("Enter name:");
                break;
            case "surname":
                System.out.println("Enter surname:");
                break;
            case "number":
                System.out.println("Enter number:");
                break;
            case "birth":
                System.out.println("Enter date of birth");
                break;
            case "gender":
                System.out.println("Enter gender:");
                break;
            case "address":
                System.out.println("Enter address:");
                break;
        }
        return scanner.nextLine();
    }

    private void addPerson(){
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        System.out.println("Enter the surname:");
        String surname =scanner.nextLine();
        System.out.println("Enter the birth date:");
        String DOB = scanner.nextLine();
        LocalDate date = setDate(DOB);
        System.out.println("Enter the Gender(M,F)");
        String gender = scanner.nextLine();
        if(!gender.matches("^[MF]$")){
            gender = null;
            System.out.println("Bad Gender!");
        }
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        contacts.add(new ContactPerson(name,number,surname,gender,date));
        System.out.println("The record added.");
    }

    private LocalDate setDate(String DOB) {
        LocalDate date;
        try {
            date = LocalDate.parse(DOB, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            date=null;
            System.out.println("Bad birth date!");
            e.printStackTrace();
        }
        return date;
    }

    private void addOrganization() {
        System.out.println("Enter the organization name:");
        String name = scanner.nextLine();
        System.out.println("Enter the address:");
        String address = scanner.nextLine();
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        contacts.add(new ContactCompany(name,number,address));
        System.out.println("The record added.");
    }

    private void remove(){
        System.out.println("Select a record:");
        String remove = scanner.nextLine();
        contacts.remove(Integer.parseInt(remove));
        System.out.println("The record removed!");
    }
}