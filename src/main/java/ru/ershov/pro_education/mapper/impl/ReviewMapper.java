package ru.ershov.pro_education.mapper.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.ershov.pro_education.dto.ReviewDto;
import ru.ershov.pro_education.entity.Review;
import ru.ershov.pro_education.mapper.AbstractMapper;
import ru.ershov.pro_education.service.impl.StatusServiceImpl;

import java.util.Objects;

@Component
public class ReviewMapper extends AbstractMapper<Review, ReviewDto> {

    protected ReviewMapper(ModelMapper modelMapper, StatusServiceImpl statusService) {
        super(Review.class, ReviewDto.class, modelMapper);
        modelMapper
                .createTypeMap(Review.class, ReviewDto.class)
                .addMappings(m -> m.skip(ReviewDto::setStatus)).setPostConverter(toDtoConverter(statusService));
    }

    private Converter<Review, ReviewDto> toDtoConverter(StatusServiceImpl statusService) {
        return context -> {
            Review source = context.getSource();
            ReviewDto destination = context.getDestination();
            mapSpecificFields(source, destination, statusService);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(Review source, ReviewDto destination, StatusServiceImpl statusService) {
        destination.setStatus(Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : statusService.findById(source.getStatusId()).getName());
    }

}
