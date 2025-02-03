package layer.dao;

import java.sql.*;

import layer.connection.DBConnection;
import layer.entity.User;

public class UserDAO {
	//CRUD
	//CREATE new user
	public boolean createUser(User user) {
		boolean created = false;
		Connection con = DBConnection.getCon();
		PreparedStatement pst = null;
		String query = "INSERT into user(name,email,password) values(?,?,?)";
		
		try {
			pst = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPassword());
			int result = pst.executeUpdate();
			if(result == 1) {
				created = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBConnection.closeCon(con, pst);
		return created;
	}
	
	//READ
	//READ user by ID
	public User getUserById(int id) {
		User user = null;
		Connection con = DBConnection.getCon();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "SELECT * from user where id=?";
		
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				user = new User(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBConnection.closeCon(con, pst, rs);
		return user;
	}
	
	
	//get user by email
	public User getUserByEmail(String email) {
		User user = null;
		Connection con = DBConnection.getCon();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "SELECT * from user where email=?";
		
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, email);
			rs = pst.executeQuery();
			while(rs.next()) {
				user = new User(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBConnection.closeCon(con, pst, rs);
		return user;
	}
	
	//UPDATE
	public boolean updateUser(User user, int id) {
		boolean updated = false;
		Connection con = DBConnection.getCon();
		PreparedStatement pst = null;
		String query = "UPDATE user set name=?, email=?, password=? where id=?";
		
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPassword());
			pst.setInt(4, id);
			int result = pst.executeUpdate();
			if(result == 1) {
				updated = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBConnection.closeCon(con, pst);
		return updated;
	}
	
	//DELETE
	public boolean deleteUser(int id) {
		boolean deleted = false;
		Connection con = DBConnection.getCon();
		PreparedStatement pst = null;
		String query = "DELETE from user where id=?";
		
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			int result = pst.executeUpdate();
			if(result == 1) {
				deleted = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		DBConnection.closeCon(con, pst);
		return deleted;
	}
}
