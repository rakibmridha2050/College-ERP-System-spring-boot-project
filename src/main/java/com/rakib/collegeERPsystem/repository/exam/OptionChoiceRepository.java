package com.rakib.collegeERPsystem.repository.exam;

import com.rakib.collegeERPsystem.entity.exam.OptionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionChoiceRepository extends JpaRepository<OptionChoice, Long> {
    List<OptionChoice> findByQuestion_QuestionId(Long questionId);
}
