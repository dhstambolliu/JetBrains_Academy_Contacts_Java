package contacts.phoneBook;

import contacts.domain.Contact;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PhoneBook {
    private final ArrayList<Contact> phoneBook;

    public PhoneBook() {
        phoneBook = loadFromFile();
    }

    public void add(Contact contact) {
        phoneBook.add(contact);
    }

    public int size() {
        return phoneBook.size();
    }

    public void print() {
        print(phoneBook);
    }

    public void print(ArrayList<Contact> contacts) {
        int i = 1;
        for (Contact c : contacts) {
            System.out.println(i + ". " + c.print());
            i++;
        }
    }

    public void remove(int index) {
        phoneBook.remove(index - 1);
        saveToFile();
    }

    public Contact get(int index) {
        return phoneBook.get(index - 1);
    }

    public void editContact(int i, String field, String value) {
        Contact contact = get(i);
        contact.setEditDateTime(LocalDateTime.now().withSecond(0).withNano(0));
        switch (field) {
            case "name":
                contact.setName(value);
                break;
            case "surname":
                contact.setSurname(value);
                break;
            case "number":
                contact.setNumber(value);
                break;
            case "birth":
                contact.setBirthDay(value);
                break;
            case "gender":
                contact.setGender(value);
            case "address":
                contact.setAddress(value);
                break;
        }
        saveToFile();
        System.out.println("The record updated!");
    }

    public ArrayList<Contact> search(String phrase) {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact c : phoneBook) {
            if (c.toString().toLowerCase().contains(phrase.toLowerCase())) {
                contacts.add(c);
            }
        }
        return contacts;
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("phonebook.db")));) {
            oos.writeObject(phoneBook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Contact> loadFromFile() {
        ArrayList<Contact> contacts;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("phonebook.db")));
            contacts = (ArrayList<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            contacts = new ArrayList<>();
            e.printStackTrace();
        }
        return contacts;
    }
}