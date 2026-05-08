package group.contactmanager2;

import java.util.Scanner;

public class ContactController {
    private ContactService contactService;
    private Scanner scanner;

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    public void start(){
        boolean b=true;
        while(b){
            menu();
            int action = action();
            switch(action){
                case 1 -> add();
                case 2 -> list();
                case 3 -> delete();
                case 4 -> search();
                case 0 -> b=false;
                default -> System.out.println("Invalid command. Please try again.");
            }
        }
    }

    public void menu(){
        System.out.println("""
                1. Add Contact
                2. Contact List
                3. Delete Contact
                4. Search Contact By
                0. Exit""");
    }

    public int action(){
        System.out.print("Enter action: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        }
        catch(NumberFormatException e){
            return -1;
        }
    }

    public void add(){
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        Contact contact = new Contact();
        contact.setName(name);
        contact.setSurname(surname);
        contact.setPhone(phone);

        contactService.add(contact);
    }

    public void list(){
        contactService.list();
    }

    public  void delete(){
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        contactService.delete(phone);
    }

    public void search(){
        System.out.print("Enter query: ");
        String query = scanner.nextLine();

        contactService.search(query);
    }
}