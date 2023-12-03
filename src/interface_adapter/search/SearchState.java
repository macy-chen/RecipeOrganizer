package interface_adapter.search;

public class SearchState {
    private String keyword;
    private String searchError = null;

    public SearchState(SearchState copy) {
        this.keyword = copy.keyword;
        this.searchError = copy.searchError;
    }

    public SearchState () {}

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    @Override
    public String toString() {
        return "SignupState{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
