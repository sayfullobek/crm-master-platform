package uTeamCrm.Crmmasterplatform.entity;

import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Help extends AbsNameEntity { // ushbu classni ochishimizdan maqsadkim foydalanuvchilarga crm systemdan qanday foydalanishni o'rgatish uchun

    @Column
    private UUID videoId; // ushbu yordam videosi

    private String link; // ushbu yordam haqida qisaqacha link



}
