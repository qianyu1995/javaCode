package com.angel.io.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

/**
 * 对象转换工具
 * @author toy_liu@enable-ets.com
 * @since 2017/08/18
 */
public class BeanUtils
{

	    /**
	     * 使用Dozer做对象转换
	     */
	    private static Mapper mapper = new DozerBeanMapper();

	    /**
	     * 实例对象间转换
	     * @param source 源实例对象
	     * @param destination 目标实例对象
	     * @return
	     */
	    public static <F, T> T convert(F source, T destination) {
	        if (source == null || destination == null) return null;
	        mapper.map(source, destination);
	        return destination;
	    }

	    /**
	     * 通过源实例列表对象生成目标实例列表对象
	     * @param fromList 源实例列表
	     * @param toClass 目标类型
	     * @return
	     */
	    public static <F, T> List<T> convert(List<F> fromList, final Class<T> toClass) {
	    	if (fromList == null) return Collections.emptyList();
	    	List<T> destination = new ArrayList<T>();
	    	for(F from : fromList) {
	    		if (from == null) {
	    			continue;
	    		}
	    		destination.add(convert(from,toClass));
	    	}
	    	return destination;
	    }

	    /**
	     * 通过源实例对象生成目标实例对象
	     * @param from 源实例对象
	     * @param toClass 目标类型
	     * @return
	     */
	    public static <F, T> T convert(F from, final Class<T> toClass) {
	        if (from == null) return null;
	        return mapper.map(from, toClass);
	    }

	public static String[] getNullPropertyNames (Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for(java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/**
	 * 复制属性忽略null
	 * @param src
	 * @param target
	 */
	public static void copyPropertiesIgnoreNull(Object src, Object target){
		org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}
}
