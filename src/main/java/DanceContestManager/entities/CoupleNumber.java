package DanceContestManager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class CoupleNumber {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long leaderNumber;
    private Long followerNumber;

    public CoupleNumber() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeaderNumber() {
        return leaderNumber;
    }

    public void setLeaderNumber(Long leaderNumber) {
        this.leaderNumber = leaderNumber;
    }

    public Long getFollowerNumber() {
        return followerNumber;
    }

    public void setFollowerNumber(Long followerNumber) {
        this.followerNumber = followerNumber;
    }
}
