package pl.kognitywistyka.io;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.kognitywistyka.io.data.DataContext;
import pl.kognitywistyka.io.model.SimpleData;

public class HelloController implements Initializable {

    protected DataContext dataContext;

    public DataContext getDataContext() {
        return dataContext;
    }

    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
        firstName.textProperty().bindBidirectional(dataContext.getSimpleData().firstNameProperty());
        lastName.textProperty().bindBidirectional(dataContext.getSimpleData().lastNameProperty());
        birthDate.valueProperty().bindBidirectional(dataContext.getSimpleData().birthDateProperty());
    }

    @FXML
    protected TextField firstName;
    @FXML
    protected TextField lastName;
    @FXML
    protected DatePicker birthDate;
    @FXML
    protected Button save;
    @FXML
    protected Button load;

    public void doLoad(ActionEvent actionEvent) {
        try (FileInputStream fis = new FileInputStream("config.ini")) {
            Properties prop = new Properties();
            prop.load(fis);
            dataContext.getSimpleData().setBirthDate(null);
            dataContext.getSimpleData().setFirstName(null);
            dataContext.getSimpleData().setLastName(null);
            if (prop.containsKey("firstName")) {
                dataContext.getSimpleData().setFirstName(prop.getProperty("firstName"));
            }
            if (prop.containsKey("lastName")) {
                dataContext.getSimpleData().setLastName(prop.getProperty("lastName"));
            }
            if (prop.containsKey("birthDate")) {
                String dateStr = prop.getProperty("birthDate");
                dataContext.getSimpleData().setBirthDate(LocalDate.from(DateTimeFormatter.ofPattern("dd-MM-yyyy").parse(dateStr)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(ActionEvent actionEvent) {
        String firstName = dataContext.getSimpleData().getFirstName();
        String lastName = dataContext.getSimpleData().getLastName();
        LocalDate birthDate = dataContext.getSimpleData().getBirthDate();
        Properties prop = new Properties();
        if (firstName != null) {
            prop.setProperty("firstName", firstName);
        }
        if (lastName != null) {
            prop.setProperty("lastName", lastName);
        }
        if (birthDate != null) {
            prop.setProperty("birthDate", DateTimeFormatter.ofPattern("dd-MM-yyyy").format(birthDate));
        }
        try (FileOutputStream fos = new FileOutputStream("config.ini")) {
            prop.store(fos, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}