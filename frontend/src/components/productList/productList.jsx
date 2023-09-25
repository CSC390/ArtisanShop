import React from "react";
import "./ProductList.css";
import Product from "../Product/Product";

const ProductList = ({ products }) => {
  return (
    <div className="product-lists grid bg-whitesmoke my-3">
      {products.map((product) => {
        // Calculate discounted price
        const discountedPrice =
          product.price - product.price * (product.discountPercentage / 100);

        return (
          <Product
            key={product.id}
            product={{ ...product, discountedPrice }} // Pass the product with discountedPrice prop
          />
        );
      })}
    </div>
  );
};

export default ProductList;
