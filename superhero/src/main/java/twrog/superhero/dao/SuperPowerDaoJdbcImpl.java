/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package twrog.superhero.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import twrog.superhero.dto.SuperPower;

/**
 *
 * @author Travis Rogers
 */
public class SuperPowerDaoJdbcImpl {
    private static final String SQL_INSERT_SUPER_POWER = "insert into SuperPower (Description) values (?)";
    private static final String SQL_SELECT_SUPER_POWER_BY_ID = "select * from SuperPower where SuperPowerID = ?";
    private static final String SQL_SELECT_ALL_SUPER_POWERS = "select * from SuperPower";
    private static final String SQL_SELECT_SUPER_POWERS_BY_HERO_ID = "select SuperPower.*  from SuperPower "
            + "inner join HeroSuperPower on HeroSuperPower.SuperPowerID = SuperPower.SuperPowerID "
            + "where HeroSuperPower.HeroID = ?";
    private static final String SQL_UPDATE_SUPER_POWER_BY_ID = "update SuperPower set Description = ? where SuperPowerID = ?";
    private static final String SQL_DELETE_SUPER_POWER_BY_ID = "delete from SuperPower where SuperPowerID = ?";
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void addSuperPower(SuperPower sp) {
        jdbcTemplate.update(SQL_INSERT_SUPER_POWER, sp.getDescription());
    }
    public SuperPower getSuperPowerByID(int superPowerID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPER_POWER_BY_ID, new SuperPowerMapper(), superPowerID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public List<SuperPower> getAllSuperPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPER_POWERS, new SuperPowerMapper());
    }
    public List<SuperPower> getSuperPowersByHeroID(int heroID) {
        return jdbcTemplate.query(SQL_SELECT_SUPER_POWERS_BY_HERO_ID, new SuperPowerMapper(), heroID);
    }
    public void updateSuperPower(SuperPower sp) {
        jdbcTemplate.update(SQL_UPDATE_SUPER_POWER_BY_ID, sp.getDescription(), sp.getSuperPowerID());
    }
    public void deleteSuperPower(int superPowerID) {
        jdbcTemplate.update(SQL_DELETE_SUPER_POWER_BY_ID, superPowerID);
    }
    
    private static final class SuperPowerMapper implements RowMapper<SuperPower> {
        @Override
        public SuperPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPower sp = new SuperPower();
            sp.setSuperPowerID(rs.getInt("SuperPowerID"));
            sp.setDescription(rs.getString("Description"));
            return sp;
        }
    }
}
