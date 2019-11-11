package com.fish.core.common.serialize;

public interface Serializer {
	Object Serialize(Object obj);

	Object Deserialize(String obj, Class<?> objclass);

}
