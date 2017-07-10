/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package twrog.superhero.dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import twrog.superhero.dto.Organization;

/**
 *
 * @author 7ravis
 */
public class OrgDaoJdbcImplTest {
    OrgDao instance;
    
    public OrgDaoJdbcImplTest() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        instance = ctx.getBean("orgDao", OrgDao.class);
        List<Organization> orgs = instance.getAllOrgs();
        for (Organization org : orgs) {
            instance.deleteOrg(org.getOrganizationID());
        }
    }
    
    /**
     * Test of addOrg and getOrgByID methods, of class OrgDaoJdbcImpl.
     */
    @Test
    public void testAddGetOrg() {
        System.out.println("addOrg");
        Organization org = new Organization();
        org.setOrgName("Justice League");
        org.setDescription("group of heros trying to make the world a better place");
        org.setStreetAddress("125 Spur Ct");
        org.setCity("New Prague");
        org.setState("MN");
        org.setZipcode("55377");
        instance.addOrg(org);
        int orgID = org.getOrganizationID();
        Organization result = instance.getOrgByID(orgID);
        assertEquals(org, result);
    }
    
    /**
     * Test of getAllOrgs method, of class OrgDaoJdbcImpl.
     */
    @Test
    public void testGetAllOrgs() {
        System.out.println("getAllOrgs");
        Organization org1 = new Organization();
        org1.setOrgName("Justice League");
        instance.addOrg(org1);
        Organization org2 = new Organization();
        org2.setOrgName("The Headhunters");
        instance.addOrg(org2);
        int expResult = 2;
        int result = instance.getAllOrgs().size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of updateOrg method, of class OrgDaoJdbcImpl.
     */
    @Test
    public void testUpdateOrg() {
        System.out.println("updateOrg");
        Organization org = new Organization();
        org.setOrgName("Justice League");
        org.setDescription("group of heros trying to make the world a better place");
        org.setStreetAddress("125 Spur Ct");
        org.setCity("New Prague");
        org.setState("MN");
        org.setZipcode("55377");
        instance.addOrg(org);
        int orgID = org.getOrganizationID();
        org.setOrgName("The Justice League");
        org.setDescription("heros trying to make the world a better place");
        org.setStreetAddress("125 Spur Court");
        org.setCity("Prague");
        org.setState("WI");
        org.setZipcode("58769");
        instance.updateOrg(org);
        Organization result = instance.getOrgByID(orgID);
        assertEquals(org, result);
    }
    
    /**
     * Test of deleteOrg method, of class OrgDaoJdbcImpl.
     */
    @Test
    public void testDeleteOrg() {
        System.out.println("deleteOrg");
        Organization org = new Organization();
        org.setOrgName("Justice League");
        instance.addOrg(org);
        int orgID = org.getOrganizationID();
        Organization result = instance.getOrgByID(orgID);
        assertEquals(org, result);
        instance.deleteOrg(orgID);
        assertNull(instance.getOrgByID(orgID));
    }
    
}
