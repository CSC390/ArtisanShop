package csc394.artisanshop.request;

import lombok.Data;

@Data
public class CampaignCreateRequest {

    int productId;

    int discountAmount;

}
