package packagee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.HashSet;
import java.io.IOException;
import java.util.Set;
public class MembreDaoImpl implements MembreDao{
    private Connection conn;
    public MembreDaoImpl(){
        conn = DatabaseConnection.connect();
    }
    public boolean insere(Membre M){
        try  {
            int id=getId();
            M.setId(id);
            String query = "INSERT INTO members VALUES(NULL,?,?,?,?);";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, M.getNom());
                stmt.setString(2, M.getPrenom());
                stmt.setString(3, M.getEmail());
                stmt.setString(4, M.getPhone());
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Incident[] chargerListIncidents(int id){
            String query = "SELECT * FROM Incidents WHERE member_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                Incident[] ics=new Incident[0];
                Incident[] temp;
                while (rs.next()) {
                    Incident ic= new Incident(
                            rs.getString("ref"),
                            rs.getString("time"),
                            rs.getBoolean("status"),
                            rs.getInt("member_id")
                    );
                    temp=new Incident[(ics.length+1)];
                    System.arraycopy(ics,0,temp,0,ics.length);
                    temp[ics.length]=ic;
                    ics=temp;
                }
                return ics;
            }catch(SQLException e) {return null;}
    }

    public Membre[] readCSV(String filename) {
            Set<Membre> objectSet = new HashSet<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",");
                    Membre m = new Membre(fields[0], fields[1],fields[2],fields[3]);
                    objectSet.add(m);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return objectSet.toArray(new Membre[0]);
    }
    public static void main(String args[]){
        try {
            MembreDaoImpl mdi = new MembreDaoImpl();
            Membre[] mbrs = mdi.readCSV("src/main/resources/packagee/csv");
            for (int i = 0; i < mbrs.length; i++) {
                mdi.insere(mbrs[i]);
            }
            System.out.println("MEMBERS LOADED FROM CSV FILE AFTER INSERTING IN THE DB :");
            for (int i = 0; i < mbrs.length; i++) {
                System.out.println(mbrs[i]);
            }
            Incident[] ics = mdi.chargerListIncidents(1);
            if(ics!=null) {
                System.out.println("For the member of id : " + mbrs[0].getId());
                for (int i = 0; i < ics.length; i++) {
                    System.out.println(ics[i]);
                }
            }
        }catch(Exception e){ System.out.println("ERROR "); e.printStackTrace();}
    }
    public int getId(){
        String query = "SELECT id FROM members ORDER BY id DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
