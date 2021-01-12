package cn.cy.course.pojo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AnxuVim
 * @version 1.0
 * @date 2021/1/11 17:01
 */
public class CategoryCountVo implements Serializable {

    public static class NameToCount implements Serializable {

        private String name;
        private int value;

        public NameToCount() {
        }

        public NameToCount(String name, int count) {
            this.name = name;
            this.value = count;
        }

        @Override
        public String toString() {
            return "NameToCount{" +
                    "name='" + name + '\'' +
                    ", count=" + value +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public CategoryCountVo(int n) {
        this.names = new ArrayList<>(n);
        this.nameCountList = new ArrayList<>(n);
    }

    //课程分类名称
    private List<String> names;

    //课程总数
    private List<NameToCount> nameCountList;

    public void add(String name, int count) {
        this.names.add(name);
        this.nameCountList.add(new NameToCount(name, count));
    }

    @Override
    public String toString() {
        return "CategoryCountVo{" +
                "names=" + names +
                ", nameCountList=" + nameCountList +
                '}';
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<NameToCount> getNameCountList() {
        return nameCountList;
    }

    public void setNameCountList(List<NameToCount> nameCountList) {
        this.nameCountList = nameCountList;
    }
}
