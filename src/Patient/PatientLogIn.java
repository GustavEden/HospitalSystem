package Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.dbConnection;
public class PatientLogIn {
    Connection connection;
    public PatientLogIn(){
        try {
            this.connection=dbConnection.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(this.connection== null){
            System.exit(1);
        }

    }
    public boolean isDatabaseConnected(){
        return this.connection != null;
    }
    public boolean isLogin(String medicalNbr) throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT Med_nbr FROM Patient WHERE Med_nbr = ? ";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, medicalNbr);

            rs = pr.executeQuery();

            boolean bol1;

            if (rs.next()) {
                return true;
            }
            return false;


        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            pr.close();
            rs.close();
        }
    }
}
