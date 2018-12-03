package cn.pangpython.mybase.designpatterns.builder;

public class Builder {

    static class Student{
        String name;
        int age;
        String number;
        static class StudentBuilder{
            String name;
            int age;
            String number;

            public StudentBuilder setName(String name){
                this.name = name;
                return this;
            }

            public StudentBuilder setAge(int age){
                this.age = age;
                return this;
            }

            public StudentBuilder setNumber(String number){
                this.number = number;
                return this;
            }

            public Student build(){
                return new Student(this);
            }
        }

        public Student(StudentBuilder builder){
            this.age = builder.age;
            this.name = builder.name;
            this.number = builder.number;
        }
    }

    public static void main(String[] args) {
        Student pangPython = new Student.StudentBuilder().setAge(18).setName("pangPython").setNumber("0001").build();
        System.out.println(pangPython);
    }
}
