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

const SupportHeader = () => {
  const { categories } = useContext(CategoryContext);
  const [showCategory, setShowCategory] = useState(false);

  const toggleCategory = () => {
    setShowCategory((prevData) => !prevData);
  };

  return (
    <nav className="navbar">
      <div className="navbar-main bg-primary">
        <div className="container">
          <div className="navbar-main-bottom flex align-center justify-between">
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

            <ul className="navbar-nav flex align-center">
              {categories.slice(0, 6).map((category, idx) => {
                return (
                  <li className="nav-item" key={idx}>
                    <Link
                      to={`category/${category}`}
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
    </nav>
  );
};

export default SupportHeader;
