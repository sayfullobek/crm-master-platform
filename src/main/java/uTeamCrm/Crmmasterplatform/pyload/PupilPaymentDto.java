package uTeamCrm.Crmmasterplatform.pyload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PupilPaymentDto {
    private UUID screenPay;
    private String userName;
    private UUID userId;
    private String name;
    private String surname;
    private String phoneNumber;
}
