package com.wk.ti.assistant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nlp2sql_result", schema = "assistant")
public class SQLAgentResultEntity extends AgentGeneralEntity {
    @Id
    @GeneratedValue(generator = "nlp2sql_result_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "nlp2sql_result_id_seq", sequenceName = "nlp2sql_result_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "sql_text")
    private String sqlText;

}
