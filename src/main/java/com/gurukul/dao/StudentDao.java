package com.gurukul.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gurukul.exception.*;
import com.gurukul.model.Student;
public class StudentDao {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    DataSource ds = new DataSource();

    public List<Student> getStudentDetails() throws SQLException {
	String query = "Select * from students";
	conn = ds.getConnection();
	List<Student> StudentList = new ArrayList<>();
	try {
	    Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if (rs != null) {
		while (rs.next()) {
		    Student obj = new Student();
		    obj.setName(rs.getString("name"));
		    obj.setEmail(rs.getString("email"));
		    obj.setCourse(rs.getString("course"));
		    obj.setAge(rs.getInt("age"));
//		    System.out.println(obj.getUname() + ", " + obj.getPswd());
		    StudentList.add(obj);
		}
	    }
	    return StudentList;
	} catch (SQLException e) {
	    System.out.println("SQLException: " + e.getMessage());
	    return null;
	} finally {

	    conn.close();
	}
    }

    public boolean insertDetails(Student student) throws SQLException, EmailException {
	conn = ds.getConnection();
	String query = "insert into students(name, email, course, age) values(?, ?, ?, ?)";
	String checkQuery = "select count(*) from students where email = ?";
	int i = 0;
	int studentId;
	boolean studentCreated = false;
	try {
	    PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
	    checkStmt.setString(1, student.getEmail());
	    ResultSet checkResult = checkStmt.executeQuery();
	    checkResult.next();
	    int emailCount = checkResult.getInt(1);

	    if (emailCount > 0) {
		throw new EmailException("Email already exists in the database");
	    } else {
		PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, student.getName());
		pstmt.setString(2, student.getEmail());
		pstmt.setString(3, student.getCourse());
		pstmt.setInt(4, student.getAge());

		i = pstmt.executeUpdate();
		if (i != 0) {
		    ResultSet rs = pstmt.getGeneratedKeys();
		    if (rs.next()) {
			studentId = rs.getInt(1);
			studentCreated = true;
			System.out.println("StudentId = " + studentId);
		    }
		}
	    }
	} catch (SQLException e) {
	    System.out.println("SQLException: " + e.getMessage());
	} finally {
	    conn.close();
	}
	return studentCreated;
    }

    public void deleteHard(int id) throws SQLException, IdNotFoundException {
	String query = "delete from students where id=?";
	conn = ds.getConnection();
	try {
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    pstmt.setInt(1, id);
	    int i = pstmt.executeUpdate();
	    if (i != 0) {
		System.out.println(id + " is deleted.");
	    } else {
		throw new IdNotFoundException(id + " not found in the database.");
	    }
	} catch (SQLException e) {
	    System.out.println("SQLException: " + e.getMessage());
	} finally {
	    conn.close();
	}
    }

    public void updateCourse(int id, String course) throws SQLException, IdNotFoundException {
	String query = "update students set course=? where id=?";
	conn = ds.getConnection();
	try {
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    pstmt.setString(1, course);
	    pstmt.setInt(2, id);

	    int i = pstmt.executeUpdate();
	    if (i != 0) {
		System.out.println("Course of " + id + " is changed to " + course);
	    } else {
		throw new IdNotFoundException(id + " not found in the database.");
	    }
	} catch (SQLException e) {
	    System.out.println("SQLException: " + e.getMessage());
	} finally {
	    conn.close();
	}
    }

}
