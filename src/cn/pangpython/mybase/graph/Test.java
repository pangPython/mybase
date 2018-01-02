package cn.pangpython.mybase.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        List<List<Course>> allCourses = new ArrayList();
        add(allCourses);
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0;i<allCourses.size();i++){
            List<Course> courses = allCourses.get(i);
            List<Integer> list = new ArrayList<>();
            for(int j = 0;j<courses.size();j++){
                if(j > i){
                    Course course = courses.get(j);
                    if(!course.isConflict()){
                        list.add(j+1);
                    }
                }
            }
            if(list.size()>0){
                map.put(i+1,list);
            }
        }

        System.out.println("======================");
        for(Map.Entry entry:map.entrySet()){
            System.out.println("key:"+entry.getKey()+"val:"+entry.getValue());
        }
        System.out.println("======================");
    }

    public static void add(List<List<Course>> allCourses){
        List<Course> courses1 = new ArrayList();
        courses1.add(new Course(false,1));
        courses1.add(new Course(true,3));
        courses1.add(new Course(false,6));
        courses1.add(new Course(true,2));
        courses1.add(new Course(false,9));
        courses1.add(new Course(true,4));
        allCourses.add(courses1);

        List<Course> courses2 = new ArrayList();
        courses2.add(new Course(true,3));
        courses2.add(new Course(false,6));
        courses2.add(new Course(true,4));
        courses2.add(new Course(false,7));
        courses2.add(new Course(true,3));
        courses2.add(new Course(false,7));
        allCourses.add(courses2);

        List<Course> courses3 = new ArrayList();
        courses3.add(new Course(false,4));
        courses3.add(new Course(true,1));
        courses3.add(new Course(false,3));
        courses3.add(new Course(true,1));
        courses3.add(new Course(false,9));
        courses3.add(new Course(false,1));
        allCourses.add(courses3);

        List<Course> courses4 = new ArrayList();
        courses4.add(new Course(true,1));
        courses4.add(new Course(false,3));
        courses4.add(new Course(true,1));
        courses4.add(new Course(false,2));
        courses4.add(new Course(true,5));
        courses4.add(new Course(false,1));
        allCourses.add(courses4);

        List<Course> courses5 = new ArrayList();
        courses5.add(new Course(false,1));
        courses5.add(new Course(true,7));
        courses5.add(new Course(false,1));
        courses5.add(new Course(true,9));
        courses5.add(new Course(false,6));
        courses5.add(new Course(true,9));
        allCourses.add(courses5);

        List<Course> courses6 = new ArrayList();
        courses6.add(new Course(true,1));
        courses6.add(new Course(false,1));
        courses6.add(new Course(false,1));
        courses6.add(new Course(false,1));
        courses6.add(new Course(true,1));
        courses6.add(new Course(false,1));
        allCourses.add(courses6);

    }
}
