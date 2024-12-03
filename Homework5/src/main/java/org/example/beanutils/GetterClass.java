package org.example.beanutils;

public class GetterClass {

    public Integer field1;
    public String field2;
    public Boolean field3;

    public GetterClass(Integer field1, String filed2, Boolean field3) {
        this.field1 = field1;
        this.field2 = filed2;
        this.field3 = field3;
    }

    public Integer getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public Boolean getField3() {
        return field3;
    }
}
