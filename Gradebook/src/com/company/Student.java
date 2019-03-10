package com.company;

import java.util.ArrayList;

/**
 * Created by dbaker on 2/15/2019.
 */
public class Student {
    private String firstName;
    private String lastName;
    private String username;
    private long phoneNumber;
    //list of Assignments
    private ArrayList<Assignment> assignments;
    private int daysAbsent;
    private int daysTardy;

    public Student(String firstName, String lastName, String username, long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        assignments = new ArrayList<>();
        daysAbsent = 0;
        daysTardy = 0;
    }
    public int getDaysAbsent() {
        return daysAbsent;
    }

    public int getDaysTardy() {
        return daysTardy;
    }

    public String getUsername() {
        return username;
    }
    //returns -1 if no assignments for student
    //total points earned for all assignments / total points possible
    public double getOverallScore(){
        double totalPtsEarned =0;
        double totalPtsPossible =0;
        if(assignments.size() ==0){
            return -1;
        }
        for(int i=0; i<assignments.size(); i++){
            totalPtsEarned = totalPtsEarned + assignments.get(i).getPointsEarned();
            totalPtsPossible = totalPtsPossible + assignments.get(i).getPointsPossible();
        }
       double OverallScore = totalPtsEarned / totalPtsPossible;
        return  OverallScore;
    }
    //returns true if assignment was added.
    //returns false if assignment was not added because the student already has an assignment with that name
    public boolean addAssignment(String assignmentName, int pointsPossible){
       if(assignmentNameIsUnique(assignmentName)){
           assignments.add(new Assignment(pointsPossible,assignmentName));
           return  true;
       }
       //name was not unique student already has assignment with name
        return false;
    }
    //returns true if score is set
    //returns false if assignment is not found
    public boolean setScore(String assignmentName, int pointsEarned){
        Assignment assignment = getAssignment(assignmentName);
        if (assignment != null){
            assignment.setPointsEarned(pointsEarned);
            return true;
        }
        //no assignment with assignmentName found
        return false;
    }
    //returns the assignment score as a percentage
    //returns -1 if assignment was not found.
    public double getAssignmentScorePercent(String assignmentName){
        Assignment assignment = getAssignment(assignmentName);
        if (assignment != null){
            return (((double)assignment.getPointsEarned())/assignment.getPointsPossible())*100.0;
        }
        //no assignment with assignmentName found
        return -1;
    }
    //returns true if assignment does not exist for student
    public boolean assignmentNameIsUnique(String assignmentName){
        for(int i=0; i<assignments.size(); i++){
            if(assignments.get(i).getAssignmentName().equalsIgnoreCase(assignmentName)){
                return false; //already Exists, name is not unique
            }
        }
        return true;
    }

    public void markTardy(){
        daysTardy++;
    }
    public void markAbsent(){
        daysAbsent++;
    }
    private int getAssignmentIndex(String assignmentName){
        int sectionIndex =-1;
        for(int i=0; i <assignments.size(); i++){
            if(assignmentName.equalsIgnoreCase(assignments.get(i).getAssignmentName())){
                sectionIndex =i;
                break;
            }
        }
        return sectionIndex;
    }

    //returns null if no current section found
    private Assignment getAssignment(String assignmentName){
        Assignment assignment = null;
        int assignmentIndex  = getAssignmentIndex(assignmentName);
        if(assignmentIndex != -1){
            assignment = assignments.get(assignmentIndex);
        }
        return assignment;
    }

}
