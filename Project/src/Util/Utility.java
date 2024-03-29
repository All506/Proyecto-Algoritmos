/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Controller.AlertController;
import Controller.LogInController;
import Domain.CircularDoublyLinkList;
import Domain.CircularLinkList;
import Objects.Career;
import Domain.DoublyLinkList;
import Domain.ListException;
import Domain.SinglyLinkList;
import Objects.Course;
import Objects.DeEnrollment;
import Objects.Enrollment;
import Objects.Security;
import Objects.Student;
import Objects.TimeTable;
import Security.AES;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.text.MaskFormatter;

public class Utility {

    //Constructores de las listas
    private static SinglyLinkList lStudent = new SinglyLinkList();
    private static SinglyLinkList lSchedule = new SinglyLinkList();
    private static DoublyLinkList lCareer = new DoublyLinkList();
    private static CircularDoublyLinkList lCourse = new CircularDoublyLinkList();
    private static CircularLinkList lSecurity = new CircularLinkList();
    private static CircularDoublyLinkList lEnrollment = new CircularDoublyLinkList();
    private static CircularDoublyLinkList lDeEnrollment = new CircularDoublyLinkList();

    private static int lastEnroll;
    private static int lastDeEnroll;
    private static boolean kindUser = false; //True if user, false if Student
    private static Student userStudent = null;

    //GETS DE LAS LISTAS 
    public static SinglyLinkList getListStudents() {
        return lStudent;
    }

    public static SinglyLinkList getListSchedule() {
        return lSchedule;
    }

    public static DoublyLinkList getListCareer() {
        return lCareer;
    }

    public static CircularDoublyLinkList getListCourse() {
        return lCourse;
    }

    public static CircularLinkList getListSecurity() {
        return lSecurity;
    }

    public static CircularDoublyLinkList getListEnrollment() {
        return lEnrollment;
    }

    public static CircularDoublyLinkList getListDeEnrollment() {
        return lDeEnrollment;
    }

    public static int getLastEnroll() {
        return lastEnroll;
    }

    public static int getLastDeEnroll() {
        return lastDeEnroll;
    }

    public static void setLastEnroll(int lastEnroll) {
        Util.Utility.lastEnroll = lastEnroll;
    }

    public static void setLastDeEnroll(int lastDeEnroll) {
        Util.Utility.lastDeEnroll = lastDeEnroll;
    }

    public static boolean isKindUser() {
        return kindUser;
    }

    public static void setKindUser(boolean kindUser) {
        Utility.kindUser = kindUser;

    }

    public static void setKindUser(boolean kindUser, String userID) throws ListException {
        Utility.kindUser = kindUser;
        Utility.userStudent = getStudentByID(userID);
    }

    public static Student getUserStudent() {
        return userStudent;
    }

    public static void setUserStudent(Student aUserStudent) {
        userStudent = aUserStudent;
    }

    //DELETE NODES DE LAS LISTA
    public static void deleteNodeLStudent(Student std) {
        try {
            if (lStudent.contains(std)) {
                lStudent.remove(std);
            }
        } catch (ListException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //SETLIST DE LAS LISTAS
    public static boolean setListStudent(Student std) throws ListException {
        boolean flag = false;
        if (Utility.lStudent.isEmpty()) {
            Utility.lStudent.add(std);
            flag = true;
        } else {
            if (!lStudent.contains(std)) {
                lStudent.add(std);
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public static boolean setListSchedule(TimeTable sch) throws ListException {
        boolean flag = false;
        if (Utility.lSchedule.isEmpty()) {
            Utility.lSchedule.add(sch);
            flag = true;
        } else {
            if (!lSchedule.contains(sch)) {
                lSchedule.add(sch);
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public static boolean setListCareer(Career car) throws ListException {
        boolean flag = false;
        if (Utility.lCareer.isEmpty()) {
            car.setIDConsecutivo();
            Utility.lCareer.add(car);
            flag = true;
        } else {
            if (!lCareer.contains(car)) {
                car.setIDConsecutivo();
                lCareer.add(car);
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public static boolean setListCareer2(Career car) throws ListException {
        boolean flag = false;
        if (Utility.lCareer.isEmpty()) {
            car.setId(car.getId());
            Utility.lCareer.add(car);
            flag = true;
        } else {
            if (!lCareer.contains(car)) {
                car.setId(car.getId());
                lCareer.add(car);
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public static boolean setListCourse(Course cou) throws ListException {
        boolean flag = false;
        if (Utility.lCourse.isEmpty()) {
            Utility.lCourse.add(cou);
            flag = true;
        } else {
            if (!lCourse.contains(cou)) {
                lCourse.add(cou);
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public static boolean setListSecurity(Security sec) throws ListException {
        boolean flag = false;
        if (Utility.lSecurity.isEmpty()) {
            Utility.lSecurity.add(sec);
            flag = true;
        } else {
            if (!lSecurity.contains(sec)) {
                lSecurity.add(sec);
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public static boolean setListEnrollment(Enrollment enr) throws ListException {
        boolean flag = false;
        int temp = Util.Utility.getLastEnroll();
        if (Utility.lEnrollment.isEmpty()) {
            Util.Utility.setLastEnroll(++temp);
            Utility.lEnrollment.add(enr);
            flag = true;
        } else {
            if (!lEnrollment.contains(enr)) {
                Util.Utility.setLastEnroll(++temp);
                lEnrollment.add(enr);
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public static boolean setListDeEnrollment(DeEnrollment deEnr) throws ListException {

        boolean flag = false;
        int temp = Util.Utility.getLastDeEnroll();
        if (Utility.lDeEnrollment.isEmpty()) {
            Util.Utility.setLastDeEnroll(++temp);
            Utility.lDeEnrollment.add(deEnr);
            flag = true;
        } else {
            if (!lDeEnrollment.contains(deEnr)) {
                Util.Utility.setLastDeEnroll(++temp);
                lDeEnrollment.add(deEnr);
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;

    }

    public static int getCareerByCourse(String courseId) throws ListException {
        Course c = Utility.getCourseByID(courseId);

        return c.getCareerId();
    }

    //UTILIDAD 
    public static int random() {
        return 1 + (int) Math.floor(Math.random() * 99);
    }

    public static int random(int bound) {
        //return 1+random.nextInt(bound);
        return 1 + (int) Math.floor(Math.random() * bound);
    }

    public static String format(double value) {
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }

    public static String hourFormat(int value) {
        return new DecimalFormat("00")
                .format(value);
    }

    public static String $format(double value) {
        return new DecimalFormat("$###,###,###,###.##")
                .format(value);
    }

    public static String perFormat(double value) {
        //#,##0.00 '%'
        return new DecimalFormat("#,##0.00'%'")
                .format(value);
    }

    public static boolean equals(Object a, Object b) { //Objeto de lista, objeto buscado
        switch (instanceOf(a, b)) {
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x.equals(y); //Devuelve un booleano comparando los enteros
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.equalsIgnoreCase(s2); //Devuelve un booleano comparando los String
            case "student":
                Student std1 = (Student) a;
                Student std2 = (Student) b;
                return std1.getStudentID().equalsIgnoreCase(std2.getStudentID());
            case "career":
                Career car1 = (Career) a;
                Career car2 = (Career) b;
                return car1.getDescription().equalsIgnoreCase(car2.getDescription());
            case "course":
                Course cour1 = (Course) a;
                Course cour2 = (Course) b;
                return cour1.getId().equalsIgnoreCase(cour2.getId());
            case "security":
                Security sec1 = (Security) a;
                Security sec2 = (Security) b;
                return sec1.getUser().equals(sec2.getUser()) && sec1.getPassword().equals(sec2.getPassword());
            case "period":
                TimeTable sch1 = (TimeTable) a;
                TimeTable sch2 = (TimeTable) b;
                return sch1.getID().equals(sch2.getID()) && sch1.getPeriod().equals(sch2.getPeriod());
            case "enrollment":
                Enrollment enr1 = (Enrollment) a;
                Enrollment enr2 = (Enrollment) b;
                return enr1.getStudentID().equalsIgnoreCase(enr2.getStudentID()) && enr1.getId() == enr2.getId();
            case "deenrollment":
                DeEnrollment der1 = (DeEnrollment) a;
                DeEnrollment der2 = (DeEnrollment) b;
                return der1.getId() == der2.getId();
            case "timetable":
                TimeTable t1 = (TimeTable) a;
                TimeTable t2 = (TimeTable) b;
                return t1.getID().equalsIgnoreCase(t2.getID());
        }
        return false; //En cualquier otro caso retorna un false
    }

    private static String instanceOf(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) { //Es a un entero?
            return "integer";
        }
        if (a instanceof String && b instanceof String) { //Es a un entero?
            return "string";
        }
        if (a instanceof Student && b instanceof Student) {
            return "student";
        }
        if (a instanceof Career && b instanceof Career) {
            return "career";
        }
        if (a instanceof Security && b instanceof Security) {
            return "security";
        }
        if (a instanceof Course && b instanceof Course) {
            return "course";
        }
        if (a instanceof TimeTable && b instanceof TimeTable) {
            return "period";
        }
        if (a instanceof Enrollment && b instanceof Enrollment) {
            return "enrollment";
        }
        if (a instanceof DeEnrollment && b instanceof DeEnrollment) {
            return "deenrollment";
        }
        if (a instanceof TimeTable && b instanceof TimeTable) {
            return "timetable";
        }

        return "unknown";
    }

    public static String dateFormat(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy")
                .format(date);
    }

    public static boolean emailChecker(String email) {
        //Just send the email and return a boolean if it matches the mail format
        //Nobody knows how the hell the pattern works but it works so...

        //Patrón del correo      
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "gmail.com");
        Matcher coincidence = pattern.matcher(email);
        return coincidence.find();
    }

    public static String getIDofString(String s) {

        int i = 0;
        String x = "";
        while (!("-").contains("" + s.charAt(i))) {
            x += s.charAt(i);
            i++;
        }

        return x;
    }

    public static Student getStudentByID(String id) throws ListException {

        for (int i = 1; i <= lStudent.size(); i++) {
            Student s = (Student) lStudent.getNode(i).data;
            if (String.valueOf(s.getId()).equals(id)) {
                return s;
            }
        }
        return null;
    }

    public static Career getCarrerByID(String id) throws ListException {

        for (int i = 1; i <= lCareer.size(); i++) {
            Career c = (Career) lCareer.getNode(i).data;
            if (String.valueOf(c.getId()).equals(id)) {
                return c;

            }
        }
        return null;
    }

    public static CircularLinkList getCoursesByCarrerID(String id) throws ListException {
        CircularLinkList list = new CircularLinkList();
        if (!lCourse.isEmpty()) {
            for (int i = 1; i <= lCourse.size(); i++) {
                Course c = (Course) lCourse.getNode(i).data;
                if ((c.getCareerId() + "").equals(id) && (courseHasSchedule(c.getId()))) {

                    list.add(c);

                }
            }
        }
        return list;
    }

    public static Course getCourseByID(String id) throws ListException {

        if (!lCourse.isEmpty()) {
            for (int i = 1; i <= lCourse.size(); i++) {
                Course c = (Course) lCourse.getNode(i).data;
                if ((c.getId() + "").equals(id)) {

                    return c;

                }
            }
        }
        return null;
    }

//    public static Course getCourseByDescription(String id) throws ListException {
//
//        if (!lCourse.isEmpty()) {
//            for (int i = 1; i <= lCourse.size(); i++) {
//                Course c = (Course) lCourse.getNode(i).data;
//                if ((c.getId() + "").equals(id)) {
//
//                    return c;
//
//                }
//            }
//        }
//        return null;
//    }
    public static TimeTable getScheduleByCourseID(String id, String period) throws ListException {

        for (int i = 1; i <= lSchedule.size(); i++) {
            TimeTable t = (TimeTable) lSchedule.getNode(i).data;

            if (((t.getID() + "").equals(id)) && (t.getPeriod().equals(period))) {
                return t;

            }
        }
        return null;
    }

    public static void replaceListCourse(CircularDoublyLinkList listToSend) {
        lCourse = listToSend;
    }

    public static boolean courseHasSchedule(String id) throws ListException {
        if (!lSchedule.isEmpty()) {
            for (int i = 1; i <= lSchedule.size(); i++) {
                TimeTable t = (TimeTable) lSchedule.getNode(i).data;
                if (((t.getID() + "").equals(id))) {

                    return true;
                }
            }
        }

        return false;
    }

    public static SinglyLinkList getEnrollmentOfStudentId() throws ListException {

        SinglyLinkList list = new SinglyLinkList();
        if (!lEnrollment.isEmpty()) {
            for (int i = 1; i <= lEnrollment.size(); i++) {
                Enrollment e = (Enrollment) lEnrollment.getNode(i).data;

                if (((e.getStudentID() + "").equals(userStudent.getStudentID()))) {
                    list.add(e);
                }

            }
        }

        return list;
    }

    public static boolean removeEnrollment(DeEnrollment dE) throws ListException {
        CircularDoublyLinkList newLEnrollment = new CircularDoublyLinkList();
        CircularDoublyLinkList oldLEnrollment = lEnrollment;
        boolean flag = false;

        for (int i = 1; i <= oldLEnrollment.size(); i++) {
            Enrollment e = (Enrollment) oldLEnrollment.getNode(i).data;
            if (e.getCourseID().equals(dE.getCourseID()) && e.getStudentID().equals(dE.getStudentID()) && e.getSchedule().equals(dE.getSchedule())) {

                flag = true;
            } else {
                newLEnrollment.add(e);
            }
        }
        Utility.lEnrollment = newLEnrollment;

        return flag;
    }

    public static boolean enrollmentExists(Enrollment enr) throws ListException {
        boolean flag = false;
        CircularDoublyLinkList list = getListEnrollment();
        if (list.isEmpty()) {
            flag = false;
        } else {
            for (int i = 1; i <= list.size(); i++) {
                Enrollment e = (Enrollment) list.getNode(i).data;
                if (enr.getStudentID().equalsIgnoreCase(e.getStudentID()) && enr.getCourseID().equalsIgnoreCase(e.getCourseID()) && getPeriodOfStringDate(enr.getDate()).equalsIgnoreCase(getPeriodOfStringDate(e.getDate()))) {
                    return true;
                }

            }

        }
        return flag;
    }

    public static String getPeriodOfStringDate(Date d) {
        String period = "";
        switch ((int) d.getMonth()) {
            case 1:
            case 2:
            case 3:
            case 0:
                period += 1;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                period += 2;
                break;
            case 8:
            case 9:
            case 10:
            case 11:
                period += 3;
                break;
        }
        return period += "-" + (d.getYear() + 1900);

    }

    public static Boolean scheuleClash(String schedule) throws ListException {
        SinglyLinkList list = getEnrollmentOfStudentId();
        if (!list.isEmpty()) {
            for (int i = 1; i <= list.size(); i++) {
                Enrollment enr = (Enrollment) list.getNode(i).data;
                String h1 = "";
                String d1 = enr.getSchedule().substring(0, 3);
                String d2 = schedule.substring(0, 3);
                int i1 = Integer.parseInt(enr.getSchedule().substring(4, 6));
                int f1 = Integer.parseInt(enr.getSchedule().substring(7, 9));
                int i2 = Integer.parseInt(schedule.substring(4, 6));
                int f2 = Integer.parseInt(schedule.substring(7, 9));

                for (int j = i1; j < f1; j++) {
                    h1 += j;
                }

                for (int k = i2; k < f2; k++) {
                    if (h1.contains("" + k) && d1.equalsIgnoreCase(d2)) {
                        return true;
                    }
                }

            }
        }

        return false;

    }

}
