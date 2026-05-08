package group.contactmanager2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private Scanner scanner;

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
        String action = scanner.nextLine();
        if(action==null)
            return -1;
        try {
            return Integer.parseInt(action);
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