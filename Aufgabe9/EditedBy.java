import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@WrittenBy(nameOfCreator = "Finn Zimmer", date ="15.12.2018")
@Retention(RetentionPolicy.RUNTIME)
public @interface EditedBy {
    String editedBy() ;
    String edit() ;


}
