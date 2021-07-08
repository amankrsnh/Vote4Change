package eVoting.dao;

import eVoting.dbutil.DBConnection;
import eVoting.dto.UserDetails;
import eVoting.dto.UserDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDao 
{
    private static PreparedStatement ps,ps1,ps2;
    private static Statement st1,st2;
    static
    {
        try
        {
            ps=DBConnection.getConnection().prepareStatement("Select user_type from user_details where aadhar_no=? and password=?");
            st1=DBConnection.getConnection().createStatement();
            st2=DBConnection.getConnection().createStatement();
            ps1=DBConnection.getConnection().prepareStatement("select * from user_details where aadhar_no=?");
            ps2=DBConnection.getConnection().prepareStatement("delete from user_details where aadhar_no=?");
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    public static String validateUser(UserDto user) throws SQLException
    {
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPassword());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }
    
    public static ArrayList<UserDetails> getAllUser() throws SQLException
    {
        ArrayList<UserDetails> users=new ArrayList<>();
        ResultSet rs=st1.executeQuery("Select * from user_details where user_type='voter'");
        while(rs.next())
        {
            UserDetails user=new UserDetails();
            user.setUserid(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setCity(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setMobile(rs.getString(7));
            users.add(user);
        }
        return users;
    }
    
    public static ArrayList<String> getAllUserId() throws SQLException
    {
        ArrayList<String> list=new ArrayList<>();
        ResultSet rs=st2.executeQuery("Select aadhar_no from user_details where user_type='voter'");
        while(rs.next())
        {
            list.add(rs.getString(1));
        }
        return list;
    }
    
    public static UserDetails getUserById(String uid) throws SQLException
    {
        ps1.setString(1, uid);
    	ResultSet rs=ps1.executeQuery();
    	UserDetails user=new UserDetails();
    	if(rs.next()) {
    		user.setUserid(rs.getString(1));
    		user.setPassword(rs.getString(2));
    		user.setUsername(rs.getString(3));
    		user.setAddress(rs.getString(4));
    		user.setCity(rs.getString(5));
    		user.setEmail(rs.getString(6));
    		user.setMobile(rs.getString(7));
    	}
    	return user;
    }
    
    public static boolean deleteUser(String uid) throws SQLException
    {
    	ps2.setString(1, uid);
    	return ps2.executeUpdate()!=0;
    }
}
