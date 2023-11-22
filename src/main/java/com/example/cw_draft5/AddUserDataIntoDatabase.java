package com.example.cw_draft5;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddUserDataIntoDatabase {
    private DatabaseConnection conn = new DatabaseConnection();
    private Connection con;
    private int StudentID;
    private String FullName;
    private Date DOB;
    private String Contact;
    private String Gender;
    private String Email;
    private String Location;
    private int Grade;
    private String Guardian;
    private String Skills;
    private String Password;


    public AddUserDataIntoDatabase(String fullName, LocalDate DOB, String contact, String gender, String email, String location, String grade, String guardian, String skills, String password, String text) {
        this.DOB = Date.valueOf(DOB);
        FullName = fullName;
        Contact = contact;
        Gender = gender;
        Email = email;
        Location = location;
        Grade = Integer.parseInt(grade);
        Guardian = guardian;
        Skills = skills;
        Password = password;


    }

    public void insert(){
        String sqlInsertStatement = "INSERT INTO student(StudentID,FullName, DOB, Contact, Gender, Email, Location, Grade, Guardian, Skills, Password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement s = con.prepareStatement(sqlInsertStatement)){
            s.setString(1, String.valueOf(StudentID));
            s.setString(2,FullName);
            s.setString(3, String.valueOf(DOB));
            s.setString(4,Contact);
            s.setString(5,Gender);
            s.setString(6,Email);
            s.setString(7,Location);
            s.setString(8, String.valueOf(Grade));
            s.setString(9,Guardian);
            s.setString(10,Skills);
            s.setString(11,Password);

            s.executeLargeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.toString());
        }finally {
            close();
        }
    }
    private void close(){
        try{
            con.close();
            System.out.println("Connection closed");
        }catch (SQLException ex){

        }
    }

}
