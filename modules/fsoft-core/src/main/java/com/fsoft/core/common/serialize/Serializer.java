package com.fsoft.core.common.serialize;

/*********
 * F-Soft 序列化接口
 * @package com.fsoft.core.common.serialize
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-12-11
 * @CopyRight © F-Soft
 **********/
public interface Serializer {

	String Serialize(Object obj);

	Object Deserialize(String obj, Class<?> objclass);

}
