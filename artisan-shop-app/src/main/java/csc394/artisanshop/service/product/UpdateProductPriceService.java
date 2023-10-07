package csc394.artisanshop.service.product;

import csc394.artisanshop.request.CampaignCreateRequest;
import csc394.artisanshop.request.PriceIncreaseRequest;

public interface UpdateProductPriceService {
    void createCampaign(CampaignCreateRequest campaignCreateRequest);

    void priceIncrease(PriceIncreaseRequest priceIncreaseRequest);
}
