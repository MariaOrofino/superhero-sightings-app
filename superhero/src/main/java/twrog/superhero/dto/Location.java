/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package twrog.superhero.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Travis Rogers
 */
public class Location {
    private int locationID;
    private String locationName;
    private String description;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    
    public int getLocationID() {
        return locationID;
    }
    
    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
    
    public String getLocationName() {
        return locationName;
    }
    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStreetAddress() {
        return streetAddress;
    }
    
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getZipcode() {
        return zipcode;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public BigDecimal getLatitude() {
        return latitude;
    }
    
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    
    public BigDecimal getLongitude() {
        return longitude;
    }
    
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.locationID;
        hash = 17 * hash + Objects.hashCode(this.locationName);
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.streetAddress);
        hash = 17 * hash + Objects.hashCode(this.city);
        hash = 17 * hash + Objects.hashCode(this.state);
        hash = 17 * hash + Objects.hashCode(this.zipcode);
        hash = 17 * hash + Objects.hashCode(this.latitude);
        hash = 17 * hash + Objects.hashCode(this.longitude);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.locationID != other.locationID) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.streetAddress, other.streetAddress)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zipcode, other.zipcode)) {
            return false;
        }
        if (
                !(this.latitude == null && other.latitude == null) &&
                !(this.latitude.equals(other.latitude))
                ) {
            return false;
        }
        if (
                !(this.longitude == null && other.longitude == null) &&
                !(this.longitude.equals(other.longitude))
                ) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}