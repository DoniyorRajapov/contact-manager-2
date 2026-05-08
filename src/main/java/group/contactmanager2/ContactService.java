package group.contactmanager2;

import java.util.List;

public class ContactService {
    ContactRepository contactRepository;

    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public  void add(Contact contact){
        int result =contactRepository.add(contact);
        if(result==1) System.out.println("Contact saved successfully!");
        else if(result==ContactRepository.DUPLICATE_PHONE) System.out.println("This phone number is already in your contacts.");
        else if(result==ContactRepository.NULL_PHONE) System.out.println("Phone number is required.");
        else if(result<1) System.out.println("Failed to add contact due to a technical error.");
    }

    public void list() {
        List<Contact> list = contactRepository.list();
        list.forEach(System.out::println);
    }

    public void delete(String phone) {
        if(contactRepository.delete(phone)>0)
            System.out.println("Contact deleted successfully.");
        else System.out.println("This contact no longer exists.");
    }

    public void search(String query) {
        List<Contact> list = contactRepository.search(query);
        list.forEach(System.out::println);
    }
}