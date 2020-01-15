package com.fsoft.core.common.serialize.imp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.fsoft.core.common.serialize.Serializer;
import com.fsoft.core.utils.DateTimeUtils;

/*********
 * F-Soft 常规序列化
 * @package com.fsoft.core.common.serialize.imp
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-12-11
 * @CopyRight © F-Soft
 **********/
public class FastJsonSerializeImp implements Serializer {
	private static final SerializerFeature[] features = { SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue,
			SerializerFeature.WriteDateUseDateFormat };
	private static DecimalFormat decFormat = new DecimalFormat("#,##0.00");
	private static final ValueFilter filter = new ValueFilter() {
		@Override
		public Object process(Object obj, String s, Object v) {
			if (v == null)
				return "";
			if (v instanceof BigDecimal) {
				BigDecimal val = (BigDecimal) v;
				if (val.compareTo(new BigDecimal("0")) == 0) {
					return "0.00";
				}
				val = val.setScale(2, BigDecimal.ROUND_UP);
				return decFormat.format(val);
			} else if (v instanceof Date || v instanceof java.sql.Date) {
				return DateTimeUtils.formatDate((Date) v); //日期类型，格式化为YYYY-MM-DD 类型
			} else if (v instanceof Timestamp) {
				return DateTimeUtils.formatDateTime((Timestamp) v);	//时间类型，格式化YYYY-MM-DD HH:MI:SS
			}
			return v;
		}
	};

	@Override
	public String Serialize(Object obj) {
		return JSON.toJSONString(obj, filter, features);
	}

	@Override
	public Object Deserialize(String obj, Class<?> clazz) {
		return JSON.parseObject(obj, clazz);
	}
}
