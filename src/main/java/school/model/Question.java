package school.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {
    private String question;
    private String questionType;
    private String answers;
    private String answer;

    @Override
    public String toString() {
        return String.format("{\"question\":\"%s\",\"questionType\":\"%s\",\"answers\":\"%s\",\"answer\":\"%s\"}", question, questionType, answers, answer);
    }
}
