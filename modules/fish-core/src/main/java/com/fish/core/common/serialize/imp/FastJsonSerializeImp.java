package com.fish.core.common.serialize.imp;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.fish.core.common.serialize.Serializer;

/**
 * 常规序列化
 * 
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-24
 * @copyright 佳乐软件股份有限公司© 2019-2019
 *
 */
public class FastJsonSerializeImp implements Serializer {

	private static final SerializerFeature[] features = { SerializerFeature.DisableCircularReferenceDetect,
			SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat };
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
			}
			return v;
		}
	};

	@Override
	public Object Serialize(Object obj) {
		return JSON.toJSONString(obj, filter, features);
	}

	@Override
	public Object Deserialize(String obj, Class<?> clazz) {
		return JSON.parseObject(obj, clazz);
	}
}
