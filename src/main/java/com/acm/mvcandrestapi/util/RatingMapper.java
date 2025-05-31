package com.acm.mvcandrestapi.util;

import com.acm.mvcandrestapi.dto.RatingDto;
import com.acm.mvcandrestapi.model.Rating;

import java.util.List;

public class RatingMapper {

    private Double rate;
    private Long count;

    public static RatingDto modelToDto(Rating rating) {
        return RatingDto.builder()
                .rate(rating.getRate())
                .count(rating.getCount())
                .build();
    }

    public static Rating dtoToModel(RatingDto ratingDto) {
        return Rating.builder()
                .rate(ratingDto.getRate())
                .count(ratingDto.getCount())
                .build();
    }

    public static List<RatingDto> modelListToDtoList(List<Rating> ratingList) {
        return ratingList.stream()
                .map(RatingMapper::modelToDto)
                .toList();
    }

    public static List<Rating> dtoListToModelList(List<RatingDto> ratingDtoList) {
        return ratingDtoList.stream()
                .map(RatingMapper::dtoToModel)
                .toList();
    }

}
