/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Domain.CircularLinkList;
import Domain.ListException;
import Objects.Course;
import Objects.Student;
import Objects.TimeTable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author LuisGa
 */
public class DeEnrollmentController implements Initializable {

    @FXML
    private Text txt;
    @FXML
    private TextField txfStudentID;
    @FXML
    private TextField txfLastName;
    @FXML
    private TextField txfFirstName;

    
    @FXML
    private TextField txfPersID;
    @FXML
    private TextField txfBirthday;
    @FXML
    private TextField txfPhoneNumber;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfAddress;
    @FXML
    private TextField txfCarrer;
    @FXML
    private ComboBox<String> cmbPeriod;
    @FXML
    private ComboBox<String> cmbCourse;
    @FXML
    private ComboBox<String> cmbSchedule;
    @FXML
    private Button btnEnroll;
    @FXML
    private Button btnCancel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Student s= Util.Utility.getUserStudent();
            
            txfStudentID.setText(s.getStudentID());
            txfPersID.setText(""+s.getId());
            txfFirstName.setText(s.getFirstname());
            txfLastName.setText(s.getLastname());
            txfAddress.setText(s.getAddress());
            txfBirthday.setText(Util.Utility.dateFormat(s.getBirthday2()));
            txfEmail.setText(s.getEmail());
            txfPhoneNumber.setText(s.getPhoneNumber());
            
            loadComboBoxCourses(""+s.getCareerID());
            txfCarrer.setText(Util.Utility.getCarrerByID(""+s.getCareerID()).getDescription());
            loadComboBoxPeriod();
//            loadComboBoxSchedule(Util.Utility.getIDofString(cmbCourse.getValue()), cmbPeriod.getValue());
            
        } catch (ListException ex) {
            Logger.getLogger(EnrollmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    public void loadComboBoxCourses(String id) throws ListException{
        //Para cargar un combobox
        CircularLinkList tempCourses = Util.Utility.getCoursesByCarrerID(id);
        
        if(!tempCourses.isEmpty())
        {
        String temporal = "";
        
        try {
            for (int i = 1; i <= tempCourses.size(); i++) {
                Course c = (Course)tempCourses.getNode(i).getData(); 
                temporal = c.getId()+"-"+c.getName();
                this.cmbCourse.getItems().add(temporal);
                        }
        } catch (ListException ex) {
            Logger.getLogger(NewStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cmbCourse.setValue(temporal);
        cmbCourse.getSelectionModel().select("Courses");
    
        }
    }
    
    public void loadComboBoxSchedule(String id,String period) throws ListException{
        //Para cargar un combobox
        TimeTable t = Util.Utility.getScheduleByCourseID(id, period);
        cmbSchedule.getItems().clear();
        if(t!=null){
                cmbSchedule.getItems().add(t.getSchedule1());
                cmbSchedule.getItems().add(t.getSchedule2());
                
            
//        cmbPeriod.setValue("1-2020");
        cmbSchedule.getSelectionModel().select("Schedule");
        }else{
        cmbSchedule.getItems().add("Not defined");
        cmbSchedule.getSelectionModel().select("Schedule");
        }
    }
    
    public void loadComboBoxPeriod(){
        //Para cargar un combobox
            for (int i = 2021; i <= 2021; i++) {
                cmbPeriod.getItems().add("1-"+i);
                cmbPeriod.getItems().add("2-"+i);
                cmbPeriod.getItems().add("3-"+i);
            }
        cmbPeriod.setValue("1-2020");
        cmbPeriod.getSelectionModel().select("Period");
    }

    @FXML
    private void btnEnroll(ActionEvent event) {
    }

    @FXML
    private void btnCancel(ActionEvent event) {
    }

    @FXML
    private void loadComboboxSchedules(ActionEvent event) throws ListException {
        if(!cmbCourse.getValue().equals("Courses")&&!cmbPeriod.getValue().equals("Period")){
//            
                loadComboBoxSchedule(Util.Utility.getIDofString(cmbCourse.getValue()), cmbPeriod.getValue());
        }
            
    }

    
    
 }    
    
