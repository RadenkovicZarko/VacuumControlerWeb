package nvp3.backendnvp3.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SearchRequest {
    private String name;
    private List<String> statusList;
    private Date dateFrom;
    private Date dateTo;
}
