package model.course;

import util.IdGenerator;

public class Course {
    private final int id;
    private String title;
    private String description;
    private String instructorName;
    private int durationInHours;
    private String difficultyLevel;
    private String status;
    
    public Course(String title, String description, String instructorName, int durationInHours, String difficultyLevel, String status) {

    	this.id = IdGenerator.generate(Course.class);
    	this.title = title;
    	this.description = description;
    	this.instructorName = instructorName;
    	this.durationInHours = durationInHours;
    	this.difficultyLevel = difficultyLevel;
    	this.status = status;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    
    public String getInstructorName() {
        return instructorName;
    }
    
    public int getDurationInHours() {
        return durationInHours;
    }
    
    public String getDifficultyLevel() {
        return difficultyLevel;
    }
    
    public String getStatus() {
        return status;
    }
    
    public boolean isActive() {
        return status.equals("ACTIVE");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
