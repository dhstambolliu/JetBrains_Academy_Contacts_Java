package contacts.domain;

import java.time.LocalDateTime;

public abstract class Contact {
    private String name;
    private String number;
    private final LocalDateTime creationDateTime;
    private LocalDateTime editDateTime;

    public Contact(String name, String number) {
        this.name = name;
        setNumber(number);
        this.creationDateTime=LocalDateTime.now().withSecond(0).withNano(0);
        this.editDateTime = LocalDateTime.now().withSecond(0).withNano(0);

    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {

        if(checkNumber(number)){
            this.number=number;
        }else{
            System.out.println("Wrong number format!");
            this.number = "";
        }
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public LocalDateTime getEditDateTime() {
        return editDateTime;
    }

    public void setEditDateTime(LocalDateTime editDateTime) {
        this.editDateTime = editDateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean checkNumber(String number){
        return number.matches(
                "([+])?((\\w+[ -]\\w{2,})|(\\(\\w+\\)[ -]\\w{2,})|(\\w+[ -]\\(\\w{2,}\\))|(\\w+)|(\\(\\w+\\)))" +
                        "([ -]\\w{2,})*");
    }

    public String print(){
        return name;
    }

    @Override
    public String toString() {
        return "Number: " + getNumber()+"\n"
                +"Time created: " +getCreationDateTime() +"\n"
                +"Time last edit: " +getEditDateTime();
    }

    public void setSurname(String surname){}

    public void setBirthDay(String value){}

    public  void setGender(String value){ }

    public  void setAddress(String value){ }
}