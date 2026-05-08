package group.contactmanager2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
    private String name;
    private String surname;
    private String phone;

    public Contact(){}
    public Contact(String name, String surname, String phone){
        this.name=name;
        this.surname=surname;
        this.phone=phone;
    }
    @Override
    public String toString() {
        return name +" "+  surname + " "+phone;
    }
}