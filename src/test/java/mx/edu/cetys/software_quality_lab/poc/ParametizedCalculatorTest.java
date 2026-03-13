package mx.edu.cetys.software_quality_lab.poc;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParametizedCalculatorTest {

    //@Test
    @DisplayName("Test with CVS Source")
    @ParameterizedTest
    @CsvSource({
            "10,10,20",
            "0,0,0,5",
            "-5,5,0",
            "'1','2','3'"
    })
    void testSumWithCsvSource(int a, int b, int expected) {
        //arrange
        //act
        assertEquals(expected, a + b);
    }

    //@test very suppecios
    @DisplayName("Validate String not empty")
    @ParameterizedTest
    @ValueSource(strings = {
            "hello",
            "world",
            "aaron",
    })
    void testValidateStringNotEmpty(String values) {
        var isEmpty = values.isEmpty();
        assertFalse(isEmpty);
    }

    @ParameterizedTest
    @DisplayName("Validate double of an integer with MethodSource")
    @MethodSource("provideNumbers")
    void testDouble(int a, int expected){
        var doubleValue = a * 2;
        assertEquals(expected, doubleValue);
    }

    public static Stream<Object[]> provideNumbers() {
        return Stream.of(
                new Object[]{2,4},
                new Object[]{5,10}
                // new Object[]{"hola","holahola"}
        );
    }

    @ParameterizedTest
    @DisplayName("Validate Pet is older tham 10 years")
    @MethodSource("providePets")
    void testPets(Pet pet, boolean expected){
        var isOlderThanTen = pet.age() > 10 ? true : false;
        assertEquals(expected, isOlderThanTen);
    }

    public static Stream<Object[]> providePets() {
        return Stream.of(
                new Object[]{new Pet("Andy", 15, "negro", "Perro"), true},
                new Object[]{new Pet(null,10,null,null), false},
                new Object[]{new Pet(null,6,null,null), false},
                new Object[]{new Pet(null,11,null,null), true}
        );
    }

    //POJO - Plain Old Java Object : Clase con getters and setters
    //Records - POJO inmutable sin BoilerPlate
    private record Pet(String name, int age, String color, String race) {}

}
