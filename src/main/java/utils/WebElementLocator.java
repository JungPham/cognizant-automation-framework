package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WebElementLocator {
    private Locator kind;
    private String value;

    public  WebElementLocator Format(Object... args){
        return new WebElementLocator(this.kind, String.format(this.value, args));
    }
}
