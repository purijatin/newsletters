package types;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


public class UseCases{
    static void test1() {
        CaptureType<String> t1 = new CaptureType<String>() {
        };
        equals(t1.getRawType(), String.class);
    }

    static void test2() {
        CaptureType<List<String>> t1 = new CaptureType<List<String>>() {
        };
        equals(t1.getRawType(), List.class);
        equals(t1.getParamADT().getParameters().get(0).getRawType(), String.class);
    }


    private static void test3() {
        CaptureType<List<List<String>>> t1 = new CaptureType<List<List<String>>>() {
        };
        equals(t1.getParamADT().getRawType(), List.class);
        equals(t1.getParamADT().getParameters().get(0).getRawType(), List.class);
    }

    static class Test4 extends CaptureType<List<String>> {
    }

    static void test4() {
        Test4 test4 = new Test4();
        equals(test4.getParamADT().getRawType(), List.class);
    }

    static class PreTest5<S> extends CaptureType<Integer> {
    }

    static class Test5 extends PreTest5<Integer> {
    }

    static void test5() {
        Test5 test5 = new Test5();
        equals(test5.getTypeParam(), Integer.class);
    }
    //end

    static class Example<S> extends CaptureType<S> {}
    static class SubClass extends Example<Integer> {}

    static void test6() {
        SubClass test6 = new SubClass();
        Class<?> type = (Class<?>)test6.getTypeParam();
        equals(type, Integer.class);
    }
    //end


    class X<T> extends CaptureType<T> {}
    class Y<A, B> extends X<B> {}
    class Z<Q> extends Y<Q, Map<Integer, List<List<List<Integer>>>>> {}

    void test7() {
        Z<String> z = new Z<>();
        TypeADT param = z.getParamADT();
        equals(param.getRawType(), Map.class);
        List<TypeADT> parameters = param.getParameters();
        equals(parameters.get(0).getRawType(), Integer.class);
        equals(parameters.get(1).getRawType(), List.class);
        equals(parameters.get(1).getParameters().get(0).getRawType(), List.class);
        equals(parameters.get(1).getParameters().get(0).getParameters().get(0).getRawType(), List.class);
        equals(parameters.get(1).getParameters().get(0).getParameters().get(0).getParameters().get(0).getRawType(), Integer.class);
    }
    //end


    static void test8() throws IllegalAccessException, InstantiationException {
        CaptureType<int[]> type = new CaptureType<int[]>() {
        };
        equals(type.getRawType(), int[].class);
    }

    static void test9() {
        CaptureType<String[]> type = new CaptureType<String[]>() {
        };
        equals(type.getRawType(), String[].class);
    }

    static class SomeClass<T> extends CaptureType<T> {
    }

    static void test10() {
        SomeClass<String> claz = new SomeClass<>();
        try {
            claz.getRawType();
            throw new RuntimeException("Shouldnt come here");
        } catch (RuntimeException ex) {

        }
    }

    static void equals(Object a, Object b) {
        if (!a.equals(b)) {
            throw new RuntimeException("Test failed. " + a + " != " + b);
        }
    }

}