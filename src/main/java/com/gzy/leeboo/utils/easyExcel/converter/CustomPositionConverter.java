package com.gzy.leeboo.utils.easyExcel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.gzy.leeboo.entity.Position;

/**
 * <h1>用于 EasyExcel 的职位格式化器</h1>
 * 实现 EasyExcel 对 {@link Position} 的转换
 */
public class CustomPositionConverter implements Converter<Position> {
    @Override
    public Class supportJavaTypeKey() {
        return Position.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Position convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Position position = new Position();
        position.setName(cellData.getStringValue());
        return position;
    }

    @Override
    public CellData convertToExcelData(Position value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(value.getName());
    }
}
