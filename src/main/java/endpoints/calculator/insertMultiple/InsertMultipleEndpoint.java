package endpoints.calculator.insertMultiple;

import endpoints.calculator.taxRelief.*;
import shared.*;

import java.util.*;

public class InsertMultipleEndpoint extends BaseEndpoint implements IEndpoint{

    private final Class<WorkingClassHero[]> classType = WorkingClassHero[].class;

    public final String path = "/calculator/insertMultiple";

    public InsertMultipleEndpoint() {
        super.initRequestBody(classType, "insertMultiple/insertMultipleRequestBody.json");
    }

    public void convertResponseToDataModel(String response) {
        super.convertJsonToDataModelArray(response, classType);
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
