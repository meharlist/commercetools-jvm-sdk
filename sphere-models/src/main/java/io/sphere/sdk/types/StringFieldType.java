package io.sphere.sdk.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class StringFieldType extends FieldTypeBase {

    @JsonCreator
    private StringFieldType() {
    }

    @JsonIgnore
    public static StringFieldType of() {
        return new StringFieldType();
    }
}
