package com.company;

import java.util.ArrayList;

/**
 * Created by dbaker on 2/15/2019.
 */
public class Gradebook {
    //properties
    //list/array of sections
    ArrayList<Section> sections;
    String currentSectionName;

    public Gradebook(){
        sections = new ArrayList<>();
        currentSectionName = "";
    }

    //methods

    //sets the currently active section - needs to set the currently active section
    //to the section with the given sectionName.
    // Returns: true if it succeeds false if it fails(no section that matches that name).
    public boolean changeSection(String sectionName){
        String oldSection = currentSectionName;
        currentSectionName = sectionName;
        if(getCurSectionIndex() == -1){
            currentSectionName = oldSection;
            return false;
        }
       return true;
    }

    //creates a new section if there are not already 6 sections
    // and sectionName is not already used , also need to set the currentSection to be the new section
    // Returns: true if it succeeds false if it fails.
    public boolean addSection(String sectionName){
        String oldSection = currentSectionName;
        currentSectionName= sectionName;
        if(getCurSectionIndex() == -1) {
            sections.add(new Section(sectionName));
            return true;
        }
        //do not add so do not modify current section
        currentSectionName = oldSection;
        return false;
    }
    //Creates a new student and adds the student to the currently active section
    //returns: True if it succeeds,
    // false if it fails(a student with the same username already exists) or no Sections
    public boolean addStudent(String firstName, String lastName, String username, long phoneNumber){
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.addStudent(firstName, lastName, username, phoneNumber);
        }
        //currentSectionName is invalid
        return false;
    }

    //returns overall score (total points earned for all assignments/ total points possible) as a percentage for specified student
    //returns -1 if the student was not found or if the student did not have any assignments assigned. or no Sections
    public double getOverallScore(String username) {
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.getOverallScore(username);
        }
        //currentSectionName is invalid
        return -1;
    }
    //returns overall score as a percentage for the current section - (the average of each students overall score.)
    //Each student should have the same weight even if they have different number of points possible
    //IE: get each student's overall score as a percentage and calculate the average percentage for the students .
    //do not count students with no assignments assigned in the average.
    //returns -1 if there were no assignments in the section.
    public double getOverallScoreAvg(){
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.getOverallScoreAvg();
        }
        //currentSectionName is invalid
        return -1;
    }

    //creates a new assignment and adds it to the student specified by username.
    //Returns true if it succeeds and false if the student is not found or the student already has an assignment with the
    //same assignmentName
    public boolean addAssignmentToStudent(String username, String assignmentName, int pointsPossible){
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.addAssignmentToStudent(username, assignmentName, pointsPossible);//FIXME
        }
        //currentSectionName is invalid
        return false;
    }

    //checks to make sure no student already has an assignment with the same assignment name first
    //if there is no existing assignment it creates a new assignment and adds the assignment to all
    // the students in the current section.
    //returns: True if successful and false if at least one student has the same assignment name
    public boolean addAssignmentToSection(String assignmentName, int pointsPossible){
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.addAssignmentToSection(assignmentName, pointsPossible);
        }
        //currentSectionName is invalid
        return false;
    }

    //sets the pointsEarned property for the student specified in the current section for the assignment specified
    //returns: true if successful, false if the student is not found or the assignmentName is not found.
    public boolean setScore(String username, String assignmentName, int pointsEarned){
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.setScore(username,assignmentName, pointsEarned);
        }
        //currentSectionName is invalid
        return false;
    }

    //returns score on assignment specified as a percentage for the specified student in the current section
    //returns -1 if the student was not found or the student did not have an assignment with the specified assignment name
    public double getAssignmentScorePercent(String username, String assignmentName){
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.getAssignmentScorePercent(username,assignmentName);
        }
        //currentSectionName is invalid
        return -1;
    }

    //returns the average score as a percentage for the current section.
    //only the student with the assignment assigned to them will be counted in the average.
    //returns -1 if no student had an assignment with the specified assignment name
    public double getAssignmentScoreAvg(String assignmentName){
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.getAssignmentScoreAvg(assignmentName);
        }
        //currentSectionName is invalid
        return -1;
    }

    //adds 1 to the specified student's tardy count.
    //returns: true if successful, false if the student was not found in the current section
    public boolean markTardy(String username) {
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.markTardy(username);
        }
        //currentSectionName is invalid
        return false;
    }

    //adds 1 to the specified student's absent count.
    //returns: true if successful, false if the student was not found in the current section
    public boolean markAbsent(String username){
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.markTardy(username);
        }
        //currentSectionName is invalid
        return false;
    }

    //returns the student tardy count for the specified student, it returns -1 if the student was not found.
    public int getTardyCount(String username){
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.getTardyCount(username);
        }
        //currentSectionName is invalid
        return -1;
    }

    //returns the student tardy count for the specified student, it returns -1 if the student was not found.
    public int getAbsentCount(String username){
        Section curSection = getCurSection();
        if (curSection != null){
            return curSection.getAbsentCount(username);
        }
        //currentSectionName is invalid
        return -1;
    }

    //returns -1 if not found
    private int getCurSectionIndex(){
        int sectionIndex =-1;
        for(int i=0; i <sections.size(); i++){
            if(currentSectionName.equalsIgnoreCase(sections.get(i).getSectionName())){
                sectionIndex =i;
                break;
            }
        }
        return sectionIndex;
    }

    //returns null if no current section found
    private Section getCurSection(){
        Section curSection = null;
        int sectionIndex  = getCurSectionIndex();
        if(sectionIndex != -1){
            curSection = sections.get(sectionIndex);
        }
        return curSection;
    }


}
