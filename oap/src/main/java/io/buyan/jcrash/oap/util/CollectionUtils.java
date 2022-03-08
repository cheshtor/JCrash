package io.buyan.jcrash.oap.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pengyu Gan
 * CreateDate 2022/3/8
 */
public class CollectionUtils {

    public static int DEFAULT_SLICE_LENGTH = 500;

    public static <T> List<List<T>> slice(List<T> data) {
        return slice(data, DEFAULT_SLICE_LENGTH);
    }

    /**
     * 将集合按指定长度进行切片
     * @param data 要切片的集合
     * @param sliceLength 切片长度
     * @param <T> 集合类型
     * @return 集合切片
     */
    public static <T> List<List<T>> slice(List<T> data, int sliceLength) {
        List<List<T>> slices = new ArrayList<>();
        if (data.size() <= sliceLength) {
            slices.add(data);
            return slices;
        }
        int toIndexBase = sliceLength;
        for (int i = 0; i < data.size(); i += sliceLength) {
            if (data.size() - i < sliceLength) {
                toIndexBase = data.size() - i;
            }
            List<T> slice = data.subList(i, i + toIndexBase);
            slices.add(slice);
        }
        return slices;
    }

}
