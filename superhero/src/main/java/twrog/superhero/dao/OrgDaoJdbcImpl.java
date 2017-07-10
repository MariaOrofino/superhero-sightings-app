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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import twrog.superhero.dto.Organization;

/**
 *
 * @author Travis Rogers
 */
public class OrgDaoJdbcImpl implements OrgDao {
    private static final String SQL_INSERT_ORG =
            "insert into Organization (OrgName, Description, StreetAddress, City, State, Zipcode) "
            + "values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ORG_BY_ID = "select Organization.* from Organization where OrganizationID = ?";
    private static final String SQL_SELECT_ALL_ORGS = "select * from Organization";
    private static final String SQL_SELECT_ORGS_BY_HER0_ID =
            "select Organization.* from Organization" +
            " inner join HeroOrganization on HeroOrganization.OrganizationID = Organization.OrganizationID" +
            " inner join Hero on Hero.HeroID = HeroOrganization.HeroID" +
            " where Hero.HeroID = ?";
    private static final String SQL_UPDATE_ORG_BY_ID =
            "update Organization set OrgName = ?, Description = ?, StreetAddress = ?, City = ?, State = ?, Zipcode = ? "
            + "where OrganizationID = ?";
    private static final String SQL_DELETE_ORG_BY_ID = "delete from Organization where OrganizationID = ?";
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrg(Organization org) {
        jdbcTemplate.update(SQL_INSERT_ORG,
                org.getOrgName(),
                org.getDescription(),
                org.getStreetAddress(),
                org.getCity(),
                org.getState(),
                org.getZipcode()
        );
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        org.setOrganizationID(id);
    }
    @Override
    public Organization getOrgByID(int orgID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORG_BY_ID, new OrgMapper(), orgID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Organization> getAllOrgs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGS, new OrgMapper());
    }
    @Override
    public List<Organization> getOrgsByHeroID(int heroID) {
        return jdbcTemplate.query(SQL_SELECT_ORGS_BY_HER0_ID, new OrgMapper(), heroID);
    }
    @Override
    public void updateOrg(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORG_BY_ID,
                org.getOrgName(),
                org.getDescription(),
                org.getStreetAddress(),
                org.getCity(),
                org.getState(),
                org.getZipcode(),
                org.getOrganizationID()
        );
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrg(int orgID) {
        deleteHeroOrgsByOrgID(orgID);
        jdbcTemplate.update(SQL_DELETE_ORG_BY_ID, orgID);
    }
    
    private static final class OrgMapper implements RowMapper<Organization> {
        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationID(rs.getInt("OrganizationID"));
            org.setOrgName(rs.getString("OrgName"));
            org.setDescription(rs.getString("Description"));
            org.setStreetAddress(rs.getString("StreetAddress"));
            org.setCity(rs.getString("City"));
            org.setState(rs.getString("State"));
            org.setZipcode(rs.getString("Zipcode"));
            return org;
        }
    }
    
    private static final String SQL_DELETE_HERO_ORG_BY_ORG_ID = "delete from HeroOrganization where OrganizationID = ?";
    private void deleteHeroOrgsByOrgID(int orgID) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORG_BY_ORG_ID, orgID);
    }
}
