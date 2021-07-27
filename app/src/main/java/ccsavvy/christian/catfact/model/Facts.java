package ccsavvy.christian.catfact.model;

import com.google.gson.annotations.SerializedName;

public class Facts {

    @SerializedName("_id")
    private String id;

    @SerializedName("_v")
    private Integer v;

    @SerializedName("status.verified")
    private Boolean isStatusVerified;

    private String user;
    private String text;
    private String updatedAt;
    private String sendDate;
    private Boolean deleted;
    private String source;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Boolean getStatusVerified() {
        return isStatusVerified;
    }

    public void setStatusVerified(Boolean statusVerified) {
        isStatusVerified = statusVerified;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
