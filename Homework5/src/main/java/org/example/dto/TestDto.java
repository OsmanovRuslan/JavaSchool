package org.example.dto;

import java.util.Objects;

public class TestDto {

    public static final String MONDAY = "MONDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String WRONG = "EXAMPLE";

    public Integer field1;
    public Boolean field2;
    private String privateField1;
    private Integer privateField2;

    public TestDto(Integer field1, Boolean field2, String privateField1, Integer privateField2) {
        this.field1 = field1;
        this.field2 = field2;
        this.privateField1 = privateField1;
        this.privateField2 = privateField2;
    }

    public Integer getField1() {
        return field1;
    }

    public void setField1(Integer field1) {
        this.field1 = field1;
    }

    public Boolean getField2() {
        return field2;
    }

    public void setField2(Boolean field2) {
        this.field2 = field2;
    }

    public String getPrivateField1() {
        return privateField1;
    }

    public void setPrivateField1(String privateField1) {
        this.privateField1 = privateField1;
    }

    public Integer getPrivateField2() {
        return privateField2;
    }

    public void setPrivateField2(Integer privateField2) {
        this.privateField2 = privateField2;
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "field1=" + field1 +
                ", field2=" + field2 +
                ", privateField1='" + privateField1 + '\'' +
                ", privateField2=" + privateField2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDto testDto = (TestDto) o;
        return Objects.equals(field1, testDto.field1) &&
                Objects.equals(field2, testDto.field2) &&
                Objects.equals(privateField1, testDto.privateField1) &&
                Objects.equals(privateField2, testDto.privateField2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, privateField1, privateField2);
    }
}
