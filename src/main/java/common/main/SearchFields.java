package common.main;

/**
 * Created by Alexander on 04.03.2017.
 */
import org.springframework.stereotype.Component;

@Component
public class SearchFields {
    private String searchName="";
    private Integer searchAge;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Integer getSearchAge() {
        return searchAge;
    }

    public void setSearchAge(Integer searchAge) {
        this.searchAge = searchAge;
    }
}
