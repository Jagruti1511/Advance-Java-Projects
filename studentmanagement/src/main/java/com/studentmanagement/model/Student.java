package com.studentmanagement.model;

public class Student {

    private int id;
    private String name;
    private double marks;
    private String mobno;
    private String address;
    private String dob;

    public Student() {
    }

    public Student(int id, String name, double marks,
                   String mobno, String address, String dob) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.mobno = mobno;
        this.address = address;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", marks=" + marks + ", mobno=" + mobno + ", address=" + address
				+ ", dob=" + dob + "]";
	}
    
}