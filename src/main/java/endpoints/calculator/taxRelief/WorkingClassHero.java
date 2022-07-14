package endpoints.calculator.taxRelief;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.math.*;
import java.time.*;
import java.time.format.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkingClassHero {
    private String birthday;
    private String gender;
    private String name;
    private String natid;
    private String salary;
    private String tax;
    private String relief;

    private final DateTimeFormatter dateTimeFormatPattern = DateTimeFormatter.ofPattern("ddMMyyyy");

    private BigDecimal getVariable(int age){
        double result;
        if (age <= 18)
            result = 1;
        else if (age <= 35)
            result = 0.8;
        else if (age <= 50)
            result = 0.5;
        else if (age <= 75)
            result = 0.367;
        else
            result = 0.05;
        return new BigDecimal(result);
    }

    private BigDecimal getGenderBonus() throws Exception {
        BigDecimal result;
        if (this.gender.equals("F"))
            result = new BigDecimal(500);
        else if (this.gender.equals("M"))
            result = new BigDecimal(0);
        else {
            throw new Exception("Gender is not defined!");
        }
        return result;
    }

    public BigDecimal calculateTaxRelief() throws Exception {
        int age = Period.between(LocalDate.parse(this.birthday, dateTimeFormatPattern), LocalDate.now()).getYears();
        BigDecimal taxRelief = new BigDecimal(salary).subtract(new BigDecimal(tax))
                                                    .multiply(this.getVariable(age))
                                                    .add(this.getGenderBonus());

        if (taxRelief.setScale(0, RoundingMode.FLOOR).compareTo(new BigDecimal("50")) == -1)
            taxRelief = new BigDecimal("50.00");

        return taxRelief;
    }

    private int numberOfDecimal(BigDecimal bigDecimal) {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return (index < 0) ? 0 : (string.length() - index - 1);
    }

    private BigDecimal normalRoundedRule(BigDecimal taxRelief) {

        BigDecimal centsPart = taxRelief.remainder(BigDecimal.ONE).stripTrailingZeros();

        if (numberOfDecimal(centsPart) > 2){
            centsPart = centsPart.setScale(2, RoundingMode.DOWN);//truncated to second decimal place - AC6
            if (centsPart.compareTo(new BigDecimal("0.50")) == 1 || centsPart.compareTo(new BigDecimal("0.50")) == 0) {
                taxRelief = taxRelief.setScale(2, RoundingMode.CEILING);//>=0.5
            } else {
                taxRelief = taxRelief.setScale(2, RoundingMode.FLOOR);//<0.5
            }
        } else {
            taxRelief = taxRelief.setScale(0, RoundingMode.FLOOR);//AC4
        }
        return taxRelief.setScale(2);
    }

    public WorkingClassHero(){
        super();
    }

    public void setRelief () throws Exception {
        this.relief = normalRoundedRule(calculateTaxRelief()).toString();
    }

    public WorkingClassHero(String natid, String name, String gender, String salary, String birthday, String tax) throws Exception {
        this.natid = natid;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.birthday = birthday;
        this.tax = tax;
        this.relief = normalRoundedRule(calculateTaxRelief()).toString();
    }
}