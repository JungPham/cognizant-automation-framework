package endpoints.calculator.insert;

import endpoints.calculator.taxRelief.*;
import shared.*;

import java.util.*;

public class InsertEndpoint extends BaseEndpoint implements IEndpoint{

    private final Class<WorkingClassHero[]> classType = WorkingClassHero[].class;

    public final String path = "/calculator/insert";

    public InsertEndpoint() {
        super.initRequestBody(classType, "insert/insertRequestBody.json");
    }

    public void convertResponseToDataModel(String response) {

    }

    public List<WorkingClassHero> getListWorkingClassHeroFromJson() throws Exception {
        List<WorkingClassHero> ListWorkingClassHeroRequestBody = new ArrayList<>();
        for (WorkingClassHero i : super.getDataModelAsArray(classType)){
            i.setRelief();
            ListWorkingClassHeroRequestBody.add(i);
        }
        return ListWorkingClassHeroRequestBody;
    }
}
