package com.college.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.college.model.Student;
import com.college.util.DBConnection;

public class StudentDAO {


    // =====================================================
    // LOGIN
    // =====================================================

    /*
     * Login करण्यासाठी ही method वापरतो.
     *
     * Database मध्ये:
     *
     * rollno + password
     *
     * match होतात का ते check करते.
     */
    public boolean checkLogin(String rollno,
                              String password) {

        /*
         * SELECT query आहे.
         *
         * ? च्या जागी values नंतर set करू.
         */
        String sql =
            "SELECT * FROM student " +
            "WHERE rollno = ? AND password = ?";

        try {

            /*
             * Database connection घेतो.
             */
            Connection con =
                DBConnection.getConnection();

            /*
             * SQL query तयार करतो.
             */
            PreparedStatement ps =
                con.prepareStatement(sql);

            /*
             * पहिल्या ? मध्ये rollno.
             */
            ps.setInt(
                1,
                Integer.parseInt(rollno)
            );

            /*
             * दुसऱ्या ? मध्ये password.
             */
            ps.setString(
                2,
                password
            );

            /*
             * SELECT असल्यामुळे
             * executeQuery() वापरतो.
             */
            ResultSet rs =
                ps.executeQuery();

            /*
             * record मिळाला म्हणजे login successful.
             */
            if (rs.next()) {

                return true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        /*
         * Record मिळाला नाही तर false.
         */
        return false;
    }



    // =====================================================
    // INSERT STUDENT
    // =====================================================

    /*
     * नवीन student database मध्ये save करण्यासाठी.
     */
    public boolean insertStudent(Student student) {

        String sql =
            "INSERT INTO student " +
            "(rollno, name, marks, mobno, address, dob, " +
            "email, password, gender, course, department, " +
            "year, city, state, pincode) " +

            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                DBConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement(sql);


            /*
             * Student object मधील values
             * SQL query मधील ? मध्ये set करतो.
             */

            ps.setInt(
                1,
                student.getRollno()
            );

            ps.setString(
                2,
                student.getName()
            );

            ps.setInt(
                3,
                student.getMarks()
            );

            ps.setString(
                4,
                student.getMobno()
            );

            ps.setString(
                5,
                student.getAddress()
            );

            /*
             * dob database मध्ये DATE आहे.
             *
             * HTML मधून date "YYYY-MM-DD"
             * format मध्ये येते.
             *
             * java.sql.Date मध्ये convert करतो.
             */
            ps.setDate(
                6,
                java.sql.Date.valueOf(
                    student.getDob()
                )
            );

            ps.setString(
                7,
                student.getEmail()
            );

            ps.setString(
                8,
                student.getPassword()
            );

            ps.setString(
                9,
                student.getGender()
            );

            ps.setString(
                10,
                student.getCourse()
            );

            ps.setString(
                11,
                student.getDepartment()
            );

            ps.setString(
                12,
                student.getYear()
            );

            ps.setString(
                13,
                student.getCity()
            );

            ps.setString(
                14,
                student.getState()
            );

            ps.setString(
                15,
                student.getPincode()
            );


            /*
             * INSERT query आहे.
             *
             * म्हणून executeUpdate().
             */
            int rows =
                ps.executeUpdate();


            /*
             * एक किंवा जास्त rows affected झाल्या
             * तर insert successful.
             */
            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }



    // =====================================================
    // GET ALL STUDENTS
    // =====================================================

    /*
     * Database मधील सर्व students
     * मिळवण्यासाठी ही method.
     *
     * SELECT query असल्यामुळे ResultSet return करतो.
     */
    public List<Student> getAllStudents() {

        /*
         * Student objects ठेवण्यासाठी List.
         */
        List<Student> students =
            new ArrayList<>();


        String sql =
            "SELECT * FROM student";


        try {

            Connection con =
                DBConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement(sql);

            /*
             * SELECT query.
             */
            ResultSet rs =
                ps.executeQuery();


            /*
             * प्रत्येक database record साठी
             * एक Student object तयार होईल.
             */
            while (rs.next()) {

                Student student =
                    new Student();


                student.setRollno(
                    rs.getInt("rollno")
                );

                student.setName(
                    rs.getString("name")
                );

                student.setMarks(
                    rs.getInt("marks")
                );

                student.setMobno(
                    rs.getString("mobno")
                );

                student.setAddress(
                    rs.getString("address")
                );

                /*
                 * Date database मधून घेतो.
                 */
                if (rs.getDate("dob") != null) {

                    student.setDob(
                        rs.getDate("dob").toString()
                    );
                }


                student.setEmail(
                    rs.getString("email")
                );

                student.setGender(
                    rs.getString("gender")
                );

                student.setCourse(
                    rs.getString("course")
                );

                student.setDepartment(
                    rs.getString("department")
                );

                student.setYear(
                    rs.getString("year")
                );

                student.setCity(
                    rs.getString("city")
                );

                student.setState(
                    rs.getString("state")
                );

                student.setPincode(
                    rs.getString("pincode")
                );


                /*
                 * Password display करण्याची गरज नाही.
                 *
                 * म्हणून password Student object मध्ये
                 * display करण्यासाठी set करत नाही.
                 */

                students.add(student);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return students;
    }
}