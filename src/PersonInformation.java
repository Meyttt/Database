import java.io.Serializable;

/**
 * Created by Александра on 07.01.2017.
 */
public class PersonInformation implements Serializable{
    private String firstName;
    private String middleName;
    private String address;

    public PersonInformation (String firstName, String middleName, String address) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
    }
    public String toString(String id){
        return "ID is: "+id+". Name is: "+firstName+" "+middleName+". Address is: "+address;
    }
    public String toString(){
        return firstName+" "+middleName+" "+address;
    }
}
