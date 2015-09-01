package io.sphere.sdk.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MoneyFieldType extends FieldTypeBase {

    @JsonCreator
    private MoneyFieldType() {
    }

    @JsonIgnore
    public static MoneyFieldType of() {
        return new MoneyFieldType();
    }
}
