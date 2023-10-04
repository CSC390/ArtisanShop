import React from "react";
import { Link } from "react-router-dom";
import Logo from "../Admin/Logo";
import { FaBox, FaList } from "react-icons/fa";

export default function Nav() {
  return (
    <aside className="h-100vh bg-dark text-white p-4">
      <div className="mb-4 mr-4">
        <Logo />
      </div>
      <nav className="nav flex-column">
        <Link to="/admin/products" className="nav-link">
          <FaBox className="mr-3" />
          Products
        </Link>
        <Link to="/admin/categories" className="nav-link">
          <FaList className="mr-2" />
          Categories
        </Link>
      </nav>
    </aside>
  );
}
