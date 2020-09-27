package com.travel.agent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long locationId;
    
    private Long userId;
    
    private String status;
    
    private int privacy;

    public Feedback() {
    }

    public Feedback(Long userId, Long locationId, String status, int privacy) {
        this.userId = userId;
        this.locationId = locationId;
        this.status = status;
        this.privacy = privacy;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPrivacy() {
		return privacy;
	}

	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}

	@Override
    public String toString() {
        return "Status {" +
                "ID=" + id +
                ", User ID=" + userId +
                ", Privacy ID=" + privacy +
                ", Location ID =" + locationId +
                ", Status='" + status + '\'' +
                '}';
    }
}


