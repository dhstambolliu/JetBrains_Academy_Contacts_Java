package contacts.domain;

public class ContactCompany extends Contact{
    private String address;

    public ContactCompany(String name, String number, String address) {
        super(name, number);
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Organization name: " +getName()+"\n"
                +"Address: " +this.address+"\n"
                +super.toString();
    }

}