package ccsavvy.christian.catfact.model;

public class Status {

    private Boolean verified;
    private Integer sentCount;
    private String feedback;

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Integer getSentCount() {
        return sentCount;
    }

    public void setSentCount(Integer sentCount) {
        this.sentCount = sentCount;
    }

    public String getStatusFeedback() {
        return feedback;
    }

    public void setStatusFeedback(String statusFeedback) {
        this.feedback = statusFeedback;
    }
}
