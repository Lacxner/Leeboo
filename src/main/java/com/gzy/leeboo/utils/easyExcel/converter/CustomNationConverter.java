package com.gzy.leeboo.utils.easyExcel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.gzy.leeboo.entity.Nation;

/**
 * <h1>用于 EasyExcel 的民族格式化器</h1>
 * 实现 EasyExcel 对 {@link Nation} 的转换
 */
public class CustomNationConverter implements Converter<Nation> {
    @Override
    public Class supportJavaTypeKey() {
        return Nation.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Nation convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Nation nation = new Nation();
        nation.setName(cellData.getStringValue());
        return nation;
    }

    @Override
    public CellData convertToExcelData(Nation value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(value.getName());
    }
}
