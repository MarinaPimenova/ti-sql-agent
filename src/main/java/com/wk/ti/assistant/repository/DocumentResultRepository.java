package com.wk.ti.assistant.repository;

import com.wk.ti.assistant.entity.SQLAgentResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface DocumentResultRepository extends JpaRepository<SQLAgentResultEntity, Long> {
}
