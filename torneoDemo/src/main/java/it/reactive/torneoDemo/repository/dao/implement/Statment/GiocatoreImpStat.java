package it.reactive.torneoDemo.repository.dao.implement.Statment;

import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;

public class GiocatoreImpStat implements DaoGiocatori {

    @Autowired
    ConnesioneDb cn;

    @Override
    public GiocatoriModel create(GiocatoreDTO giocatoreDTO, int id) throws SQLException {
        return null;
    }

    @Override
    public HashSet<GiocatoriModel> readGiocatoriWithIdSquadra(int id) throws Exception {
        GiocatoriModel giocatore = new GiocatoriModel();
        String query = "SELECT g.*, s.nome as nome_squadra FROM giocatore g JOIN squadra s ON g.id_squadra = s.id where g.id=1";
        Statement ps = cn.init().prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        if (rs.next()) {
            giocatore = MapperGiocatore.rsToModel(rs);
        }
        rs.close();
        return new HashSet<>();
    }

    @Override
    public Optional<GiocatoriModel> readForName(String nome) {
        return Optional.empty();
    }

    @Override
    public Optional<GiocatoriModel> readForId(int id) {
        return Optional.empty();
    }

    @Override
    public void incrementaAmmonizioni(int id) throws SQLException {

    }

}
