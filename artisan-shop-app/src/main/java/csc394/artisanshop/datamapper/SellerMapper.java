package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.SellerDto;
import csc394.artisanshop.entities.Seller;

public class SellerMapper {

    public static SellerDto toSellerDto(Seller seller) {
        if (seller == null) {
            return null;
        }

        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(seller.getSellerId());
        sellerDto.setSellerEmail(seller.getSellerEmail());
        sellerDto.setFirstName(seller.getFirstName());
        sellerDto.setLastName(seller.getLastName());
        sellerDto.setSellerName(seller.getSellerName());
        return sellerDto;
    }

    public static Seller toSeller(SellerDto sellerDto) {
        if (sellerDto == null) {
            return null;
        }

        Seller seller = new Seller();
        seller.setSellerId(sellerDto.getId());
        seller.setSellerEmail(sellerDto.getSellerEmail());
        seller.setSellerName(sellerDto.getSellerName());
        seller.setFirstName(sellerDto.getFirstName());
        seller.setLastName(sellerDto.getLastName());

        return seller;
    }
}
