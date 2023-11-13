package use_case;

public class SearchInputData {

    final private String keyword;

    public SearchInputData(String keyword) {
        this.keyword = keyword;
    }

    String getKeyword() {
        return this.keyword;
    }
}
