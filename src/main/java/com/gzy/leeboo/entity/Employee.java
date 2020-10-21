package com.gzy.leeboo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gzy.leeboo.config.validator.annotation.Phone;
import com.gzy.leeboo.config.validator.group.AddEmployee;
import com.gzy.leeboo.config.validator.group.UpdateEmployee;
import com.gzy.leeboo.utils.easyExcel.converter.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 员工
 */
@HeadRowHeight(25)
public class Employee implements Serializable {
    private static final long serialVersionUID = -7528890670934001564L;

    @NotNull(groups = UpdateEmployee.class)
    @Min(value = 1, groups = UpdateEmployee.class)
    @ExcelIgnore
    private Integer id;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^\\d{8}$", groups = AddEmployee.class, message = "工号格式不正确！")
    @ExcelProperty(value = {"员工信息表", "工号"})
    private String workID;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,5}$", groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工姓名格式不正确！")
    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "姓名"})
    private String name;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^男|女$", groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工性别格式不正确！")
    @ExcelProperty(value = {"员工信息表", "性别"})
    private String gender;

    @NotNull(groups = AddEmployee.class)
    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "出生日期"}, converter = CustomLocalDateConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate birthday;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$",
            groups = {AddEmployee.class, UpdateEmployee.class},message = "员工身份证格式不正确！")
    @ColumnWidth(20)
    @ExcelProperty(value = {"员工信息表", "身份证号"})
    private String idCard;

    @Valid
    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "民族"}, converter = CustomNationConverter.class)
    private Nation nation;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{4,12}$", groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工籍贯格式不正确！")
    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "籍贯"})
    private String nativePlace;

    @Valid
    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "政治面貌"}, converter = CustomPoliticsConverter.class)
    private Politics politics;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^已婚|未婚|离异$", groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工婚否状态格式不正确！")
    @ExcelProperty(value = {"员工信息表", "婚否"})
    private String wedlock;

    @NotNull(groups = AddEmployee.class)
    @Email(groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工邮箱格式不正确！")
    @ColumnWidth(20)
    @ExcelProperty(value = {"员工信息表", "邮箱"})
    private String email;

    @NotNull(groups = AddEmployee.class)
    @Phone(groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工手机号码格式不正确！")
    @ColumnWidth(14)
    @ExcelProperty(value = {"员工信息表", "手机号码"})
    private String phone;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^[\\w_-（）\\u4e00-\\u9fa5]{4,24}$", groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工地址格式不正确！")
    @ColumnWidth(26)
    @ExcelProperty(value = {"员工信息表", "地址"})
    private String address;

    @Valid
    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "部门"}, converter = CustomDepartmentConverter.class)
    private Department department;

    @Valid
    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "职称"}, converter = CustomRankConverter.class)
    private Rank rank;

    @Valid
    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "职位"}, converter = CustomPositionConverter.class)
    private Position position;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^劳动合同|劳务合同$", groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工聘用形式格式不正确！")
    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "聘用形式"})
    private String engageForm;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^博士|硕士|本科|大专|高中|初中|小学|其他$",
            groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工最高学历格式不正确！")
    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "最高学历"})
    private String tiptopDegree;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^[\\w（）\\u4e00-\\u9fa5]{4,16}$", groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工毕业院校格式不正确！")
    @ColumnWidth(18)
    @ExcelProperty(value = {"员工信息表", "毕业院校"})
    private String school;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^^[\\w（）\\u4e00-\\u9fa5]{2,12}$", groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工专业格式不正确！")
    @ColumnWidth(18)
    @ExcelProperty(value = {"员工信息表", "专业"})
    private String specialty;

    @NotNull(groups = AddEmployee.class)
    @Pattern(regexp = "^在职|离职$", groups = {AddEmployee.class, UpdateEmployee.class}, message = "员工在职状态格式不正确！")
    @ColumnWidth(12)
    @ExcelProperty(value = {"员工信息表", "在职状态"})
    private String workState;

    @NotNull(groups = AddEmployee.class)
    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "入职日期"}, converter = CustomLocalDateConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate beginDate;

    @NotNull(groups = AddEmployee.class)
    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "转正日期"}, converter = CustomLocalDateConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate conversionDate;

    @NotNull(groups = AddEmployee.class)
    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "合同起始日期"}, converter = CustomLocalDateConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate beginContract;

    @NotNull(groups = AddEmployee.class)
    @Min(value = 0, groups = AddEmployee.class)
    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "合同期限"})
    private Double contractTerm;

    @NotNull(groups = AddEmployee.class)
    @ColumnWidth(16)
    @ExcelProperty(value = {"员工信息表", "合同结束日期"}, converter = CustomLocalDateConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate endContract;

    @NotNull(groups = AddEmployee.class)
    @Min(value = 0, groups = AddEmployee.class)
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