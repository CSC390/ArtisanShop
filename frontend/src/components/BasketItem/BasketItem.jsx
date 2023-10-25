import { AiOutlineMinus, AiOutlinePlus } from "react-icons/ai";
import { BsTrash } from "react-icons/bs";
import { formatPrice } from "../../utils/helpers";
import "../../styles/BasketPage.css";
import { BasketContext } from "../../context/basketContext";
import PropTypes from "prop-types";
import { useContext } from "react";

const BasketItem = ({ product }) => {
  const {
    dispatch: basketDispatch,
    addQtyItem,
    minusQtyItem,
    removeFromBasket,
    addToCheckout,
    removeFromCheckout,
  } = useContext(BasketContext);

  const singleCheckoutHandler = (event) => {
    if (event.target.checked) {
      addToCheckout(basketDispatch, product?.id);
    } else {
      removeFromCheckout(basketDispatch, product?.id);
    }
  };

  return (
    <div className="basket-list-product grid px-3 py-3" key={product.id}>
      <div className="checkbox-product py-3">
        <div className="checkbox-icon">
          <input
            type="checkbox"
            className="form-control"
            onChange={singleCheckoutHandler}
            checked={product.checkoutStatus}
          />
        </div>
      </div>

      <div className="basket-list-product-info grid">
        <div className="product-info-img">
          <img
            src={product?.productImageUrl}
            alt={product?.productName}
            className="img-cover"
          />
        </div>
        <div className="product-info-details py-2">
          <div className="product-info-details-top">
            <h4>{product?.productName}</h4>
            <button
              type="button"
              className="remove-btn"
              onClick={() => removeFromBasket(basketDispatch, product.id)}
            >
              <BsTrash />
            </button>
          </div>

          <div className="flex align-center flex-wrap py-1">
            <span className="fs-13 text-dark">Brand: {product?.productBrand}</span>
            <span className="mx-3 fs-13 text-dark">
              Category: {product?.categories[0].categoryName}
            </span>
          </div>

          <div className="flex align-center justify-between">
            <span className="fw-7 fs-17 text-yellow">
              ${product?.productPrice}
            </span>
            <div className="quantity">
              <div className="quantity-toggle flex">
                <button
                  className={`qty-dec flex align-center justify-center ${
                    product?.quantity === 1 ? "active" : ""
                  }`}
                  onClick={() => minusQtyItem(basketDispatch, product?.id)}
                >
                  <AiOutlineMinus size={14} />
                </button>
                <div className="qty-value flex align-center justify-center fs-14 mx-2">
                  {product?.quantity}
                </div>
                <button
                  className={`qty-inc flex align-center justify-center ${
                    product?.quantity === product?.stock ? "active" : ""
                  }`}
                  onClick={() => addQtyItem(basketDispatch, product?.id)}
                >
                  <AiOutlinePlus size={14} />
                </button>
              </div>
            </div>
          </div>

          <div className="fs-14">
            <span className="fw-6">Total: </span>
            {formatPrice(product?.totalPrice)}
          </div>
        </div>
      </div>
    </div>
  );
};

export default BasketItem;

BasketItem.propTypes = {
  product: PropTypes.object,
};
