package group.contactmanager2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
    private String name;
    private String surname;
    private String phone;

    @Override
    public String toString() {
        return name +" "+  surname + " "+phone;
    }
}