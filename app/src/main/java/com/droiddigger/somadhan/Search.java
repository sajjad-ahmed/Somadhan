package com.droiddigger.somadhan;

/**
 * Created by Sajjad Ahmed on 5/18/2017.
 */

public class Search {
    public String searchText = "";
    public String searchCategory = "";

    public Search(String searchText, String searchCategory) {
        this.searchText = searchText;
        this.searchCategory = searchCategory;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

}
