package com.gzy.leeboo.utils.easyExcel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.gzy.leeboo.entity.Politics;

/**
 * <h1>用于 EasyExcel 的政治面貌格式化器</h1>
 * 实现 EasyExcel 对 {@link Politics} 的转换
 */
public class CustomPoliticsConverter implements Converter<Politics> {
    @Override
    public Class supportJavaTypeKey() {
        return Politics.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Politics convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Politics politics = new Politics();
        politics.setName(cellData.getStringValue());
        return politics;
    }

    @Override
    public CellData convertToExcelData(Politics value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(value.getName());
    }
}
