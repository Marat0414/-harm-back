package kz.muradaliev.charm.back.dto;

import jakarta.servlet.http.Part;
import kz.muradaliev.charm.back.model.Gender;
import kz.muradaliev.charm.back.model.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


public class ProfileUpdateDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String about;
    private Gender gender;
    private Status status;
    private Part photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Part getPhoto() {
        return photo;
    }

    public void setPhoto(Part photo) {
        this.photo = photo;
    }
}