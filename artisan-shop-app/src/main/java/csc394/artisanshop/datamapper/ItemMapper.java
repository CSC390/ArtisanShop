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
        itemDto.setName(item.getName());
        itemDto.setPrice(item.getPrice());
      //  itemDto.setCategoryDto(item.getItemCategory());
        return itemDto;
    }

    public static Item toItem(ItemDto itemDto) {
        if (itemDto == null) {
            return null;
        }

        Item item = new Item();
        item.setId(itemDto.getId());//.setSellerId(itemDto.getSellerId());
        item.setName(itemDto.getName());//.setSellerEmail(itemDto.getSellerEmail());
        item.setDescription(itemDto.getDescription());//.setSellerName(itemDto.getSellerName());
        item.setPrice(itemDto.getPrice());//.setFirstName(itemDto.getFirstName());
      //  item.setItemCategory(itemDto.getCategoryDto());

        return item;
    }
}
