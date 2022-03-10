package pl.kognitywistyka.io.model;

import java.io.Serializable;

/**
 * Created by pwilkin on 10.03.2022.
 */
public class Address implements Serializable {

    public static enum AddressType {
        CORRESPONDENCE,
        REGISTRATION,
        LIVING
    }

    protected AddressType addressType;
    protected String street;
    protected String city;
    protected String postCode;
    protected String streetNo;
    protected String flatNo;

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }
}
