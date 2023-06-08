package uTeamCrm.Crmmasterplatform.entity.template;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbsWallet extends AbsEntity {

    private Double monthlyProfit;

    private Double allProfit;

}
