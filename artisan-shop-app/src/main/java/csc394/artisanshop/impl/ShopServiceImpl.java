package csc394.artisanshop.impl;

import csc394.artisanshop.datamapper.ProductMapper;
import csc394.artisanshop.datamapper.SellerMapper;
import csc394.artisanshop.dto.ImageDto;
import csc394.artisanshop.dto.ProductDto;
import csc394.artisanshop.dto.SellerDto;
import csc394.artisanshop.entities.Product;
import csc394.artisanshop.entities.Seller;
import csc394.artisanshop.repositories.ProductDtoRepository;
import csc394.artisanshop.repositories.SellerDtoRepository;
import csc394.artisanshop.services.ShopService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final SellerDtoRepository sellerDtoRepository;
    private final ProductDtoRepository productDtoRepository;

    private final EntityManager entityManager;

    @Autowired
    public ShopServiceImpl(final SellerDtoRepository sellerDtoRepository,
                           final ProductDtoRepository productDtoRepository,
                           final EntityManager entityManager) {
        this.sellerDtoRepository = sellerDtoRepository;
        this.productDtoRepository = productDtoRepository;
        this.entityManager = entityManager;
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
    public Product addItem(Long sellerId, Product product) {
        Optional<SellerDto> sellerDtoOptional = sellerDtoRepository.findById(sellerId);
        if (!sellerDtoOptional.isPresent()) {
            throw new IllegalArgumentException("Seller with ID: " + sellerId + " not found");
        }
        SellerDto sellerDto = sellerDtoOptional.get();
        ProductDto productDto = ProductMapper.toProductDto(product);
        productDto.setSellerDto(sellerDto);

        if (productDto.getImages() != null) {
            for (ImageDto imageDto : productDto.getImages()) {
                imageDto.setProduct(productDto);
            }
        }

        ProductDto savedProductDto = productDtoRepository.save(productDto);
        return ProductMapper.toProduct(savedProductDto);
    }


    @Override
    @Transactional
    public void removeItem(Long sellerId, Long itemId) {
        Optional<ProductDto> itemDtoOptional = productDtoRepository.findById(itemId);
        if (!itemDtoOptional.isPresent() || !itemDtoOptional.get().getSellerDto().getId().equals(sellerId)) {
            throw new IllegalArgumentException("Item with ID: " + itemId + " not found or doesn't belong to the seller with ID: " + sellerId);
        }
        productDtoRepository.deleteById(itemId);
    }

    @Override
    @Transactional
    public List<Product> getSellerItems(Long sellerId) {
        List<ProductDto> productDtos = productDtoRepository.findBySellerDto_Id(sellerId);
        return productDtos.stream()
                .map(ProductMapper::toProduct)
                .collect(Collectors.toList());
    }

    public Product updateItem(Long itemId, Product product) {
        Optional<ProductDto> existingItemOpt = productDtoRepository.findById(itemId);

        if (!existingItemOpt.isPresent()) {
            throw new IllegalArgumentException("Item not found with id: " + itemId);
        }
        ProductDto existingItem = existingItemOpt.get();

        if (product.getProductName() != null) {
            existingItem.setProductName(product.getProductName());
        }
        if (product.getProductDetails() != null) {
            existingItem.setProductDetails(product.getProductDetails());
        }
        if (product.getProductPrice() != null) {
            existingItem.setProductPrice(product.getProductPrice());
        }
        if (product.getQuantity() != null) {
            existingItem.setQuantity(product.getQuantity());
        }
         productDtoRepository.save(existingItem);
        return ProductMapper.toProduct(existingItem);
    }
}
