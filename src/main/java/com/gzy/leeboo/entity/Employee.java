package com.gzy.leeboo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gzy.leeboo.utils.easyExcel.converter.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 员工
 */
@HeadRowHeight(25)
public class Employee implements Serializable {
    private static final long serialVersionUID = -7528890670934001564L;

    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = {"员工信息表", "工号"})
    private String workID;

    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "姓名"})
    private String name;

    @ExcelProperty(value = {"员工信息表", "性别"})
    private String gender;

    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "出生日期"}, converter = CustomLocalDateConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate birthday;

    @ColumnWidth(20)
    @ExcelProperty(value = {"员工信息表", "身份证号"})
    private String idCard;

    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "民族"}, converter = CustomNationConverter.class)
    private Nation nation;

    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "籍贯"})
    private String nativePlace;

    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "政治面貌"}, converter = CustomPoliticsConverter.class)
    private Politics politics;

    @ExcelProperty(value = {"员工信息表", "婚否"})
    private String wedlock;

    @ColumnWidth(20)
    @ExcelProperty(value = {"员工信息表", "邮箱"})
    private String email;

    @ColumnWidth(14)
    @ExcelProperty(value = {"员工信息表", "手机号码"})
    private String phone;

    @ColumnWidth(26)
    @ExcelProperty(value = {"员工信息表", "地址"})
    private String address;

    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "部门"}, converter = CustomDepartmentConverter.class)
    private Department department;

    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "职称"}, converter = CustomRankConverter.class)
    private Rank rank;

    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "职位"}, converter = CustomPositionConverter.class)
    private Position position;

    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "聘用形式"})
    private String engageForm;

    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "最高学历"})
    private String tiptopDegree;

    @ColumnWidth(18)
    @ExcelProperty(value = {"员工信息表", "毕业院校"})
    private String school;

    @ColumnWidth(18)
    @ExcelProperty(value = {"员工信息表", "专业"})
    private String specialty;

    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "在职状态"})
    private String workState;

    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "入职日期"}, converter = CustomLocalDateConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate beginDate;

    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "转正日期"}, converter = CustomLocalDateConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate conversionDate;

    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "合同起始日期"}, converter = CustomLocalDateConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate beginContract;

    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "合同期限"})
    private Double contractTerm;

    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "合同结束日期"}, converter = CustomLocalDateConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate endContract;

    @ExcelProperty(value = {"员工信息表", "工龄"})
    private Integer workAge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getWedlock() {
        return wedlock;
    }

    public void setWedlock(String wedlock) {
        this.wedlock = wedlock;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEngageForm() {
        return engageForm;
    }

    public void setEngageForm(String engageForm) {
        this.engageForm = engageForm;
    }

    public String getTiptopDegree() {
        return tiptopDegree;
    }

    public void setTiptopDegree(String tiptopDegree) {
        this.tiptopDegree = tiptopDegree;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }

    public String getWorkID() {
        return workID;
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public Double getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Double contractTerm) {
        this.contractTerm = contractTerm;
    }

    public LocalDate getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(LocalDate conversionDate) {
        this.conversionDate = conversionDate;
    }

    public LocalDate getBeginContract() {
        return beginContract;
    }

    public void setBeginContract(LocalDate beginContract) {
        this.beginContract = beginContract;
    }

    public LocalDate getEndContract() {
        return endContract;
    }

    public void setEndContract(LocalDate endContract) {
        this.endContract = endContract;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Politics getPolitics() {
        return politics;
    }

    public void setPolitics(Politics politics) {
        this.politics = politics;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}