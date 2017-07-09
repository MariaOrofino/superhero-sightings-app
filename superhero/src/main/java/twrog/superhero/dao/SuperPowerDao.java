/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package twrog.superhero.dao;

import java.util.List;
import twrog.superhero.dto.SuperPower;

/**
 *
 * @author Travis Rogers
 */
public interface SuperPowerDao {

    void addSuperPower(SuperPower sp);

    void deleteSuperPower(int superPowerID);

    List<SuperPower> getAllSuperPowers();

    SuperPower getSuperPowerByID(int superPowerID);

    List<SuperPower> getSuperPowersByHeroID(int heroID);

    void updateSuperPower(SuperPower sp);

}
