package com.bjpowernode.oa.servlet.bean;

import java.util.Objects;

/**
 * 一个普通的java类，这个java类可以封装零散的数据。代表一个部门对象。
 */
public class Dept {
    private String deptno;
    private String dname;
    private String loc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept bean = (Dept) o;
        return Objects.equals(deptno, bean.deptno) && Objects.equals(dname, bean.dname) && Objects.equals(loc, bean.loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptno, dname, loc);
    }

    @Override
    public String toString() {
        return "bean{" +
                "deptno='" + deptno + '\'' +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Dept(String deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Dept() {
    }
}
