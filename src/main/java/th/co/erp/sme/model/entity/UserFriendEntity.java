package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "tbl_user_friend")
@DynamicUpdate
@Data
public class UserFriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "friend_id")
    private Integer friendId;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "status")
    private String status;


    @Column(name = "following_status")
    private String followingStatus;

    @Column(name = "friend_date")
    private LocalDate friendDate;

    @Column(name = "following_date")
    private LocalDateTime followingDate;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "create_by")
    private String createBy;

    @PrePersist
    public void insert(){
        createDate = LocalDateTime.now();
    }

    @PreUpdate
    public void update(){
        updateDate = LocalDateTime.now();
    }
}
