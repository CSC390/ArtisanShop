import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
import csc394.artisanshop.services.ShopService;

@SpringBootTest(classes = ArtisanShopApplication.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopService shopService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void whenUpdateProduct_thenProductUpdated() throws Exception {

        Long productId = 1L;
        Product originalProduct = new Product(); // Original product details
        originalProduct.setProductId(productId);
        originalProduct.setProductName(" Old Me");
        originalProduct.setProductPrice(20.00);

        Product updatedProduct = new Product(); // Updated product details
        updatedProduct.setProductId(productId);
        updatedProduct.setProductName("New Me (different price)");
        updatedProduct.setProductPrice(25.00);

        given(shopService.updateItem(eq(productId), any(Product.class))).willReturn(updatedProduct);

        mockMvc.perform(put("/products/{productId}", productId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedProduct)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").value(updatedProduct.getProductId()))
            .andExpect(jsonPath("$.productName").value(updatedProduct.getProductName()))
            .andExpect(jsonPath("$.productPrice").value(updatedProduct.getProductPrice()));
}

}
