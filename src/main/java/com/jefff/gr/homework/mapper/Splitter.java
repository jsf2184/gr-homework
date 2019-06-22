package com.jefff.gr.homework.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Splitter {
    public List<String> split(final String src) {
        if (src == null)
        {
            return new ArrayList<>();
        }
        String[] parts = src.split(" *[|, ] *");
        List<String> result = Arrays.stream(parts).filter(s -> !StringUtils.isEmpty(s)).collect(Collectors.toList());
        return result;
    }

}
