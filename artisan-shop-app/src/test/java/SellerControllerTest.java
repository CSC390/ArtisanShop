import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import csc394.artisanshop.ArtisanShopApplication;
import csc394.artisanshop.entities.Product;
import csc394.artisanshop.entities.Seller;
import csc394.artisanshop.services.ShopService;

@SpringBootTest(classes = ArtisanShopApplication.class)
@AutoConfigureMockMvc
public class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopService shopService;

    @Autowired
    private ObjectMapper objectMapper; //to  serialize into JSON

    @Test
    public void whenRegisterSeller_thenCreateSeller() throws Exception {


        Seller seller = new Seller(); // Set up your Seller data
        seller.setAddress("Bohamour");
        seller.setFirstName("Hassan");
        seller.setLastName("Al-Mannai");
        seller.setSellerName("Hassan's Shop");
        seller.setSellerId(1L);

        given(shopService.setupShop(any(Seller.class))).willReturn(seller);

        mockMvc.perform(post("/seller/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(seller)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.sellerId").value(seller.getSellerId()))
            .andExpect(jsonPath("$.sellerName").value(seller.getSellerName()));

    }


    @Test
    public void whenUpdateSeller_thenSellerUpdated() throws Exception {

        Long sellerId = 1L;
        Seller sellerToUpdate = new Seller(); // seller to be updated
        sellerToUpdate.setAddress("220 E 42nd St, New York, NY 10017");
        sellerToUpdate.setFirstName("His Highness");
        sellerToUpdate.setLastName("Sheikh Tamim");
        sellerToUpdate.setSellerName("Tamim's Shop");
        sellerToUpdate.setSellerId(sellerId); 

        Seller updatedSeller = new Seller(); // Expected info
        updatedSeller.setAddress("120 E 42nd St, New York, NY 10017");
        updatedSeller.setFirstName("Me");
        updatedSeller.setLastName("So");
        updatedSeller.setSellerName("MeSo soup");
        updatedSeller.setSellerId(sellerId);

        given(shopService.updateShop(eq(sellerId), any(Seller.class))).willReturn(updatedSeller);

        mockMvc.perform(put("/seller/update/{id}", sellerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sellerToUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sellerId").value(updatedSeller.getSellerId()))
                .andExpect(jsonPath("$.sellerName").value(updatedSeller.getSellerName()))
                .andExpect(jsonPath("$.address").value(updatedSeller.getAddress()))
                .andExpect(jsonPath("$.firstName").value(updatedSeller.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(updatedSeller.getLastName()));
    }
    
    @Test
    public void whenAddItem_thenProductAdded() throws Exception {
     
        Long sellerId = 1L;
        Seller seller = new Seller(); 
        seller.setSellerId(sellerId);

        Product newProduct = new Product(); // The product to be added
        newProduct.setProductName("Vase");
        newProduct.setProductDetails("A  handcrafted pottery vase.");
        newProduct.setProductPrice(25.99);
        newProduct.setSeller(seller); 

        Product addedProduct = new Product(); // The expected product object after addition
        addedProduct.setProductName(newProduct.getProductName());
        addedProduct.setProductDetails(newProduct.getProductDetails());
        addedProduct.setProductPrice(newProduct.getProductPrice());

        // Mock the behavior of the shop service to return the added product
        given(shopService.addItem(eq(sellerId), any(Product.class))).willReturn(addedProduct);

   
        mockMvc.perform(post("/seller/{sellerId}/addItem", sellerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(addedProduct.getId()))
                .andExpect(jsonPath("$.productName").value(addedProduct.getProductName()))
                .andExpect(jsonPath("$.productDetails").value(addedProduct.getProductDetails()))
                .andExpect(jsonPath("$.productPrice").value(addedProduct.getProductPrice()));
    }

    @Test
    public void whenRemoveItem_thenProductRemoved() throws Exception {

        Long sellerId = 1L;
        Long itemId = 1L;
    
        doNothing().when(shopService).removeItem(eq(sellerId), eq(itemId));
    

        mockMvc.perform(delete("/seller/{sellerId}/removeItem/{itemId}", sellerId, itemId))
            .andExpect(status().isNoContent()); // Expect 204 no content response
    }

    @Test
    public void whenGetSellerItems_thenReturnListOfProducts() throws Exception {

        Long sellerId = 1L;
        List<Product> productList = new ArrayList<>();
        Product productOne = new Product();
        productOne.setProductId(1L);
        productOne.setProductName("Oryx Necklace");
        productOne.setProductPrice(15.99);
        productList.add(productOne);

        Product productTwo = new Product();
        productTwo.setProductId(2L);
        productTwo.setProductName("Candle");
        productTwo.setProductPrice(8.99);
        productList.add(productTwo);

        given(shopService.getSellerItems(eq(sellerId))).willReturn(productList);

        mockMvc.perform(get("/seller/{sellerId}/items", sellerId))
                .andExpect(status().isOk())
                //supposing a list of 2 products is returned
                .andExpect(jsonPath("$[0].productName").value(productOne.getProductName()))
                .andExpect(jsonPath("$[0].productPrice").value(productOne.getProductPrice()))
                .andExpect(jsonPath("$[1].productName").value(productTwo.getProductName()))
                .andExpect(jsonPath("$[1].productPrice").value(productTwo.getProductPrice()));
    }

    @Test
    public void whenGetSellerByShopName_thenSellerReturned() throws Exception {
      
        String shopName = "Hassan's Shop";
        Seller seller = new Seller(); 
        seller.setSellerId(1L);
        seller.setSellerName(shopName);
        seller.setFirstName("Hassan");
        seller.setLastName("Al-Mannai");
        seller.setAddress("123 s Lane");

    
        given(shopService.findByShopName(eq(shopName))).willReturn(Optional.of(seller));


        mockMvc.perform(get("/seller/seller/{shopName}", shopName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sellerId").value(seller.getSellerId()))
                .andExpect(jsonPath("$.sellerName").value(seller.getSellerName()))
                .andExpect(jsonPath("$.firstName").value(seller.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(seller.getLastName()))
                .andExpect(jsonPath("$.address").value(seller.getAddress()));
    }

}
