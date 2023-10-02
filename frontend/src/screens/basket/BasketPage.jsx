import { useContext, useEffect } from "react";
import { BasketContext } from "../../context/basketContext";
import "../../styles/BasketPage.css";
import { FaHourglassEnd } from "react-icons/fa";
import { PaymentMethods, BasketItem, CheckoutSummary } from "../../components";
import images from "../../utils/images";
import { Link } from "react-router-dom";

const BasketPage = () => {
  const {
    basket,
    clearBasket,
    dispatch: basketDispatch,
    getCheckoutTotal,
    checkoutTotal,
    checkoutCount,
    setCheckoutAll,
    unsetCheckoutAll,
    checkoutAll,
  } = useContext(BasketContext);

  const checkallHandler = (event) => {
    if (event.target.checked) {
      setCheckoutAll(basketDispatch);
    } else {
      unsetCheckoutAll(basketDispatch);
    }
  };

  useEffect(() => {
    getCheckoutTotal(basketDispatch);
  }, [basket]);

  if (basket.length === 0) {
    return (
      <div className="container my-5">
        <div className="container-fluid border bg-white mb-5 mt-5">
          <div
            className="d-flex flex-column align-items-center justify-content-center"
            style={{ minHeight: "130px" }}
          >
            <h1 className="font-weight-semi-bold text-uppercase mb-3">
              Shopping Cart
            </h1>
          </div>
        </div>
        <div className="empty-cart flex justify-center align-center flex-column font-manrope">
          <img src={images.empty_cart} alt="" height="150px" width="200px" />
          <span className="fw-6 fs-15 text-gray">
            Your shopping cart is empty.
          </span>
          <Link to="/" className="shopping-btn bg-yellow text-white fw-5">
            Go shopping Now
          </Link>
        </div>
      </div>
    );
  }

  return (
    <main className="bg-whitesmoke">
      <div className="container">
        <div className="sc-wrapper">
          <div className="basket grid">
            {/* basket left */}
            <div className="basket-l py-4">
              <div className="basket-top bg-white py-3 px-4">
                <h2>
                  Shopping Cart <span className="text-primary">(2)</span>
                </h2>

                <div className="flex align-center justify-between">
                  <div className="checkbox-item flex py-3">
                    <div className="checkbox-icon flex align-center">
                      <input
                        type="checkbox"
                        className="form-control"
                        id="checkall"
                        onChange={checkallHandler}
                        checked={checkoutAll}
                      />
                    </div>
                    <p className="form-text">Select all items</p>
                  </div>
                  <button
                    type="button"
                    className="fw-7 fs-16 text-primary"
                    onClick={() => clearBasket(basketDispatch)}
                  >
                    Delete
                  </button>
                </div>
              </div>

              <div className="basket-list bg-white my-3">
                {basket.map((basketItem) => {
                  return <BasketItem item={basketItem} key={basketItem.id} />;
                })}
              </div>
            </div>
            {/* basket right */}
            <div className="basket-r py-4">
              <CheckoutSummary
                checkoutCount={Number(checkoutCount)}
                checkoutTotal={Number(checkoutTotal)}
              />
              <PaymentMethods />
            </div>
          </div>
        </div>
      </div>
    </main>
  );
};

export default BasketPage;
