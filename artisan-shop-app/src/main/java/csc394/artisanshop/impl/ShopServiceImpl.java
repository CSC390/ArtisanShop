package csc394.artisanshop.impl;

import csc394.artisanshop.datamapper.ItemMapper;
import csc394.artisanshop.datamapper.SellerMapper;
import csc394.artisanshop.dto.ItemDto;
import csc394.artisanshop.dto.SellerDto;
import csc394.artisanshop.entities.Item;
import csc394.artisanshop.entities.Seller;
import csc394.artisanshop.repositories.ItemDtoRepository;
import csc394.artisanshop.repositories.SellerDtoRepository;
import csc394.artisanshop.services.ShopService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final SellerDtoRepository sellerDtoRepository;
    private final ItemDtoRepository itemDtoRepository;

    @Autowired
    public ShopServiceImpl(final SellerDtoRepository sellerDtoRepository,
                           final ItemDtoRepository itemDtoRepository) {
        this.sellerDtoRepository = sellerDtoRepository;
        this.itemDtoRepository = itemDtoRepository;
    }

    @Transactional
    @Override
    public Seller setupShop(Seller seller) {
        SellerDto sellerDto = SellerMapper.toSellerDto(seller);
        SellerDto savedSellerDto = sellerDtoRepository.save(sellerDto);
        return SellerMapper.toSeller(savedSellerDto);
    }


    @Override
    @Transactional
    public Optional<Seller> findByShopName(String shopName) {
        Optional<SellerDto> sellerDtoOptional = sellerDtoRepository.findBySellerName(shopName);
        return sellerDtoOptional.map(SellerMapper::toSeller);
    }

    @Override
    @Transactional
    public Seller updateShop(Long id, Seller seller) {
        SellerDto sellerDto = SellerMapper.toSellerDto(seller);
        SellerDto updatedSellerDto = sellerDtoRepository.save(sellerDto);
        return SellerMapper.toSeller(updatedSellerDto);
    }
    @Override
    @Transactional
    public Item addItem(Long sellerId, Item item) {
        Optional<SellerDto> sellerDtoOptional = sellerDtoRepository.findById(sellerId);
        if (!sellerDtoOptional.isPresent()) {
            throw new IllegalArgumentException("Seller with ID: " + sellerId + " not found");
        }
        SellerDto sellerDto = sellerDtoOptional.get();
        ItemDto itemDto = ItemMapper.toItemDto(item);
        itemDto.setSellerDto(sellerDto);
        ItemDto savedItemDto = itemDtoRepository.save(itemDto);
        return ItemMapper.toItem(savedItemDto);
    }

    @Override
    @Transactional
    public Item updateItemPrice(Long itemId, Double newPrice) {
        Optional<ItemDto> optionalItem = itemDtoRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            ItemDto itemDto = optionalItem.get();
            itemDto.setPrice(newPrice);
            ItemDto updatedItemDto = itemDtoRepository.save(itemDto);
            return ItemMapper.toItem(updatedItemDto);
        } else {
            throw new IllegalArgumentException("Item with ID: " + itemId + " not found");
        }
    }
    @Override
    @Transactional
    public List<Item> getAllItems() {
        List<ItemDto> itemDto = itemDtoRepository.findAll();
        return itemDto.stream()
                .map(ItemMapper::toItem)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteSeller(Long id) {
        sellerDtoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void removeItem(Long sellerId, Long itemId) {
        Optional<ItemDto> itemDtoOptional = itemDtoRepository.findById(itemId);
        if (!itemDtoOptional.isPresent() || !itemDtoOptional.get().getSellerDto().getId().equals(sellerId)) {
            throw new IllegalArgumentException("Item with ID: " + itemId + " not found or doesn't belong to the seller with ID: " + sellerId);
        }

        itemDtoRepository.deleteById(itemId);
    }

    @Override
    @Transactional
    public List<Item> getSellerItems(Long sellerId) {
        List<ItemDto> itemDtos = itemDtoRepository.findBySellerDto_Id(sellerId);
        return itemDtos.stream()
                .map(ItemMapper::toItem)
                .collect(Collectors.toList());
    }
}
