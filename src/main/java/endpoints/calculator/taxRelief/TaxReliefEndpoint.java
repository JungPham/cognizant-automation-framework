package endpoints.calculator.taxRelief;

import shared.*;
import java.util.*;

public class TaxReliefEndpoint extends BaseEndpoint implements IEndpoint{

    private final Class<TaxReliefDataModel[]> taxReliefDataModel = TaxReliefDataModel[].class;

    public final String path = "/calculator/taxRelief";

    public void convertResponseToDataModel(String response){
        super.convertJsonToDataModelArray(response, taxReliefDataModel);
    }

    public List<TaxReliefDataModel> getListWorkingClassHeroResponseBody(){
        List<TaxReliefDataModel> ListWorkingClassHeroResponseBody = new ArrayList<>();
        ListWorkingClassHeroResponseBody.addAll(Arrays.asList(super.getDataModelAsArray(taxReliefDataModel)));
        return ListWorkingClassHeroResponseBody;
    }

    public List<WorkingClassHero> getListWorkingClassHeroFromCSV() throws Exception {
        return CSVReader.getListHeroFromCSV();
    }

    public boolean compareNatIdActualWithExpected(List<WorkingClassHero> workingClassHeroListFromCSVOrJson, List<TaxReliefDataModel> workingClassHeroListFromAPI, int position, String sign) throws Exception {
        if (workingClassHeroListFromCSVOrJson.size() == workingClassHeroListFromAPI.size()){
            for (int i =0 ; i < workingClassHeroListFromCSVOrJson.size(); i++){
                String natid = workingClassHeroListFromCSVOrJson.get(i).getNatid();
                natid = natid.substring(0, position - 1) + String.join("", Collections.nCopies(natid.length() - (position - 1), sign));
                System.out.println("Expected natId: " + natid);
                System.out.println("Actual natId: " + workingClassHeroListFromAPI.get(i).getNatid());
                if (!natid.equals(workingClassHeroListFromAPI.get(i).getNatid())) {
                    return false;
                }
                else{
                    continue;
                }
            }
            return true;
        } else {
            throw new Exception("The Name list from CSV is not equal to the list from API returns");
        }
    }

    public boolean compareReliefActualWithExpected(List<WorkingClassHero> workingClassHeroListFromCSVOrJson, List<TaxReliefDataModel> workingClassHeroListFromAPI) throws Exception {
        if (workingClassHeroListFromCSVOrJson.size() == workingClassHeroListFromAPI.size()){
            for (int i =0 ; i < workingClassHeroListFromCSVOrJson.size(); i++){
                System.out.println("Expected relief: " + workingClassHeroListFromCSVOrJson.get(i).getRelief());
                System.out.println("Actual relief: " + workingClassHeroListFromAPI.get(i).getRelief());
                if (!Objects.equals(workingClassHeroListFromCSVOrJson.get(i).getRelief(), workingClassHeroListFromAPI.get(i).getRelief())) {
                    return false;
                }
                else{
                    continue;
                }
            }
            return true;
        } else {
            throw new Exception("The Relief list calculated from CSV is not equal to the list from API returns");
        }
    }

    public boolean compareNameActualWithExpected(List<WorkingClassHero> workingClassHeroListFromCSVOrJson, List<TaxReliefDataModel> workingClassHeroListFromAPI) throws Exception {
        if (workingClassHeroListFromCSVOrJson.size() == workingClassHeroListFromAPI.size()){
            for (int i =0 ; i < workingClassHeroListFromCSVOrJson.size(); i++){
                System.out.println("Expected name: " + workingClassHeroListFromCSVOrJson.get(i).getName());
                System.out.println("Actual name: " + workingClassHeroListFromAPI.get(i).getName());
                if (!Objects.equals(workingClassHeroListFromCSVOrJson.get(i).getName(), workingClassHeroListFromAPI.get(i).getName())) {
                    return false;
                }
                else{
                    continue;
                }
            }
            return true;
        } else {
            throw new Exception("The Name list from CSV is not equal to the list from API returns");
        }
    }
}
