package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.ItemDto;
import csc394.artisanshop.entities.Item;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        if (item == null) {
            return null;
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setDescription(item.getDescription());
        itemDto.setItemName(item.getItemNameEnt());
        itemDto.setPrice(item.getPrice());
        itemDto.setImageUrl(item.getImageUrl());
        itemDto.setQuantity(item.getQuantity());
        itemDto.setCategoryDto(CategoryMapper.toCategoryDto(item.getItemCategory()));
        itemDto.setSellerDto(SellerMapper.toSellerDto(item.getSeller()));

        return itemDto;
    }

    public static Item toItem(ItemDto itemDto) {
        if (itemDto == null) {
            return null;
        }

        Item item = new Item();
        item.setId(itemDto.getId());
        item.setItemNameEnt(itemDto.getItemName());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setQuantity(itemDto.getQuantity());
        item.setImageUrl(itemDto.getImageUrl());
        item.setItemCategory(CategoryMapper.toCategory(itemDto.getCategoryDto()));
        item.setSeller(SellerMapper.toSeller(itemDto.getSellerDto()));
        return item;
    }
}
