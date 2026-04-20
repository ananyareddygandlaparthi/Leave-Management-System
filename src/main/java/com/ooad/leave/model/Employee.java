package com.ooad.leave.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {
    private String department;
    private int leaveBalance;

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public int getLeaveBalance() { return leaveBalance; }
    public void setLeaveBalance(int leaveBalance) { this.leaveBalance = leaveBalance; }
}
