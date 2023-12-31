package com.jpa.expert.entity.car;

import com.jpa.expert.aduting.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_CAR_OWNER")
@Getter @Setter @ToString(callSuper = true, exclude = {"carRegistrations"})
// 사용자 정의 SQL 삭제 구문으로 소프트 삭제를 수행합니다.
@SQLDelete(sql = "UPDATE TBL_CAR_OWNER SET DELETED = 1 WHERE ID = ?")
// 하이버네이트 필터를 사용하여 삭제된 레코드를 제외합니다.
@Where(clause = "DELETED = 0")
public class CarOwner extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String carOwnerName;
    @NotNull private int carOwnerAge;
    @Embedded private CarOwnerAddress carOwnerAddress;
    // 레코드가 삭제되었는지 여부를 나타내는 플래그입니다.
    private boolean deleted = Boolean.FALSE;
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "carOwner")
    private List<CarRegistration> carRegistrations = new ArrayList<>();

    public void setCarRegistration(CarRegistration carRegistration){
        if (carRegistration.getCarOwner()!= this){
            carRegistration.setCarOwner(this);
        }
        this.carRegistrations.add(carRegistration);
    }
}
