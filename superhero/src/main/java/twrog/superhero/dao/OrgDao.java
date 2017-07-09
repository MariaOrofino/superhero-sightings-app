/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package twrog.superhero.dao;

import java.util.List;
import twrog.superhero.dto.Organization;

/**
 *
 * @author Travis Rogers
 */
public interface OrgDao {

    void addOrg(Organization org);

    void deleteOrg(int orgID);

    List<Organization> getAllOrgs();

    Organization getOrgByID(int orgID);

    List<Organization> getOrgsByHeroID(int heroID);

    void updateOrg(Organization org);

}
