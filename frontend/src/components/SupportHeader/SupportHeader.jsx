import { useState, useEffect, useContext } from "react";
import { Link } from "react-router-dom";
// react icons
import { FaUser } from "react-icons/fa";
import { FiLogOut } from "react-icons/fi";
import { BsSearch, BsCaretDownFill } from "react-icons/bs";
import { HiShoppingBag } from "react-icons/hi";
import { AiOutlineBars } from "react-icons/ai";
// contexts
import { CategoryContext } from "../../context/categoryContext";
import { BasketContext } from "../../context/basketContext";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "../../styles/SupportHeader.css";
import { useSelector, useDispatch } from "react-redux";

const SupportHeader = () => {
  const { userInfo } = useSelector((state) => state.auth);
  const { categories } = useContext(CategoryContext);
  const [showCategory, setShowCategory] = useState(false);

  const toggleCategory = () => {
    setShowCategory((prevData) => !prevData);
  };

  const {
    getBasketTotal,
    itemsCount,
    totalAmount,
    dispatch: basketDispatch,
    basket,
  } = useContext(BasketContext);

  useEffect(() => {
    getBasketTotal(basketDispatch);
    // eslint-disable-next-line
  }, [basket]);

  return (
    <nav className="">
      <div className="navbar-main bg-primary">
        <div className="container">
          <div className="row navbar-main-top align-items-center justify-content-between">
            <div className="col-auto">
              <Link to="/" className="navbar-brand">
                <span className="text-yellow fs-26 fw-6">Artisan </span>
                <span className="text-white fs-26 fw-6">Marketplace</span>
              </Link>
            </div>

            <div className="col-auto">
              <div className="navbar-basket text-white d-flex align-items-center">
                <Link to="/cart" className="basket-btn">
                  <HiShoppingBag size={29} />
                  <span className="basket-count flex align-center justify-center">
                    {userInfo ? itemsCount : "0"}
                  </span>
                </Link>
                <div className="text-end basket-count">
                  <p className="text-uppercase fs-14">my cart</p>
                  <Link to="/basket" className="fw-7">
                    $ &nbsp;
                    <span className="basket-amount">
                      {userInfo ? totalAmount : "0"}
                    </span>
                  </Link>
                </div>
              </div>
            </div>
          </div>
          <div className="navbar-main-bottom flex align-center justify-between p-2">
            <div className="navbar-cats-wrapper">
              <div
                className="navbar-cats-btn flex align-center text-white px-2 py-2"
                onClick={toggleCategory}
              >
                <AiOutlineBars />
                <span className="text-uppercase mx-3 fs-13">
                  all categories
                </span>
                <BsCaretDownFill />
              </div>
              <ul
                className={`category-list ${
                  showCategory ? "show-category-list" : ""
                }`}
                onClick={toggleCategory}
              >
                {categories.map((category, idx) => {
                  return (
                    <li className="category-item" key={idx}>
                      <Link
                        to={`category/${category}`}
                        className="category-item-link text-uppercase text-dark fs-12"
                      >
                        {category?.replace("-", " ")}
                      </Link>
                    </li>
                  );
                })}
              </ul>
            </div>
            <div className="">
              <ul className="horizontal-nav flex align-center">
                {categories.slice(0, 6).map((category, idx) => {
                  return (
                    <li className="nav-item" key={idx}>
                      <Link
                        to={`/category/${category}`}
                        className="nav-link no-wrap"
                      >
                        {category.replace("-", " ")}
                      </Link>
                    </li>
                  );
                })}
              </ul>
            </div>
          </div>
        </div>
      </div>
      <ToastContainer />
    </nav>
  );
};

export default SupportHeader;
