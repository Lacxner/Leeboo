package com.gzy.leeboo.utils.easyExcel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.gzy.leeboo.entity.Department;

/**
 * <h1>用于 EasyExcel 的部门格式化器</h1>
 * 实现 EasyExcel 对 {@link Department} 的转换
 */
public class CustomDepartmentConverter implements Converter<Department> {
    @Override
    public Class supportJavaTypeKey() {
        return Department.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Department convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Department department = new Department();
        department.setName(cellData.getStringValue());
        return department;
    }

    @Override
    public CellData convertToExcelData(Department value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(value.getName());
    }
}
