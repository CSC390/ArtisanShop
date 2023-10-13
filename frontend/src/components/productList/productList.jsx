import "../../styles/ProductList.css";
import "../../App.css";
import { AiOutlineStar } from "react-icons/ai";
import { Link } from "react-router-dom";
import { FilterContext } from "../../context/filterContext";
import PropTypes from "prop-types";
import { useContext } from "react";
import React, { useState } from "react";

const ProductList = ({ products }) => {
  const { grid_view } = useContext(FilterContext);

  return (
    <div className="products">
      <div className="container">
        <div className={`product-list ${grid_view ? "gridview" : "listview"}`}>
          {products?.map((product) => {
            return (
              <Link
                to={`/products/${product?.id}`}
                className="product-item"
                key={product?.id}
              >
                <div className="product-item-img">
                  <img
                    src={product?.productImageUrl}
                    alt={product?.productName}
                    className="img-cover"
                  />
                  <div className="product-discount">
                    {product?.discountPercentage}
                    <span>%</span>
                  </div>
                </div>
                <div className="product-item-body">
                  <span className="product-category">
                    {product?.categories?.[0]?.categoryName}
                  </span>
                  <span className="product-title">{product?.productName}</span>

                  <div className="product-price">
                    <span className="fw-6 fs-16">
                      $ &nbsp;{product?.productPrice}
                    </span>
                    <span className="text-dark">
                      Brand: {product?.productBrand}
                    </span>
                  </div>

                  <div className="product-item-bottom fs-12 flex align-center">
                    <div>
                      <span className="fw-6">Stock:</span> {product?.stock}
                    </div>
                    <div className="product-rating flex align-center">
                      <AiOutlineStar />
                      {product?.favoriteNumber}
                    </div>
                  </div>
                </div>
              </Link>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default ProductList;

ProductList.propTypes = {
  products: PropTypes.array,
};
