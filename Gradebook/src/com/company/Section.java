package com.company;

import java.util.ArrayList;

/**
 * Created by dbaker on 2/15/2019.
 */
public class Section {
    //list of students
    private ArrayList<Student> students;
    private String sectionName;

    public Section(String sectionName) {
        this.sectionName = sectionName;
        students = new ArrayList<>();
    }

    public String getSectionName() {
        return sectionName;
    }

    //if username doesnt exsist add student
    public boolean addStudent(String firstName, String lastName, String username, long phoneNumber){
        if(getStudentIndex(username)==-1){
            students.add(new Student(firstName, lastName, username, phoneNumber));
            return true;
        }
        return false;
    }

    //returns -1 if no student found or no assignments for the student
    public double getOverallScore(String username){
        Student student = getStudent(username);
        if (student != null){
            return student.getOverallScore();
        }
        //no student with username found
        return -1;

    }
    //returns -1 if no students found with assignments
    //returns average overallScore for all students that have at least one assignment
    public double getOverallScoreAvg(){
        double overallScore = 0;
        int numberStudents = 0;
        //go through each student to see if to include in the total
        for(int i=0; i<students.size(); i++){
            double studentScore = students.get(i).getOverallScore();
            //check to make sure they have at least one assignment
            if(studentScore !=-1) {
                overallScore = overallScore + studentScore;
                numberStudents++;
            }
        }
        //no students with an assignment found
        if(numberStudents ==0) {
            return -1;
        }
        return overallScore/numberStudents;
    }

    //returns false if no student found or assignment with assignmentName already exists for the student
    public boolean addAssignmentToStudent(String username, String assignmentName, int pointsPossible){
        Student student = getStudent(username);
        if (student != null){
            return student.addAssignment(assignmentName, pointsPossible);//FIXME
        }
        //no student with username found
        return false;

    }
    //returns false if no student found or assignment with assignmentName already exists for the student
    public boolean addAssignmentToSection(String assignmentName, int pointsPossible){
        //go through each student to see if any student already contains an assignment with the name
        for(int i=0; i<students.size(); i++){
            if(students.get(i).assignmentNameIsUnique(assignmentName)){
                return false;
            }
        }
        //go back through all students and add the assignment
        for (int i=0; i<students.size(); i++){
            students.get(i).addAssignment(assignmentName,pointsPossible);
        }
        return true;

    }
    //sets the score for the specified student
    //returns true if successful
    //returns false if student was not found or assignment was not found
    public boolean setScore(String username, String assignmentName, int pointsEarned){
        Student student = getStudent(username);
        if (student != null){
            return student.setScore(assignmentName, pointsEarned);
        }
        //no student with username found
        return false;

    }
    //returns score on assignment specified as a percentage for the specified student in the current section
    //returns -1 if the student was not found or the student did not have an assignment with the specified assignment name
    public double getAssignmentScorePercent(String username, String assignmentName){
        Student student = getStudent(username);
        if (student != null){
            return student.getAssignmentScorePercent(assignmentName);
        }
        //no student with username found
        return -1;
    }

    //returns -1 if no students found with assignments
    //returns average percentage for all students that have the  assignment
    public double getAssignmentScoreAvg(String assignmentName){
        double overallScore = 0;
        int numberStudents = 0;
        //go through each student to see if to include in the total
        for(int i=0; i<students.size(); i++){
            double studentScore = students.get(i).getAssignmentScorePercent(assignmentName);
            //check to make sure they have the assignment
            if(studentScore !=-1) {
                overallScore = overallScore + studentScore;
                numberStudents++;
            }
        }
        //no students with an assignment found
        if(numberStudents ==0) {
            return -1;
        }
        return overallScore/numberStudents;
    }
    //returns true if marked student successfully
    //returns false if student was not found
    public boolean markTardy(String username){
        Student student = getStudent(username);
        if (student != null){
            student.markTardy();
            return true;
        }
        //no student with username found
        return false;
    }
    //returns true if marked student successfully
    //returns false if student was not found
    public boolean markAbsent(String username){
        Student student = getStudent(username);
        if (student != null){
           student.markAbsent();
           return true;
        }
        //no student with username found
        return false;
    }
    //returns  number of days tardy
    // returns -1 if student was not found
    public int getTardyCount(String username){
        Student student = getStudent(username);
        if (student != null){
            return student.getDaysTardy();
        }
        //no student with username found
        return -1;
    }
    //returns number of days tardy
    //returns -1 if student was not found
    public int getAbsentCount(String username){
        Student student = getStudent(username);
        if (student != null){
            return student.getDaysAbsent();
        }
        //no student with username found
        return -1;
    }

    //returns -1 if username is not found otherwise returns index;
    private int getStudentIndex(String userName){
        int studentIndex =-1;
        for(int i=0; i <students.size(); i++){
            if(userName.equalsIgnoreCase(students.get(i).getUsername())){
                studentIndex =i;
                break;
            }
        }
        return studentIndex;
    }

    //returns null if no current section found
    private Student getStudent(String userName){
        Student student = null;
        int studentIndex  = getStudentIndex(userName);
        if(studentIndex != -1){
            student = students.get(studentIndex);
        }
        return student;
    }

}
