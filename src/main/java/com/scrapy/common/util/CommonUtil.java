package com.scrapy.common.util;

import java.lang.reflect.Array;
import java.util.*;

public class CommonUtil {

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true; // null이면 비어있음
        }

        if (object instanceof String) {
            return ((String) object).trim().isEmpty(); // 공백 문자열도 비어있음 처리
        }

        if (object instanceof Collection<?>) {
            return ((Collection<?>) object).isEmpty(); // List, Set, Queue 등
        }

        if (object instanceof Map<?, ?>) {
            return ((Map<?, ?>) object).isEmpty(); // HashMap 등
        }

        if (object.getClass().isArray()) {
            return Array.getLength(object) == 0; // 배열 길이 체크
        }

        return false; // 그 외 객체는 비어있다고 볼 수 없음
    }


}
