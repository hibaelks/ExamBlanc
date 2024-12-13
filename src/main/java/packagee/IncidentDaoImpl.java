package packagee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncidentDaoImpl implements IncidentDao{
    private Connection conn;
    public IncidentDaoImpl(){
        conn = DatabaseConnection.connect();
    }
    public boolean inserer(Incident I){
        try  {
            String query = "INSERT INTO incidents VALUES(?,?,?,?,?);";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, I.getRef());
                stmt.setString(2, I.getTime());
                stmt.setBoolean(3, I.getStatus());
                stmt.setInt(4, I.getMemberId());
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean inser(Incident[] ics){
        try  {
            int rowsAffected =0;
            for(int i=0;i<ics.length;i++) {
                String query = "INSERT INTO incidents VALUES(?,?,?,?);";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, ics[i].getRef());
                    stmt.setString(2, ics[i].getTime());
                    stmt.setBoolean(3, ics[i].getStatus());
                    stmt.setInt(4, ics[i].getMemberId());
                    rowsAffected += stmt.executeUpdate();
                }
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String args[]){
        IncidentDaoImpl idi=new IncidentDaoImpl();
        Incident [] ics= {new Incident("A4","2023-12-2 22:22:22",true,1),new Incident("B4","2023-12-2 22:22:22",true,1),new Incident("C4","2023-12-2 22:22:22",true,1)};
        idi.inser(ics);
    }

}
