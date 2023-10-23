package csc394.artisanshop.impl;

import csc394.artisanshop.datamapper.ItemMapper;
import csc394.artisanshop.datamapper.SellerMapper;
import csc394.artisanshop.dto.ImageDto;
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
        Optional<SellerDto> existingSellerOpt = sellerDtoRepository.findById(id);

        if (!existingSellerOpt.isPresent()) {
            throw new IllegalArgumentException("Seller not found with id: " + id);
        }

        SellerDto existingSellerDto = existingSellerOpt.get();

        if (seller.getSellerName() != null) {
            existingSellerDto.setSellerName(seller.getSellerName());
        }
        if (seller.getSellerEmail() != null) {
            existingSellerDto.setSellerEmail(seller.getSellerEmail());
        }
        if (seller.getFirstName() != null) {
            existingSellerDto.setFirstName(seller.getFirstName());
        }
        if (seller.getLastName() != null) {
            existingSellerDto.setLastName(seller.getLastName());
        }

        SellerDto updatedSellerDto = sellerDtoRepository.save(existingSellerDto);
        return SellerMapper.toSeller(updatedSellerDto);
    }

    @Transactional
    @Override
    public Item addItem(Long sellerId, Item item) {
        Optional<SellerDto> sellerDtoOptional = sellerDtoRepository.findById(sellerId);
        if (!sellerDtoOptional.isPresent()) {
            throw new IllegalArgumentException("Seller with ID: " + sellerId + " not found");
        }
        SellerDto sellerDto = sellerDtoOptional.get();
        ItemDto itemDto = ItemMapper.toItemDto(item);
        itemDto.setSellerDto(sellerDto);

        if (itemDto.getImages() != null) {
            for (ImageDto imageDto : itemDto.getImages()) {
                imageDto.setItem(itemDto);
            }
        }

        ItemDto savedItemDto = itemDtoRepository.save(itemDto);
        return ItemMapper.toItem(savedItemDto);
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

    public ItemDto updateItem(Long itemId, Item item) {
        Optional<ItemDto> existingItemOpt = itemDtoRepository.findById(itemId);

        if (!existingItemOpt.isPresent()) {
            throw new IllegalArgumentException("Item not found with id: " + itemId);
        }
        ItemDto existingItem = existingItemOpt.get();

        if (item.getItemName() != null) {
            existingItem.setItemName(item.getItemName());
        }
        if (item.getDescription() != null) {
            existingItem.setDescription(item.getDescription());
        }
        if (item.getPrice() != null) {
            existingItem.setPrice(item.getPrice());
        }
        if (item.getQuantity() != null) {
            existingItem.setQuantity(item.getQuantity());
        }
        return itemDtoRepository.save(existingItem);
    }
}
