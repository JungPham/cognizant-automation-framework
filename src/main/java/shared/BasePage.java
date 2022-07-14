package shared;

import abstracts.BaseInitPage;

public class BasePage extends BaseInitPage {

    public void goToUrl() {
        driver.get(System.getProperty("url"));
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public String getLastPartOfUrl() {
        String url = driver.getCurrentUrl();
        String[] urlSplit = url.split("/");
        return urlSplit[urlSplit.length-1];
    }

    public String getSectionPartOfUrl() {
        String url = driver.getCurrentUrl();
        String[] urlSplit = url.split("#");
        return urlSplit[urlSplit.length-1];
    }
}
