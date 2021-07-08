package eVoting.dao;

import eVoting.dbutil.DBConnection;
import eVoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDao 
{
    private static PreparedStatement ps,ps1;
    static
    {
        try
        {
            ps=DBConnection.getConnection().prepareStatement("Select * from user_details where aadhar_no=?");
            ps1=DBConnection.getConnection().prepareStatement("Insert into user_details values(?,?,?,?,?,?,?,?,?)");
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    public static boolean searchUser(String userid) throws SQLException
    {
        ps.setString(1,userid);
        return ps.executeQuery().next();
    }
    public static boolean registerUser(UserDetails user) throws SQLException
    {
        ps1.setString(1, user.getUserid());
        ps1.setString(2, user.getPassword());
        ps1.setString(3, user.getUsername());
        ps1.setString(4, user.getAddress());
        ps1.setString(5, user.getCity());
        ps1.setString(6, user.getEmail());
        ps1.setString(7, user.getMobile());
        ps1.setString(8, "voter");
        ps1.setString(9, user.getGender());
        return(ps1.executeUpdate()!=0);
    }
}
