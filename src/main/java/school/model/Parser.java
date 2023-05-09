package school.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Parser {
    private String title;
    private List<String> ids;
    private List<StringBuilder> content;

    public StringBuilder convertToJson() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < ids.size(); i++)
            str.append("\"")
                    .append(ids.get(i))
                    .append("\"")
                    .append(":")
                    .append("\"")
                    .append(content.get(i))
                    .append("\"")
                    .append(",");
        str.delete(str.length() - 1, str.length());
        str.append("}");
        return str;
    }
}
