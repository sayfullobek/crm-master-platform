package uTeamCrm.Crmmasterplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentContent extends AbsEntity {
    @OneToOne
    private Attachment attachment;

    private byte[] bytes;
}
