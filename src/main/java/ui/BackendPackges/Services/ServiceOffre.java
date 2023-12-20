package ui.BackendPackges.Services;


import ui.BackendPackges.Entities.Offres;
import ui.BackendPackges.Utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceOffre implements IService<Offres>{
    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;

    public ServiceOffre() {
        try {
            ste = con.createStatement();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }




    @Override
    public void add(Offres offre) throws SQLException {
        String sql = "INSERT INTO `offres`('id_offre',`pays`, `cite`, `lieu`, `date`, `prix`) VALUES (NULL,o.getPays(), o.getCite(), o.getLieu(), o.getDate(), o.getPrix())";
        ste.executeUpdate(sql);
        System.out.println("offre  Ajouté !");

    }

    @Override
    public void update(Offres offre) throws SQLException {
        String sql = "UPDATE `offres` SET `pays`=offre.setPays(),`cite`=offre.setCite(),`lieu`=offre.setLieu(),`date`=offre.setDate(),`prix`=offre.setPrix() WHERE 1";
        ste.executeUpdate(sql);
        System.out.println("offre  modifié !");

    }

    @Override
    public void delete(int id) throws SQLException {
        String sql="DELETE FROM `offres` WHERE `id` = " + id;
        ste.executeUpdate(sql);
        System.out.println("offre  supprime !");

    }

    @Override
    public ArrayList readAll() throws SQLException {
        ArrayList<Offres> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * FROM `offres`;";
            ResultSet res = ste.executeQuery(sql2);

            while (res.next()) {
                int id = (res.getInt(1));
                String pays = (res.getString(2));

                String date = (res.getString(2));
                String cite = (res.getString(3));
                String lieu = (res.getString(4));
                int prix = (res.getInt(4));
                Offres o = new Offres(id,pays,cite,lieu,date,prix);
                list.add(o);
            }
        }
        catch(SQLException ex) {
            System.out.println(ex);
        }

        return list;

    }

    @Override
    public Offres get(int id) throws SQLException {
        String sql = "SELECT * FROM `offres` WHERE `id` = " + id;
        ResultSet res = ste.executeQuery(sql);
        if (res.next()) {
            String pays = res.getString("pays");
            String cite = res.getString("cite");
            String lieu = res.getString("lieu");
            String date = res.getString("date");
            int prix = res.getInt("prix");
            return new Offres(id, pays, cite, lieu, date,prix);
        }
        return null;
    }

}
