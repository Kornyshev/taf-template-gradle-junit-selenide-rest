package taf.template.tests.annotations;

import org.junit.jupiter.api.extension.ExtendWith;
import taf.template.tests.extensions.RepositoryPreconditionExtension;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtendWith(RepositoryPreconditionExtension.class)
public @interface NewRepository {

    String name();
    String isPrivate();

}
