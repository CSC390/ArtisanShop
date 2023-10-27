package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.ImageDto;
import csc394.artisanshop.entities.Image;

public class ImageMapper {
    public static ImageDto toImageDto(Image image) {
        if (image == null) {
            return null;
        }
        ImageDto dto = new ImageDto();
        dto.setId(image.getImageId());
        dto.setImages(image.getUrl());
        dto.setDescription(image.getDescription());
        return dto;
    }

    public static Image toImage(ImageDto dto) {
        if (dto == null) {
            return null;
        }
        Image image = new Image();
        image.setImageId(dto.getId());
        image.setUrl(dto.getImages());
        image.setDescription(dto.getDescription());
        return image;
    }
}
