package contacts.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContactPerson extends Contact{
    private String surname;
    private String gender;
    private LocalDate birthDay;


    public ContactPerson(String name, String number, String surname, String gender, LocalDate birthDay) {
        super(name, number);
        this.surname = surname;
        this.gender = gender;
        this.birthDay = birthDay;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(String gender) {
        if(gender.matches("^[MF]$")){
            this.gender = gender;
        }else{
            this.gender=null;
        }
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthDay(String birtDay){
        LocalDate date = null;
        try {
            date = LocalDate.parse(birtDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            System.out.println("Wrong birth date");
            e.printStackTrace();
        }
        setBirthDay(date);
    }

    @Override
    public String toString() {
        return "Name: " +getName()+"\n"
                +"Surname: " +this.surname+"\n"
                +"Birth date: "+ (this.birthDay==null?"[no data]":this.birthDay)+"\n"
                +"Gender: "+(this.gender==null?"[no data]":this.gender) +"\n"
                +super.toString();

    }

    public String print(){
        return getName()+" "+ this.surname;
    }
}