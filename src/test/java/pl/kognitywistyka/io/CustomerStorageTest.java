package pl.kognitywistyka.io;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kognitywistyka.io.model.Address;
import pl.kognitywistyka.io.model.Address.AddressType;
import pl.kognitywistyka.io.model.Customer;

/**
 * Created by pwilkin on 10.03.2022.
 */
public class CustomerStorageTest {

    private static Customer mockCustomer() {
        Address adr1 = new Address();
        adr1.setAddressType(AddressType.CORRESPONDENCE);
        adr1.setCity("Warszawa");
        adr1.setPostCode("02-001");
        adr1.setStreet("Puławska");
        adr1.setStreetNo("412");
        Address adr2 = new Address();
        adr2.setAddressType(AddressType.LIVING);
        adr2.setCity("Warszawa");
        adr2.setPostCode("02-042");
        adr2.setStreet("Ząbkowska");
        adr2.setStreetNo("14");
        adr2.setFlatNo("44");
        Set<Address> addresses = new HashSet<>();
        addresses.add(adr1);
        addresses.add(adr2);
        Customer cst = new Customer();
        cst.setAddresses(addresses);
        cst.setFirstName("Janina");
        cst.setLastName("Nowak");
        cst.setEmail("janina.nowak111@gmail.com");
        cst.setBirthDate(Date.from(LocalDate.of(1958, Month.DECEMBER, 14).atStartOfDay().toInstant(ZoneOffset.UTC)));
        cst.setRegistrationDate(new java.util.Date());
        return cst;
    }

    @Test
    public void testStoreAndLoadCustomer() {
        Customer cst = mockCustomer();
        File file = new File("serial.txt");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(cst);
            }
        } catch (IOException e) {
            Assertions.fail(e);
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                Customer readCst = (Customer) ois.readObject();
                Assertions.assertEquals("janina.nowak111@gmail.com", readCst.getEmail());
            } catch (ClassNotFoundException e) {
                Assertions.fail(e);
            }
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void testStoreAndLoadCustomerXStream() {
        XStream xstream = new XStream(new StaxDriver());
        Customer cst = mockCustomer();
        File file = new File("serial.xml");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            xstream.toXML(cst, fos);
        } catch (IOException e) {
            Assertions.fail(e);
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            Customer readCst = (Customer) xstream.fromXML(fis);
            Assertions.assertEquals("janina.nowak111@gmail.com", readCst.getEmail());
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

}
