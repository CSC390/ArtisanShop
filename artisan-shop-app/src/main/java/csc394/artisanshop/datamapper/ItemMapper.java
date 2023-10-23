package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.ImageDto;
import csc394.artisanshop.dto.ItemDto;
import csc394.artisanshop.entities.Image;
import csc394.artisanshop.entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        if (item == null) {
            return null;
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setDescription(item.getDescription());
        itemDto.setItemName(item.getItemName());
        itemDto.setPrice(item.getPrice());
        itemDto.setQuantity(item.getQuantity());
        itemDto.setSellerDto(SellerMapper.toSellerDto(item.getSeller()));
        itemDto.setBrand(item.getBrand());
        itemDto.setCategoryDto(CategoryMapper.toCategoryDto(item.getItemCategory()));

        List<ImageDto> imageDtos = item.getImageUrls() != null
                ? item.getImageUrls().stream()
                .map(ImageMapper::toImageDto)
                .collect(Collectors.toList())
                : new ArrayList<>();
        itemDto.setImages(imageDtos);

        return itemDto;
    }

    public static Item toItem(ItemDto itemDto) {
        if (itemDto == null) {
            return null;
        }

        Item item = new Item();
        item.setId(itemDto.getId());
        item.setItemName(itemDto.getItemName());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setQuantity(itemDto.getQuantity());
        item.setSeller(SellerMapper.toSeller(itemDto.getSellerDto()));
        item.setBrand(itemDto.getBrand());
        item.setItemCategory(CategoryMapper.toCategory(itemDto.getCategoryDto()));

        List<Image> images = itemDto.getImages() != null
                ? itemDto.getImages().stream()
                .map(ImageMapper::toImage)
                .collect(Collectors.toList())
                : new ArrayList<>();
        item.setImageUrls(images);
        return item;
    }
}
