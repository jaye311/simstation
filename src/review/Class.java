package review;

import java.util.List;

public class Class {
    Class extension = null;
    String name;
    Visibility visibility;
    List<Field> fields;
    List<Method> methods;
}

enum Visibility { PRIVATE, PROTECTED, PACKAGE, PUBLIC }

class Method {
    String name;
    Visibility visibility;
    Class returnType;
    List<Parameter> parameters;
}

class Parameter {
    String name;
    Class type;
}

class Field {
    String name;
    Visibility visibility;
    Class type;
}