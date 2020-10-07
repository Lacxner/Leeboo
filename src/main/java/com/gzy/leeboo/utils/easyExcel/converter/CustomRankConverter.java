package com.gzy.leeboo.utils.easyExcel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.gzy.leeboo.entity.Rank;

/**
 * <h1>用于 EasyExcel 的职称格式化器</h1>
 * 实现 EasyExcel 对 {@link Rank} 的转换
 */
public class CustomRankConverter implements Converter<Rank> {
    @Override
    public Class supportJavaTypeKey() {
        return Rank.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Rank convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Rank rank = new Rank();
        rank.setName(cellData.getStringValue());
        return rank;
    }

    @Override
    public CellData convertToExcelData(Rank value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(value.getName());
    }
}
