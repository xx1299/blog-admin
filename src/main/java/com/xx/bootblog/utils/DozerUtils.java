package com.xx.bootblog.utils;




import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DozerUtils {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass){
        List destinationList = new ArrayList();
        for (Iterator i$ = sourceList.iterator(); i$.hasNext();){
            Object sourceObject = i$.next();
            Object destinationObject = mapper.map(sourceObject, destinationClass);
            System.out.println(sourceObject);
            System.out.println(destinationObject);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    public static <T> T map(Object object, Class<T> destinationClass){
        T map = mapper.map(object, destinationClass);
        return map;
    }

}
