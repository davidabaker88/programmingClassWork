package com.company;

/**
 * Created by dbaker on 2/15/2019.
 */
public class Assignment {

    private int pointsPossible;



    private int pointsEarned;
    private String assignmentName;

    public Assignment(int pointsPossible, String assignmentName) {
        this.pointsPossible = pointsPossible;
        this.assignmentName = assignmentName;
        pointsEarned = 0;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public int getPointsPossible() {
        return pointsPossible;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public String getAssignmentName() {
        return assignmentName;
    }
}
