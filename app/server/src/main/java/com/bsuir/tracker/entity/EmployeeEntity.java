package com.bsuir.tracker.entity;

import javax.persistence.*;

/**
 * Created by Pavel on 25.04.2017.
 */
@Entity
@Table(name = "employee", schema = "spp-time-tracker", catalog = "")
public class EmployeeEntity {
    private int idemployee;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer avatarIdimage;
    private int roleIdrole;
    private int companyIdcompany;
    private String resetPasswordToken;
    private String changeEmailToken;
    private String confirmRegisterToken;
    private String tempEmail;
    private String patronymic;
    private Integer workdayIdworkdayType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idemployee", nullable = false)
    public int getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(int idemployee) {
        if (idemployee < 0){
            throw new IllegalArgumentException();
        }
        this.idemployee = idemployee;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "avatar_idimage", nullable = true)
    public Integer getAvatarIdimage() {
        return avatarIdimage;
    }

    public void setAvatarIdimage(Integer avatarIdimage) {
        if(avatarIdimage != null) {
            if (avatarIdimage < 0){
                throw new IllegalArgumentException();
            }
        }
        this.avatarIdimage = avatarIdimage;
    }

    @Basic
    @Column(name = "role_idrole", nullable = false)
    public int getRoleIdrole() {
        return roleIdrole;
    }

    public void setRoleIdrole(int roleIdrole) {
        if (roleIdrole < 0){
            throw new IllegalArgumentException();
        }
        this.roleIdrole = roleIdrole;
    }

    @Basic
    @Column(name = "company_idcompany", nullable = false)
    public int getCompanyIdcompany() {
        return companyIdcompany;
    }

    public void setCompanyIdcompany(int companyIdcompany) {
        if (companyIdcompany < 0){
            throw new IllegalArgumentException();
        }
        this.companyIdcompany = companyIdcompany;
    }

    @Basic
    @Column(name = "reset_password_token", nullable = true, length = 255)
    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Basic
    @Column(name = "change_email_token", nullable = true, length = 255)
    public String getChangeEmailToken() {
        return changeEmailToken;
    }

    public void setChangeEmailToken(String changeEmailToken) {
        this.changeEmailToken = changeEmailToken;
    }

    @Basic
    @Column(name = "confirm_register_token", nullable = true, length = 255)
    public String getConfirmRegisterToken() {
        return confirmRegisterToken;
    }

    public void setConfirmRegisterToken(String confirmRegisterToken) {
        this.confirmRegisterToken = confirmRegisterToken;
    }

    @Basic
    @Column(name = "temp_email", nullable = true, length = 45)
    public String getTempEmail() {
        return tempEmail;
    }

    public void setTempEmail(String tempEmail) {
        this.tempEmail = tempEmail;
    }

    @Basic
    @Column(name = "patronymic", nullable = true, length = 45)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "workday_idworkday_type", nullable = true)
    public Integer getWorkdayIdworkdayType() {
        return workdayIdworkdayType;
    }

    public void setWorkdayIdworkdayType(Integer workdayIdworkdayType) {
        //if (workdayIdworkdayType < 0){
        //    throw new IllegalArgumentException();
        //}
        this.workdayIdworkdayType = workdayIdworkdayType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (idemployee != that.idemployee) return false;
        if (roleIdrole != that.roleIdrole) return false;
        if (companyIdcompany != that.companyIdcompany) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (avatarIdimage != null ? !avatarIdimage.equals(that.avatarIdimage) : that.avatarIdimage != null)
            return false;
        if (resetPasswordToken != null ? !resetPasswordToken.equals(that.resetPasswordToken) : that.resetPasswordToken != null)
            return false;
        if (changeEmailToken != null ? !changeEmailToken.equals(that.changeEmailToken) : that.changeEmailToken != null)
            return false;
        if (confirmRegisterToken != null ? !confirmRegisterToken.equals(that.confirmRegisterToken) : that.confirmRegisterToken != null)
            return false;
        if (tempEmail != null ? !tempEmail.equals(that.tempEmail) : that.tempEmail != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (workdayIdworkdayType != null ? !workdayIdworkdayType.equals(that.workdayIdworkdayType) : that.workdayIdworkdayType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idemployee;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (avatarIdimage != null ? avatarIdimage.hashCode() : 0);
        result = 31 * result + roleIdrole;
        result = 31 * result + companyIdcompany;
        result = 31 * result + (resetPasswordToken != null ? resetPasswordToken.hashCode() : 0);
        result = 31 * result + (changeEmailToken != null ? changeEmailToken.hashCode() : 0);
        result = 31 * result + (confirmRegisterToken != null ? confirmRegisterToken.hashCode() : 0);
        result = 31 * result + (tempEmail != null ? tempEmail.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (workdayIdworkdayType != null ? workdayIdworkdayType.hashCode() : 0);
        return result;
    }
}
