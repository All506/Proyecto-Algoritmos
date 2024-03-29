/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
//Security{user=302740270, password=C07872}
import Domain.CircularDoublyLinkList;
import Domain.ListException;
import Domain.SinglyLinkList;
import Objects.Course;
import Objects.TimeTable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LuisGa
 */
public class NewTimeTableController implements Initializable {

    @FXML
    private ComboBox<String> cmbCourses;
    @FXML
    private Spinner<String> spnDay1;
    @FXML
    private Spinner<Integer> spnEnd2;
    @FXML
    private Spinner<Integer> spnStart2;
    @FXML
    private Spinner<String> spnDay2;
    @FXML
    private Spinner<Integer> spnStart1;
    @FXML
    private Spinner<Integer> spnEnd1;
    @FXML
    private Button btnClean;
    @FXML
    private Button btnValidSave;
    @FXML
    private ComboBox<String> cmbPeriod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadComboBoxCourses();
        this.loadSpinnerDays();
        this.loadComboBoxPeriod();
        this.loadSpinnerStart();
       
    }    
    
    
      //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
      //Description:Carga el combobox de cursos con todos los diponibles
       public void loadComboBoxCourses(){
        //Para cargar un combobox
        CircularDoublyLinkList tempCourses = new CircularDoublyLinkList();
        tempCourses = Util.Utility.getListCourse();
        String temporal = "";
        if(!tempCourses.isEmpty()){
        try {
            for (int i = 1; i <= tempCourses.size(); i++) {
                Course c = (Course)tempCourses.getNode(i).getData(); 
                temporal = c.getId()+"-"+c.getName();
                this.cmbCourses.getItems().add(temporal);
                        }
        } catch (ListException ex) {
            Logger.getLogger(NewStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        cmbCourses.setValue(temporal);
        cmbCourses.getSelectionModel().select("Courses");
    }

      //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
      //Description:Carga el combobox de dias, con los 6 dias habiles para matricular.
      public void loadSpinnerDays(){
      
      ObservableList<String> days = FXCollections.observableArrayList(
            "Saturday" ,"Friday" ,"Thursday" ,"Wednesday" ,"Tuesday" ,"Monday");
      
      // Value factory.
       SpinnerValueFactory<String> valueFactory = //
               new SpinnerValueFactory.ListSpinnerValueFactory<String>(days);
       SpinnerValueFactory<String> valueFactory2 = //
               new SpinnerValueFactory.ListSpinnerValueFactory<String>(days);
       
       valueFactory.setValue("Monday");
       valueFactory2.setValue("Monday");
    
 
       spnDay1.setValueFactory(valueFactory);
       spnDay2.setValueFactory(valueFactory2);
      
      } 
      
      //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
      //Description:Carga el combobox start con las horas de inicio habiles para un curso 
      public void loadSpinnerStart(){
      
      ObservableList<Integer> start = FXCollections.observableArrayList(
            7,8,9,10,11,12,13,14,15,16,17,18,19,20);

      // Value factory.
       SpinnerValueFactory<Integer> valueFactory = //
               new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(start);
       SpinnerValueFactory<Integer> valueFactory2 = //
               new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(start);
       
       valueFactory.setValue(7);
       valueFactory2.setValue(7);
    
 
       spnStart1.setValueFactory(valueFactory);
       spnStart2.setValueFactory(valueFactory2);
       
       ObservableList<Integer> end = FXCollections.observableArrayList(
            8,9,10,11,12,13,14,15,16,17,18,19,20,21);

      // Value factory.
       SpinnerValueFactory<Integer> valueFactory3 = //
               new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(end);
       SpinnerValueFactory<Integer> valueFactory4 = //
               new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(end);
       
       valueFactory3.setValue(8);
       valueFactory4.setValue(8);
    
 
       spnEnd1.setValueFactory(valueFactory3);
       spnEnd2.setValueFactory(valueFactory4);
      
      }
      //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
      //Description:carga el spinner ending con las horas de finalización habiles para un curso 
      public void loadSpinnerEnding(){
      
      ObservableList<Integer> ending = FXCollections.observableArrayList();
          for (int i = (Integer)spnStart1.getValue(); i < 21; i++) {
             ending.add(i+1); 
          }
       SpinnerValueFactory<Integer> valueFactory = //
               new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(ending);
       spnEnd1.setValueFactory(valueFactory);
      }
      
      
      public void loadSpinnerEnding2(){
      
      ObservableList<Integer> ending = FXCollections.observableArrayList();
          for (int i = (Integer)spnStart2.getValue(); i < 21; i++) {
             ending.add(i+1); 
          }
       SpinnerValueFactory<Integer> valueFactory = //
               new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(ending);
       spnEnd2.setValueFactory(valueFactory);
      }
      //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
      //Description:Carga el combobox period con el periodo respectivo a la fecha actual. 
      public void loadComboBoxPeriod(){
          
        java.util.Date d = java.sql.Date.valueOf(java.time.LocalDate.now());
        //Para cargar un combobox
        String period = "";
        
        switch((int)d.getMonth()){
            case 1: case 2: case 3: case 0:
                period+=1;
                break;
            case 4: case 5: case 6: case 7:
                period+=2;
                break;
            case 8: case 9: case 10: case 11:
                period+=3;
                break;
        }
        period+="-"+(d.getYear()+1900);
        this.cmbPeriod.getItems().add(period);

        
        cmbPeriod.getSelectionModel().select("Period");
    }
      
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
    //Description:Resetea el valor de los combobox 
    @FXML
    private void btnClean(ActionEvent event) {
        cmbCourses.getSelectionModel().select("Courses");
        this.loadSpinnerDays();
        cmbPeriod.getSelectionModel().select("Period");
        this.loadSpinnerStart(); 
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
    //Description:
    @FXML
    private void btnValidSave(ActionEvent event) throws ListException {
        
        int i1=spnStart1.getValue();
        int i2=spnStart2.getValue();
        int f1=spnEnd1.getValue();
        int f2=spnEnd2.getValue();
        
        
        if(spnDay1.getValue().equals(spnDay2.getValue())&& scheduleClash(i1,f1,i2,f2)){

            callAlert("alert", "¡Schedule Clash!", "Course schedules cannot collide.");
        }else{
 
            if(cmbCourses.getValue().equalsIgnoreCase("Courses")||cmbPeriod.getValue().equalsIgnoreCase("Period")){
            
                callAlert("alert", "¡Incomplete data!", "You must select a course and a period");
                
            }else{
//                if(btnValidSave.getText().equalsIgnoreCase("Validate and update")){
//                    System.out.println("88");
//                    Util.Utility.getListSchedule().remove(new TimeTable(Util.Utility.getIDofString(cmbCourses.getValue()),"", "", ""));
//                }
                TimeTable t = new TimeTable(Util.Utility.getIDofString(cmbCourses.getValue()),
                        cmbPeriod.getValue(),
                        spnDay1.getValue().substring(0, 3)+" "+Util.Utility.hourFormat(spnStart1.getValue())+"-"+Util.Utility.hourFormat(spnEnd1.getValue()), 
                        spnDay2.getValue().substring(0, 3)+" "+Util.Utility.hourFormat(spnStart2.getValue())+"-"+Util.Utility.hourFormat(spnEnd2.getValue()));
              
                if(btnValidSave.getText().equalsIgnoreCase("Validate and update"))
                    Util.Utility.getListSchedule().remove(t);
                
                Util.Utility.setListSchedule(t);
                btnClean(event);
                callAlert("notification", "Notification", "Schedule has been saved");
            }
        }
        
    }

    
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
      //Description:
    @FXML
    private void spn2MouseClicked(MouseEvent event) {
        loadSpinnerEnding2();
    }

    @FXML
    private void spn1MouseClicked(MouseEvent event) {
        loadSpinnerEnding();
    }

    
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
      //Description:
    private Boolean scheduleClash(int i1, int f1, int i2, int f2) {
        String h1="";      
        for (int j = i1; j < f1; j++) {
            h1+=j;
        }
        
        for (int k = i2; k < f2; k++) {
            if(h1.contains(""+k)){  
                return true;
            }
        }
        return false;
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
      //Description:
    private void callAlert(String fxmlName, String title, String text) {
        //Se llama la alerta
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/" + fxmlName + ".fxml"));
            Parent root1;
            root1 = (Parent) loader.load();
            //Se llama al controller de la nueva ventana abierta
            AlertController controller = loader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Alerta");
            //Se le asigna la información a la controladora
            controller.setText(title, text);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - -
    //Description:Valida que el horario no tenga un horario ya definido.
    @FXML
    private void cmbPeriodAction(ActionEvent event) throws ListException {
        CircularDoublyLinkList s = Util.Utility.getListCourse();
        SinglyLinkList sch = Util.Utility.getListSchedule();
        Course find=null;
        btnValidSave.setText("Validate and save");
        if(!s.isEmpty()&&!sch.isEmpty()&&!cmbCourses.getValue().equals("Courses")&&!cmbPeriod.getValue().equals("Period")){
        for (int i = 1; i <=s.size() ; i++) {
            Course c = (Course)s.getNode(i).data;
            if(c.getId().equals(Util.Utility.getIDofString(cmbCourses.getValue()))){ 
                find=(Course)s.getNode(i).data;;
                i=  s.size()+1;
             
            }
            }
            for (int j = 1; j <= sch.size(); j++) {
                TimeTable t = (TimeTable)sch.getNode(j).data;

                if(find.getId().equals(t.getID())&&t.getPeriod().equals(cmbPeriod.getValue())){

            //"ya tiene horario"
            callAlert("alert", "¡schedule already exists!", "The selected course already has \n schedule. Continue to update it.");
            btnValidSave.setText("Validate and update");
            }
           } 
        
     }else{
     btnValidSave.setText("Validate and save");   
     }
   
    }
 
}
