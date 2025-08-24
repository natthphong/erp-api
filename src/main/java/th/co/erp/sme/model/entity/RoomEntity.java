package th.co.erp.sme.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user_room")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "access_status")
    private String accessStatus = "PRIVATE";

    @Column(name = "status")
    private String status;

    @Column(name = "is_deleted")
    private String isDeleted = "N";

    @Column(name = "create_date")
    private LocalDateTime createDate;
}
