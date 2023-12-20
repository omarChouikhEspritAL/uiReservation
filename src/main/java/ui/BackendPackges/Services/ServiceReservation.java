package ui.BackendPackges.Services;


import ui.BackendPackges.Entities.Offres;
import ui.BackendPackges.Entities.Reservation;
import ui.BackendPackges.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class ServiceReservation implements IService<Reservation> {
    private Connection connection= DataSource.getInstance().getCon();
    private Statement statement;

    private ServiceOffre serviceOffre= new ServiceOffre();
    public ServiceReservation(){
        try {
            statement= connection.createStatement();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void add(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservations VALUES (NULL, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(reservation.getDateDebut().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(reservation.getDateFin().getTime()));
            preparedStatement.setInt(3, reservation.getNombrePassages());
            preparedStatement.setInt(4, reservation.getOffre().getId_offre());
            preparedStatement.setInt(5, 1);

            int res = preparedStatement.executeUpdate();
            System.out.println("Number of tuples added: " + res);
        }
    }


    @Override
    public void update(Reservation t) throws SQLException {
        if (get(t.getId()) != null) {
            String query = "UPDATE reservations " +
                    "SET dateDebut = ?," +
                    "dateFin = ?," +
                    "nbrPass = ?," +
                    "idOffre = ?," +
                    "idMembre = ? " +
                    "WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDate(1, new java.sql.Date(t.getDateDebut().getTime()));
                preparedStatement.setDate(2, new java.sql.Date(t.getDateFin().getTime()));
                preparedStatement.setInt(3, t.getNombrePassages());
                preparedStatement.setInt(4, t.getOffre().getId_offre());
                preparedStatement.setInt(5, t.getOffre().getId_offre());
                preparedStatement.setInt(6, t.getId());

                int res = preparedStatement.executeUpdate();
                System.out.println("Number of tuples modified: " + res);
            }
        } else {
            throw new SQLException("Reservation not found");
        }
    }


    @Override
    public void delete(int id) throws SQLException {
        try {
            if (id!= 0) {
                String query = "delete from reservations where id=" + id;
                statement.executeUpdate(query);
            } else {
                throw new SQLException("Reservation unavailable");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public ArrayList<Reservation> readAll() throws SQLException {
        ArrayList<Reservation>reservations = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from reservations");
            while (resultSet.next()){
                int id=resultSet.getInt(1);
                Date dateDebut = resultSet.getDate(2);
                Date dateFin= resultSet.getDate(3);
                int nombrePassages = resultSet.getInt(4);
                Offres offre =  new Offres(1,"Turkey","Istambul","xxxx",
                        "10-09-2023",1852.8f); //(serviceOffre.get(resultSet.getInt(5)));
                //int id_membre=resultSet.getInt(6);
                reservations.add(new Reservation(id, dateDebut, dateFin, nombrePassages,offre ));//id_membre));
            }
        }catch (SQLException ex){
            System.out.println(ex);
        }
        return reservations;
    }
    public ArrayList<Reservation> readAllById(int idM) throws SQLException {
        ArrayList<Reservation>reservations = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from reservations where idMembre=1");
            while (resultSet.next()){
                int id=resultSet.getInt(1);
                Date dateDebut = resultSet.getDate(2);
                Date dateFin= resultSet.getDate(3);
                int nombrePassages = resultSet.getInt(4);
                Offres offre =  new Offres(1,"Turkey","Istambul","xxxx",
                        "10-09-2023",1852.8f);
                int id_membre=resultSet.getInt(6);
                reservations.add(new Reservation(id, dateDebut, dateFin, nombrePassages, offre));//serviceOffre.get(id_offre)));//,id_membre));
            }
        }catch (SQLException ex){
            System.out.println(ex);
        }
        return reservations;
    }

    @Override
    public Reservation get(int idR) throws SQLException {
        try {
            ResultSet resultSet = statement.executeQuery("select * from reservations where id=" + idR);
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                Date dateDebut = resultSet.getDate(2);
                Date dateFin = resultSet.getDate(3);
                int nombrePassages = resultSet.getInt(4);
                Offres offre = (serviceOffre.get(1));
                // User user = resultSet.getInt(6); // a refaire

                return new Reservation(id, dateDebut, dateFin, nombrePassages, offre);//,user);
            } else {
                System.out.println("No reservation found with ID: " + idR);
            }
        }catch (SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
}