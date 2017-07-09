/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package twrog.superhero.service;

import javax.inject.Inject;
import twrog.superhero.dao.HeroDao;
import twrog.superhero.dao.LocationDao;
import twrog.superhero.dao.OrgDao;
import twrog.superhero.dao.SightingDao;
import twrog.superhero.dao.SuperPowerDao;

/**
 *
 * @author Travis Rogers
 */
public class Service {
    HeroDao heroDao;
    LocationDao locationDao;
    OrgDao orgDao;
    SightingDao sightingDao;
    SuperPowerDao superPowerDao;

    @Inject
    public Service(HeroDao heroDao, LocationDao locationDao, OrgDao orgDao, SightingDao sightingDao, SuperPowerDao superPowerDao) {
        this.heroDao = heroDao;
        this.locationDao = locationDao;
        this.orgDao = orgDao;
        this.sightingDao = sightingDao;
        this.superPowerDao = superPowerDao;
    }
    
    
}
